package com.converter.classes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ToLanguage {

    @JacksonXmlProperty(isAttribute = true, localName = "xml:lang")
    private String lang;

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

}
