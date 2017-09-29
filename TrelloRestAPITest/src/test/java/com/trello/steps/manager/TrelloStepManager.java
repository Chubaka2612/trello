package com.trello.steps.manager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.trello.core.context.Context;
import com.trello.core.cucumber.ConfigProperties;
import com.trello.core.impl.RestTemplateHttpClient;
import com.trello.core.impl.TrelloAPIImpl;
import com.trello.domain.entity.Board;
import com.trello.domain.entity.TrelloEntity;

public abstract class TrelloStepManager {

	private Context context;

	public TrelloStepManager(Context context) {
		this.context = context;
	}

	public Context getContext() {
		return context;
	}

	public void tokenize() {
		ConfigProperties configProperties = new ConfigProperties();
		context.putTrelloAPI(new TrelloAPIImpl(configProperties.getAppKey(), configProperties.getAccessToken(),
				new RestTemplateHttpClient()));
	}
	
	public void checkPresence(TrelloEntity entity, String status) {
		boolean isPresent;
		if (status.equals("present")) {
			isPresent = true;
		} else {
			isPresent = false;
		}
		Assert.assertEquals("The presence of the trelloEntity is not as expected.", entity == null, !isPresent);
	}
	
	public List<TrelloEntity> getTrelloEntitiesByNameFromContext(String name) {
		List<TrelloEntity> filteredEntities = new ArrayList<>();
		getContext().getAllEntities().entrySet().stream().filter(e -> name.equals(e.getKey().getName()))
				.forEach(e -> filteredEntities.add((Board)e.getValue()));
		return filteredEntities;
	}
}
