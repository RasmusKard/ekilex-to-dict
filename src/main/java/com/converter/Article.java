package com.converter;

import java.util.HashSet;
import java.util.List;

public class Article {

    private List<DefinitionBlock> definitionBlocks;

    private String wordValue;

    private HashSet<String> paradigms;

    public HashSet<String> getParadigms() {
        return paradigms;
    }

    public void setParadigms(HashSet<String> paradigms) {
        this.paradigms = paradigms;
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
