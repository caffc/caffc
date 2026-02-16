package com.germaniumhq.caffc.compiler.model;

import java.util.Objects;

public class StringConstant {
    public String name;
    public String value;
    public byte[] bytes;
    public int bytesSize;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StringConstant that = (StringConstant) o;
        return Objects.equals(bytes, that.bytes);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bytes);
    }

    @UsedInTemplate("caffc/template/c/constants_c.peb")
    public String valueAsBytes() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            int unsignedByte = bytes[i] & 0xFF;
            String hexString = Integer.toHexString(unsignedByte);
            result.append("0x").append(hexString.length() == 1 ? "0" + hexString : hexString);
            result.append(", ");
        }

        result.append("0x00");

        return result.toString();
    }
}
