package com.germaniumhq.caffc.compiler.model;

import java.util.Objects;

public class StringConstant {
    public String name;
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

    public String valueAsBytes() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < bytes.length; i++) {
            int unsignedByte = bytes[i] & 0xFF;
            result.append("0x").append(Integer.toHexString(unsignedByte));
            result.append(", ");
        }

        result.append("0x00");

        return result.toString();
    }
}
