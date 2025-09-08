package com.germaniumhq.caffc.compiler.model;

import java.util.LinkedHashMap;
import java.util.Map;

public class Tags {
    public Map<String, Tag> tags = new LinkedHashMap<>();

    public void put(String name, Tag tag) {
        tags.put(name, tag);
    }

    public boolean has(String name) {
        return tags.containsKey(name);
    }

    public Tag get(String name) { return tags.get(name); }
}
