package com.germaniumhq.caffc.compiler.model;

import java.util.Objects;

public class StringConstant {
    public String name;
    public String value;
    public int bytesSize;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StringConstant that = (StringConstant) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
