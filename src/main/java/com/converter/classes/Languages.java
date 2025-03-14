package com.converter.classes;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Languages {

    @JacksonXmlElementWrapper(localName = "from", useWrapping = false)
    @JacksonXmlProperty(localName = "lang")
    private List<String> fromLanguages;

    @JacksonXmlElementWrapper(localName = "to", useWrapping = false)
    @JacksonXmlProperty(localName = "lang")
    private List<String> toLanguages;

    public List<String> getFromLanguages() {
        return fromLanguages;
    }

    public void setFromLanguages(List<String> fromLanguages) {
        this.fromLanguages = fromLanguages;
    }

    public List<String> getToLanguages() {
        return toLanguages;
    }

    public void setToLanguages(List<String> toLanguages) {
        this.toLanguages = toLanguages;
    }

}
