package com.eki.dict.classes;

import java.util.List;

public class SynRelation extends AbstractRelation {

	private static final long serialVersionUID = 1L;

	private List<String> wordLexemesPoses;

	private RelationStatus relationStatus;

	private RelationStatus oppositeRelationStatus;

	private List<TypeWordRelParam> relationParams;

	private List<TypeWordRelMeaning> wordMeanings;

	private int sameValuePublicWordCount;

	public List<String> getWordLexemesPoses() {
		return wordLexemesPoses;
	}

	public void setWordLexemesPoses(List<String> wordLexemesPoses) {
		this.wordLexemesPoses = wordLexemesPoses;
	}

	public RelationStatus getRelationStatus() {
		return relationStatus;
	}

	public void setRelationStatus(RelationStatus relationStatus) {
		this.relationStatus = relationStatus;
	}

	public RelationStatus getOppositeRelationStatus() {
		return oppositeRelationStatus;
	}

	public void setOppositeRelationStatus(RelationStatus oppositeRelationStatus) {
		this.oppositeRelationStatus = oppositeRelationStatus;
	}

	public List<TypeWordRelParam> getRelationParams() {
		return relationParams;
	}

	public void setRelationParams(List<TypeWordRelParam> relationParams) {
		this.relationParams = relationParams;
	}

	public List<TypeWordRelMeaning> getWordMeanings() {
		return wordMeanings;
	}

	public void setWordMeanings(List<TypeWordRelMeaning> wordMeanings) {
		this.wordMeanings = wordMeanings;
	}

	public int getSameValuePublicWordCount() {
		return sameValuePublicWordCount;
	}

	public void setSameValuePublicWordCount(int sameValuePublicWordCount) {
		this.sameValuePublicWordCount = sameValuePublicWordCount;
	}
}
