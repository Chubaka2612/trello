package com.trello.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.trello.core.TrelloAPI;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class TrelloEntity {

	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	protected TrelloAPI trelloService;

	public void setInternalTrello(TrelloAPI trelloService) {
		this.trelloService = trelloService;
	}

}
