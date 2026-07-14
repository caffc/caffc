package com.germaniumhq.caffc.compiler.settings.string;

import com.germaniumhq.caffc.compiler.settings.CaffcFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StringSettings implements CaffcFeature {
    private String locale;
    private List<String> localeList = new ArrayList<>();

    @Override
    public String implName() {
        return "default";
    }

    @Override
    public void readFrom(Map<String, Object> config) {
        if (config.containsKey("locale")) {
            locale = config.get("locale").toString();
        }
        if (config.containsKey("locale_list")) {
            Object localeList = config.get("locale_list");
            if (localeList instanceof List) {
                @SuppressWarnings("unchecked")
                List<String> list = (List<String>) localeList;
                this.localeList = list;
            }
        }
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public List<String> getLocaleList() {
        return localeList;
    }

    public void setLocaleList(List<String> localeList) {
        this.localeList = localeList;
    }
}
