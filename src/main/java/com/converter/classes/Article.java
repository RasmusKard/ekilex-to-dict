package com.converter.classes;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JsonPropertyOrder({ "wordValue", "forms", "definitionBlocks" })
@JacksonXmlRootElement(localName = "ar")
public class Article {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "def")
    private List<DefinitionBlock> definitionBlocks;

    // @JacksonXmlProperty(localName = "k")
    private String wordValue;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "k")
    private Set<String> forms;

    public Set<String> getForms() {
        return forms;
    }

    public void setForms(Set<String> forms) {
        this.forms = forms;
    }

    public String getWordValue() {
        return wordValue;
    }

    public void setWordValue(String wordValue) {
        this.wordValue = wordValue;
    }

    public List<DefinitionBlock> getDefinitionBlocks() {
        return definitionBlocks;
    }

    public void setDefinitionBlocks(List<DefinitionBlock> definitionBlocks) {
        this.definitionBlocks = definitionBlocks;
    }

    // list of def blocks
    // wordvalue
    // list of paradigms

}
