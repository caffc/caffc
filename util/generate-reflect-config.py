import click
import os
import zipfile
import json

# run as:
# python util/generate-reflect-config.py --jar target/caffc-1.0.0-SNAPSHOT.jar

@click.command()
@click.option("--jar-file", "--jar",
              help="Jar file to scan the classes from")
@click.option("--out-file", "--out",
              help="Output file to write",
              default="src/main/resources/META-INF/native-image/reflect-config.json")
def main(jar_file: str, out_file: str) -> None:
    class_list: list[str] = [
        "java.util.List",
        "java.util.Set",
        "java.util.Map",
        "sun.security.provider.SHA2$SHA256",
    ]

    with zipfile.ZipFile(jar_file, 'r') as zip_ref:
        for file_info in zip_ref.infolist():
            if file_info.is_dir():
                continue

            if not file_info.filename.endswith(".class"):
                continue

            class_name = file_info.filename[:-len(".class")]
            class_name = class_name.replace('/', '.')
            class_list.append(class_name)

    classes = [ {
        "name": it,
        "allDeclaredConstructors": True,
        "allDeclaredMethods": True,
        "allDeclaredFields": True,
    } for it in class_list ]

    with open(out_file, 'wt', encoding='utf-8') as f:
        json.dump(classes, f, indent=2)


if __name__ == "__main__":
    main()

