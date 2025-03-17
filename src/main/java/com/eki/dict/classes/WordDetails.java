package com.eki.dict.classes;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

@JsonIncludeProperties({ "word", "lexemes" })
public class WordDetails extends AbstractDataObject {

	private static final long serialVersionUID = 1L;

	private Word word;

	private List<WordLexeme> lexemes;

	private WordRelationDetails wordRelationDetails;

	private String firstDefinitionValue;

	private boolean activeTagComplete;

	public WordDetails() {
	}

	public Word getWord() {
		return word;
	}

	public void setWord(Word word) {
		this.word = word;
	}

	public List<WordLexeme> getLexemes() {
		return lexemes;
	}

	public void setLexemes(List<WordLexeme> lexemes) {
		this.lexemes = lexemes;
	}

	public WordRelationDetails getWordRelationDetails() {
		return wordRelationDetails;
	}

	public void setWordRelationDetails(WordRelationDetails wordRelationDetails) {
		this.wordRelationDetails = wordRelationDetails;
	}

	public String getFirstDefinitionValue() {
		return firstDefinitionValue;
	}

	public void setFirstDefinitionValue(String firstDefinitionValue) {
		this.firstDefinitionValue = firstDefinitionValue;
	}

	public boolean isActiveTagComplete() {
		return activeTagComplete;
	}

	public void setActiveTagComplete(boolean activeTagComplete) {
		this.activeTagComplete = activeTagComplete;
	}
}
