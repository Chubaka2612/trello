package com.trello.core.context;

import java.util.HashMap;
import java.util.Map;

import com.trello.core.impl.TrelloAPIImpl;
import com.trello.domain.entity.TrelloEntity;


/***
 * This class is responsible for storing Content to share between StepDefinition
 * instance.
 */
public class Context {

	private HashMap<Key, TrelloEntity> scenarioEntities = new HashMap<>();
	private TrelloAPIImpl trelloAPI;

	public void put(Key key, TrelloEntity entity) {
		scenarioEntities.put(key, entity);
	}

	public TrelloEntity get(Key key) {
		for (Map.Entry<Key, TrelloEntity> entry : scenarioEntities.entrySet()) {
			if(key.getId().isEmpty()) {
				//search by name
				// id: "", type: null, name: value
				if(key.getType() == null) {
					if(entry.getKey().getName().equals(key.getName())) {
						return entry.getValue();
					}
				} else {
					//search by name and type, as name for different entity can be the same
					// id: "", type: value, name: value
					if(entry.getKey().getName().equals(key.getName()) && entry.getKey().getType().equals(key.getType()) ) {
						return entry.getValue();
					}
				}
			} 
			//search by ID
			if(entry.getKey().getId().equals(key.getId())) {
				return entry.getValue();
			}
		}
		return null;
	}

	public void putTrelloAPI(TrelloAPIImpl trello) {
		trelloAPI = trello;
	}

	public TrelloAPIImpl getTrelloAPI() {
		return trelloAPI;
	}

	public void clear() {
		scenarioEntities.clear();
	}

	public int getSize() {
		return scenarioEntities.size();
	}

	public HashMap<Key, TrelloEntity> getAllEntities() {
		return scenarioEntities;
	}
}
