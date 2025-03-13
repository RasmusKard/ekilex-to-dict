package com.eki.dict.classes;

public class AbstractRelation extends AbstractDataObject implements DecoratedWordType {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long lexemeId;

	private Long meaningId;

	private Long wordId;

	private String wordValue;

	private String wordValuePrese;

	private String wordLang;

	private String wordAspectCode;

	private String[] wordTypeCodes;

	private boolean prefixoid;

	private boolean suffixoid;

	private boolean foreign;

	private Integer wordHomonymNr;

	private boolean homonymsExist;

	private String relTypeCode;

	private String relTypeLabel;

	private Long orderBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getLexemeId() {
		return lexemeId;
	}

	public void setLexemeId(Long lexemeId) {
		this.lexemeId = lexemeId;
	}

	public Long getMeaningId() {
		return meaningId;
	}

	public void setMeaningId(Long meaningId) {
		this.meaningId = meaningId;
	}

	public Long getWordId() {
		return wordId;
	}

	public void setWordId(Long wordId) {
		this.wordId = wordId;
	}

	@Override
	public String getWordValue() {
		return wordValue;
	}

	@Override
	public void setWordValue(String wordValue) {
		this.wordValue = wordValue;
	}

	@Override
	public String getWordValuePrese() {
		return wordValuePrese;
	}

	@Override
	public void setWordValuePrese(String wordValuePrese) {
		this.wordValuePrese = wordValuePrese;
	}

	public String getWordLang() {
		return wordLang;
	}

	public void setWordLang(String wordLang) {
		this.wordLang = wordLang;
	}

	public String getWordAspectCode() {
		return wordAspectCode;
	}

	public void setWordAspectCode(String wordAspectCode) {
		this.wordAspectCode = wordAspectCode;
	}

	@Override
	public String[] getWordTypeCodes() {
		return wordTypeCodes;
	}

	@Override
	public void setWordTypeCodes(String[] wordTypeCodes) {
		this.wordTypeCodes = wordTypeCodes;
	}

	@Override
	public boolean isPrefixoid() {
		return prefixoid;
	}

	@Override
	public void setPrefixoid(boolean prefixoid) {
		this.prefixoid = prefixoid;
	}

	@Override
	public boolean isSuffixoid() {
		return suffixoid;
	}

	@Override
	public void setSuffixoid(boolean suffixoid) {
		this.suffixoid = suffixoid;
	}

	@Override
	public boolean isForeign() {
		return foreign;
	}

	@Override
	public void setForeign(boolean foreign) {
		this.foreign = foreign;
	}

	public Integer getWordHomonymNr() {
		return wordHomonymNr;
	}

	public void setWordHomonymNr(Integer wordHomonymNr) {
		this.wordHomonymNr = wordHomonymNr;
	}

	public boolean isHomonymsExist() {
		return homonymsExist;
	}

	public void setHomonymsExist(boolean homonymsExist) {
		this.homonymsExist = homonymsExist;
	}

	public String getRelTypeCode() {
		return relTypeCode;
	}

	public void setRelTypeCode(String relTypeCode) {
		this.relTypeCode = relTypeCode;
	}

	public String getRelTypeLabel() {
		return relTypeLabel;
	}

	public void setRelTypeLabel(String relTypeLabel) {
		this.relTypeLabel = relTypeLabel;
	}

	public Long getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}
}
