package com.eki.dict.classes;

import java.util.List;

public class LexemeLangGroup extends LangGroup {

	private static final long serialVersionUID = 1L;

	private List<Lexeme> lexemes;

	public List<Lexeme> getLexemes() {
		return lexemes;
	}

	public void setLexemes(List<Lexeme> lexemes) {
		this.lexemes = lexemes;
	}

}
