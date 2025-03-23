package com.converter.classes;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

@JsonPropertyOrder({ "keyPhrases", "definitionBlocks" })
@JacksonXmlRootElement(localName = "ar")
public class Article {

    @JacksonXmlElementWrapper(localName = "def")
    @JacksonXmlProperty(localName = "def")
    private List<DefinitionBlock> definitionBlocks;

    @JsonIgnore
    private String wordValue;
    @JsonIgnore
    private Set<String> forms;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "k")
    public Set<String> getKeyPhrases() {
        // handle mainWord always being the first element
        // this is done due to XDXF format semantics
        LinkedHashSet<String> forms = new LinkedHashSet<String>(this.forms);

        if (forms.contains(this.wordValue)) {
            forms.remove(this.wordValue);
        }
        forms.addFirst(this.wordValue);

        // clean faulty data of word forms
        List<String> elementsToRemove = new ArrayList<String>(Arrays.asList("", "-"));
        forms.removeAll(elementsToRemove);

        return forms;
    }

    public String getWordValue() {
        return wordValue;
    }

    public void setWordValue(String wordValue) {
        this.wordValue = wordValue;
    }

    public Set<String> getForms() {
        return forms;
    }

    public void setForms(Set<String> forms) {
        this.forms = forms;
    }

    public List<DefinitionBlock> getDefinitionBlocks() {
        return definitionBlocks;
    }

    public void setDefinitionBlocks(List<DefinitionBlock> definitionBlocks) {
        this.definitionBlocks = definitionBlocks;
    }

}
