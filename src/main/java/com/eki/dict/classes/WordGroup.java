package com.eki.dict.classes;

import java.util.List;

public class WordGroup extends AbstractDataObject {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String groupTypeCode;

	private String groupTypeLabel;

	private List<WordRelation> members;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupTypeCode() {
		return groupTypeCode;
	}

	public void setGroupTypeCode(String groupTypeCode) {
		this.groupTypeCode = groupTypeCode;
	}

	public String getGroupTypeLabel() {
		return groupTypeLabel;
	}

	public void setGroupTypeLabel(String groupTypeLabel) {
		this.groupTypeLabel = groupTypeLabel;
	}

	public List<WordRelation> getMembers() {
		return members;
	}

	public void setMembers(List<WordRelation> members) {
		this.members = members;
	}

}
