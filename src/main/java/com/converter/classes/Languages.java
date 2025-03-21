package com.converter.classes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Languages {

    @JacksonXmlProperty(localName = "from")
    private FromLanguage fromLanguage;

    @JacksonXmlProperty(localName = "to")
    private ToLanguage toLanguage;

    public FromLanguage getFromLanguage() {
        return fromLanguage;
    }

    public void setFromLanguage(FromLanguage fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    public ToLanguage getToLanguage() {
        return toLanguage;
    }

    public void setToLanguage(ToLanguage toLanguage) {
        this.toLanguage = toLanguage;
    }

}
