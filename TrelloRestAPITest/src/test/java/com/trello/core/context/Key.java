package com.trello.core.context;

/**
 * Class to describe unique complex key of the terllo entity to store in Context
 * @author vskirko
 */
public class Key {

	private String id;
	private String name;
	private EntityType type;

	public Key(String id, String name, EntityType type) {
		this.id = id;
		this.name = name;
		this.type = type;
	}
	
	public Key(String id) {
		this.id = id;
	}

	public Key(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public EntityType getType() {
		return type;
	}
}
