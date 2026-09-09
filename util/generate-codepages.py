#!/usr/bin/env python3
"""Generate CaffC single-byte code-page classes from a local unicode data tree.

Default data root (already downloaded):

  /home/raptor/learn/projects/caffc/unicode/
    www.unicode.org/Public/MAPPINGS/...
    www.iana.org/assignments/charset-reg/...

Output (grouped by vendor / registry):

  templates/i18n/default/caffc/generated/
    iso/          microsoft/     apple/     misc/     next/
    iana/         # from IANA charset-reg (+ resolved local mapping tables)

Only single-byte mappings are generated (keys 0x00–0xFF).

Usage:
  python3 util/generate-codepages.py
  python3 util/generate-codepages.py --unicode-root /path/to/unicode
"""

from __future__ import annotations

import argparse
import re
from dataclasses import dataclass
from pathlib import Path

REPO_ROOT = Path(__file__).resolve().parents[1]
DEFAULT_OUT = REPO_ROOT / "templates" / "i18n" / "default" / "caffc" / "generated"
DEFAULT_UNICODE_ROOT = Path("/home/raptor/learn/projects/caffc/unicode")

FORMAT_A_LINE = re.compile(r"^0x([0-9A-Fa-f]+)\s+0x([0-9A-Fa-f]+)")
IBM_STYLE_LINE = re.compile(r"^([0-9A-Fa-f]{2})\s+([0-9A-Fa-f]{4})\b")
CHARSET_NAME = re.compile(r"Charset name(?:\(s\))?:\s*([^\n\r]+)", re.I)
MAPPINGS_URL = re.compile(
    r"https?://(?:www\.)?unicode\.org/Public/MAPPINGS/([^\s<>\"']+)",
    re.I,
)

# ISO/IEC 8859 parts that exist (12 was never published).
ISO8859_PARTS = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 16)

MICROSOFT_WINDOWS = (1250, 1251, 1252, 1253, 1254, 1255, 1256, 1257, 1258, 874)
MICROSOFT_PC = (
    437, 737, 775, 850, 852, 855, 857, 860, 861, 862, 863, 864, 865, 866, 869
)

APPLE_SINGLE_BYTE = (
    "ROMAN", "CYRILLIC", "GREEK", "TURKISH", "CENTEURO",
    "CELTIC", "CROATIAN", "ICELAND", "ROMANIAN",
    "ARABIC", "FARSI", "HEBREW", "THAI", "DINGBATS", "SYMBOL",
    "GAELIC", "INUIT",
)

MISC_TABLES = (
    ("KOI8-R.TXT", "Koi8R", "koi8-r"),
    ("KOI8-U.TXT", "Koi8U", "koi8-u"),
    ("CP1006.TXT", "1006", "1006"),
    ("CP424.TXT", "424", "424"),
    ("CP856.TXT", "856", "856"),
    ("ATARIST.TXT", "AtariSt", "atari-st"),
    ("KZ1048.TXT", "Kz1048", "kz-1048"),
)

IANA_SKIP_NAMES = {
    "charset-reg.csv", "charset-reg.txt", "charset-reg.xml", "index.html",
}

# IANA regs that omit a MAPPINGS URL but map 1:1 onto a known local table.
IANA_IMPLIED_MAPPINGS = {
    "windows-1250": "VENDORS/MICSFT/WINDOWS/CP1250.TXT",
    "windows-1251": "VENDORS/MICSFT/WINDOWS/CP1251.TXT",
    "windows-1252": "VENDORS/MICSFT/WINDOWS/CP1252.TXT",
    "windows-1253": "VENDORS/MICSFT/WINDOWS/CP1253.TXT",
    "windows-1254": "VENDORS/MICSFT/WINDOWS/CP1254.TXT",
    "windows-1255": "VENDORS/MICSFT/WINDOWS/CP1255.TXT",
    "windows-1256": "VENDORS/MICSFT/WINDOWS/CP1256.TXT",
    "windows-1257": "VENDORS/MICSFT/WINDOWS/CP1257.TXT",
    "windows-1258": "VENDORS/MICSFT/WINDOWS/CP1258.TXT",
    "windows-874": "VENDORS/MICSFT/WINDOWS/CP874.TXT",
    "ISO-8859-13": "ISO8859/8859-13.TXT",
    "ISO-8859-14": "ISO8859/8859-14.TXT",
    "ISO-8859-15": "ISO8859/8859-15.TXT",
    "ISO-8859-16": "ISO8859/8859-16.TXT",
    "ibm775": "VENDORS/MICSFT/PC/CP775.TXT",
    "ibm866": "VENDORS/MICSFT/PC/CP866.TXT",
    "KZ-1048": "VENDORS/MISC/KZ1048.TXT",
}


@dataclass(frozen=True)
class CodePageSpec:
    vendor_dir: str
    vendor_name: str
    class_name: str
    registry_name: str
    mapping_text: str
    source_label: str


TEMPLATE = """\
module caffc.i18n

#ifdef i18n.files.contains("generated/{vendor_dir}/{file_name}") {{
#public
class CodePage{class_name} implements CodePageEncoding {{
  TreeSetDictU8ToU32 codePageToUtf8
  TreeSetDictU32ToU8 utf8ToCodePage

  constructor() {{
    _this.codePageToUtf8 = new TreeSetDictU8ToU32(256)
    _this.utf8ToCodePage = new TreeSetDictU32ToU8(256)

{puts}
  }}

  name() -> str {{
    return "{registry_name}"
  }}

  vendor() -> str {{
    return "{vendor_name}"
  }}

  fromUtf8(str value) -> u8[] data, bool error, i32 errorByteIndex {{
    i32 currentByteIndex = 0
    i32 nextByteIndex = 0
    i32 outByteIndex = 0
    u8 byteValue
    bool codePageError
    u32 codePoint

    if not value or value.size() == 0 {{
      return EMPTY_U8_ARRAY, false, -1
    }}

    data = new u8[value.codePointsSize()]

    errorByteIndex = -1
    while nextByteIndex < value.size() {{
      currentByteIndex = nextByteIndex
      codePoint, nextByteIndex = value.getCodePoint(currentByteIndex)
      byteValue, codePageError = _this.utf8ToCodePage.get(codePoint)

      if codePageError {{
        byteValue = '?'

        if errorByteIndex < 0 {{
          errorByteIndex = currentByteIndex
        }}
      }}

      data[outByteIndex] = byteValue
      outByteIndex = outByteIndex + 1
    }}

    return data, errorByteIndex >= 0, errorByteIndex
  }}

  toUtf8(u8[] data) -> str value, bool error, i32 errorIndex {{
    i32 i = 0
    i32 out = 0
    i32 dataLen
    u32 codePoint
    bool codePageError
    u8 b
    u8[] buf

    if not data {{
      return "", false, -1
    }}

    dataLen = (i32) data.size
    if dataLen == 0 {{
      return "", false, -1
    }}

    // Worst case: 4 UTF-8 bytes per code-page byte
    buf = new u8[dataLen * 4 + 1]
    errorIndex = -1

    while i < dataLen {{
      b = data[i]
      codePoint, codePageError = _this.codePageToUtf8.get(b)

      if codePageError {{
        codePoint = 0x3F
        if errorIndex < 0 {{
          errorIndex = i
        }}
      }}

      if codePoint <= 0x7F {{
        buf[out] = (u8) codePoint
        out = out + 1
      }} else {{
        if codePoint <= 0x7FF {{
          buf[out] = (u8) (0xC0 | (codePoint >> 6))
          buf[out + 1] = (u8) (0x80 | (codePoint & 0x3F))
          out = out + 2
        }} else {{
          if codePoint <= 0xFFFF {{
            buf[out] = (u8) (0xE0 | (codePoint >> 12))
            buf[out + 1] = (u8) (0x80 | ((codePoint >> 6) & 0x3F))
            buf[out + 2] = (u8) (0x80 | (codePoint & 0x3F))
            out = out + 3
          }} else {{
            buf[out] = (u8) (0xF0 | (codePoint >> 18))
            buf[out + 1] = (u8) (0x80 | ((codePoint >> 12) & 0x3F))
            buf[out + 2] = (u8) (0x80 | ((codePoint >> 6) & 0x3F))
            buf[out + 3] = (u8) (0x80 | (codePoint & 0x3F))
            out = out + 4
          }}
        }}
      }}

      i = i + 1
    }}

    buf[out] = 0
    value = str.fromPtrBuffer(buf._caffc_data, out)
    return value, errorIndex >= 0, errorIndex
  }}
}}

unit_init() {{
  CodePageEncoding codePage = new CodePage{class_name}()
  codePages.put(codePage.name(), codePage)
}}
}}
"""


def mappings_root(unicode_root: Path) -> Path:
    return unicode_root / "www.unicode.org" / "Public" / "MAPPINGS"


def iana_charset_reg_dir(unicode_root: Path) -> Path:
    return unicode_root / "www.iana.org" / "assignments" / "charset-reg"


def read_text(path: Path) -> str | None:
    if not path.is_file():
        return None
    return path.read_text(encoding="utf-8", errors="replace")


def parse_format_a(text: str) -> list[tuple[int, int]] | None:
    pairs: list[tuple[int, int]] = []
    for line in text.splitlines():
        match = FORMAT_A_LINE.match(line.strip())
        if not match:
            continue
        byte = int(match.group(1), 16)
        code_point = int(match.group(2), 16)
        if byte > 0xFF:
            return None
        pairs.append((byte, code_point))
    return _dedupe_pairs(pairs)


def parse_ibm_style(text: str) -> list[tuple[int, int]] | None:
    pairs: list[tuple[int, int]] = []
    for line in text.splitlines():
        match = IBM_STYLE_LINE.match(line.strip())
        if not match:
            continue
        byte = int(match.group(1), 16)
        code_point = int(match.group(2), 16)
        pairs.append((byte, code_point))
    return _dedupe_pairs(pairs)


def _dedupe_pairs(pairs: list[tuple[int, int]]) -> list[tuple[int, int]] | None:
    if not pairs:
        return None
    by_byte: dict[int, int] = {}
    for byte, code_point in pairs:
        by_byte.setdefault(byte, code_point)
    return sorted(by_byte.items(), key=lambda item: item[0])


def class_name_from_registry(name: str) -> str:
    cleaned = re.sub(r"[^A-Za-z0-9]+", " ", name).strip()
    parts = cleaned.split()
    if not parts:
        return "Unknown"
    return "".join(part[:1].upper() + part[1:] for part in parts)


def render_puts(pairs: list[tuple[int, int]]) -> str:
    lines: list[str] = []
    for byte, code_point in pairs:
        lines.append(
            f"    _this.codePageToUtf8.put(0x{byte:02X}, 0x{code_point:04X})"
        )
        lines.append(
            f"    _this.utf8ToCodePage.put(0x{code_point:04X}, 0x{byte:02X})"
        )
    return "\n".join(lines)


def generate_one(spec: CodePageSpec, pairs: list[tuple[int, int]], out_dir: Path) -> Path:
    file_name = f"CodePage{spec.class_name}.caffc"
    content = TEMPLATE.format(
        vendor_dir=spec.vendor_dir,
        file_name=file_name,
        class_name=spec.class_name,
        registry_name=spec.registry_name,
        vendor_name=spec.vendor_name,
        puts=render_puts(pairs),
    )
    vendor_dir = out_dir / spec.vendor_dir
    vendor_dir.mkdir(parents=True, exist_ok=True)
    out_path = vendor_dir / file_name
    out_path.write_text(content, encoding="utf-8")
    print(
        f"wrote {out_path.relative_to(REPO_ROOT)} "
        f"({len(pairs)} mappings, vendor={spec.vendor_name}, name={spec.registry_name})"
    )
    return out_path


def clean_generated(out_dir: Path) -> None:
    if not out_dir.exists():
        return
    for path in out_dir.rglob("CodePage*.caffc"):
        path.unlink()
    for path in sorted(out_dir.rglob("*"), reverse=True):
        if path.is_dir():
            try:
                path.rmdir()
            except OSError:
                pass


def load_mappings_file(unicode_root: Path, relative: str) -> str | None:
    path = mappings_root(unicode_root) / relative
    return read_text(path)


def build_unicode_org_specs(unicode_root: Path) -> list[CodePageSpec]:
    specs: list[CodePageSpec] = []

    for part in ISO8859_PARTS:
        text = load_mappings_file(unicode_root, f"ISO8859/8859-{part}.TXT")
        if text is None:
            print(f"missing local mapping: ISO8859/8859-{part}.TXT")
            continue
        specs.append(
            CodePageSpec(
                vendor_dir="iso",
                vendor_name="ISO",
                class_name=f"8859_{part}",
                registry_name=f"8859-{part}",
                mapping_text=text,
                source_label=f"ISO8859/8859-{part}.TXT",
            )
        )

    for cp in MICROSOFT_WINDOWS:
        rel = f"VENDORS/MICSFT/WINDOWS/CP{cp}.TXT"
        text = load_mappings_file(unicode_root, rel)
        if text is None:
            print(f"missing local mapping: {rel}")
            continue
        specs.append(
            CodePageSpec(
                vendor_dir="microsoft",
                vendor_name="Microsoft",
                class_name=str(cp),
                registry_name=str(cp),
                mapping_text=text,
                source_label=rel,
            )
        )

    for cp in MICROSOFT_PC:
        rel = f"VENDORS/MICSFT/PC/CP{cp}.TXT"
        text = load_mappings_file(unicode_root, rel)
        if text is None:
            print(f"missing local mapping: {rel}")
            continue
        specs.append(
            CodePageSpec(
                vendor_dir="microsoft",
                vendor_name="Microsoft",
                class_name=str(cp),
                registry_name=str(cp),
                mapping_text=text,
                source_label=rel,
            )
        )

    for apple_name in APPLE_SINGLE_BYTE:
        rel = f"VENDORS/APPLE/{apple_name}.TXT"
        text = load_mappings_file(unicode_root, rel)
        if text is None:
            print(f"missing local mapping: {rel}")
            continue
        class_suffix = "".join(
            part.capitalize()
            for part in apple_name.lower().replace("-", "_").split("_")
        )
        specs.append(
            CodePageSpec(
                vendor_dir="apple",
                vendor_name="Apple",
                class_name=class_suffix,
                registry_name="mac-" + apple_name.lower().replace("_", "-"),
                mapping_text=text,
                source_label=rel,
            )
        )

    for file_name, class_name, registry_name in MISC_TABLES:
        rel = f"VENDORS/MISC/{file_name}"
        text = load_mappings_file(unicode_root, rel)
        if text is None:
            print(f"missing local mapping: {rel}")
            continue
        specs.append(
            CodePageSpec(
                vendor_dir="misc",
                vendor_name="Misc",
                class_name=class_name,
                registry_name=registry_name,
                mapping_text=text,
                source_label=rel,
            )
        )

    rel = "VENDORS/NEXT/NEXTSTEP.TXT"
    text = load_mappings_file(unicode_root, rel)
    if text is not None:
        specs.append(
            CodePageSpec(
                vendor_dir="next",
                vendor_name="NeXT",
                class_name="NextStep",
                registry_name="nextstep",
                mapping_text=text,
                source_label=rel,
            )
        )
    else:
        print(f"missing local mapping: {rel}")

    return specs


def extract_iana_charset_name(text: str, fallback: str) -> str:
    match = CHARSET_NAME.search(text)
    if not match:
        return fallback
    name = match.group(1).strip()
    # Some regs put prose on the same line; keep the first token-ish name.
    name = re.split(r"\s{2,}|\t", name)[0].strip()
    if " " in name and not name.lower().startswith("iso-"):
        # e.g. tis-620 prose leak
        name = name.split()[0].strip()
    return name or fallback


def resolve_iana_mapping_text(unicode_root: Path, reg_path: Path, text: str, charset: str) -> tuple[str, str] | None:
    """Return (mapping_text, source_label) for a single-byte IANA registration."""
    pairs = parse_format_a(text)
    if pairs is not None and len(pairs) >= 32:
        return text, f"iana://charset-reg/{reg_path.name}#format-a"

    ibm_pairs = parse_ibm_style(text)
    if ibm_pairs is not None and len(ibm_pairs) >= 32:
        return text, f"iana://charset-reg/{reg_path.name}#ibm-style"

    urls = MAPPINGS_URL.findall(text)
    for relative in urls:
        relative = relative.rstrip(").,;")
        mapping = load_mappings_file(unicode_root, relative)
        if mapping is not None:
            return mapping, relative

    implied = IANA_IMPLIED_MAPPINGS.get(charset) or IANA_IMPLIED_MAPPINGS.get(charset.lower())
    if implied:
        mapping = load_mappings_file(unicode_root, implied)
        if mapping is not None:
            return mapping, implied

    return None


def build_iana_specs(unicode_root: Path) -> list[CodePageSpec]:
    reg_dir = iana_charset_reg_dir(unicode_root)
    if not reg_dir.is_dir():
        print(f"missing IANA charset-reg dir: {reg_dir}")
        return []

    specs: list[CodePageSpec] = []
    for path in sorted(reg_dir.iterdir()):
        if not path.is_file() or path.name in IANA_SKIP_NAMES:
            continue
        text = read_text(path)
        if text is None:
            continue

        charset = extract_iana_charset_name(text, path.name)
        resolved = resolve_iana_mapping_text(unicode_root, path, text, charset)
        if resolved is None:
            print(f"skip iana (no single-byte table): {path.name} ({charset})")
            continue

        mapping_text, source_label = resolved
        # Prefer format-A parse; fall back to IBM-style for embedded IBM tables.
        pairs = parse_format_a(mapping_text)
        if pairs is None:
            pairs = parse_ibm_style(mapping_text)
        if pairs is None:
            print(f"skip iana (multi-byte or empty): {path.name} ({charset})")
            continue

        specs.append(
            CodePageSpec(
                vendor_dir="iana",
                vendor_name="IANA",
                class_name=class_name_from_registry(charset),
                registry_name=charset,
                mapping_text=mapping_text,
                source_label=source_label,
            )
        )
        # pairs validated; mapping_text kept for generate pass
        _ = pairs

    return specs


def emit_specs(specs: list[CodePageSpec], out_dir: Path) -> tuple[int, int]:
    written = 0
    skipped = 0
    for spec in specs:
        pairs = parse_format_a(spec.mapping_text)
        if pairs is None:
            pairs = parse_ibm_style(spec.mapping_text)
        if pairs is None:
            print(f"skip multi-byte or empty: {spec.source_label}")
            skipped += 1
            continue
        generate_one(spec, pairs, out_dir)
        written += 1
    return written, skipped


def main() -> int:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument(
        "--unicode-root",
        type=Path,
        default=DEFAULT_UNICODE_ROOT,
        help="Local mirror containing www.unicode.org and www.iana.org",
    )
    parser.add_argument(
        "--out-dir",
        type=Path,
        default=DEFAULT_OUT,
        help="Output root for generated/<vendor>/CodePage*.caffc",
    )
    args = parser.parse_args()

    if not args.unicode_root.is_dir():
        raise SystemExit(f"unicode root not found: {args.unicode_root}")

    args.out_dir.mkdir(parents=True, exist_ok=True)
    clean_generated(args.out_dir)

    unicode_specs = build_unicode_org_specs(args.unicode_root)
    iana_specs = build_iana_specs(args.unicode_root)

    written, skipped = emit_specs(unicode_specs + iana_specs, args.out_dir)
    print(f"done: wrote {written}, skipped {skipped}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
