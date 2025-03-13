package com.converter.classes;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class DefinitionBlock {
    @JacksonXmlProperty(localName = "gr")
    private String wordType;

    @JacksonXmlProperty(localName = "deftext")
    private String meaning;

    @JacksonXmlElementWrapper(localName = "ex")
    @JacksonXmlProperty(localName = "ex_orig")
    private List<String> usages;

    public List<String> getUsages() {
        return usages;
    }

    public void setUsages(List<String> usages) {
        this.usages = usages;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getWordType() {
        return wordType;
    }

    public void setWordType(String wordType) {
        this.wordType = wordType;
    }
}

// main level word
// wordValue
// paradigms
//

// article
// wordtype
// list of definition and example blocks (lexemes)
