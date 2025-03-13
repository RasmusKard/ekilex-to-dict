package com.eki.dict.classes;

import java.util.List;

public class CollocationPosGroup extends AbstractDataObject {

	private static final long serialVersionUID = 1L;

	private String code;

	private List<CollocationRelGroup> relationGroups;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<CollocationRelGroup> getRelationGroups() {
		return relationGroups;
	}

	public void setRelationGroups(List<CollocationRelGroup> relationGroups) {
		this.relationGroups = relationGroups;
	}

}
