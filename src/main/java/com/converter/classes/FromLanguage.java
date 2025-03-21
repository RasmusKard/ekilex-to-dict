package com.converter.classes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class FromLanguage {

    @JacksonXmlProperty(isAttribute = true, localName = "xml:lang")
    private String lang;

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }
}
