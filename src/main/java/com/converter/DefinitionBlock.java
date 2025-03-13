package com.converter;

import java.util.List;

public class DefinitionBlock {
    private String wordType;

    private String meaning;

    private List<String> examples;

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
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
