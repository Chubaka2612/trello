package com.trello.core.cucumber;

import org.junit.Assert;

import com.trello.core.context.Key;
import com.trello.domain.entity.TrelloEntity;
import com.trello.steps.manager.TrelloStepManager;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;

public class AbstractStepDefinition {

	TrelloStepManager trelloStepsManager;

	public AbstractStepDefinition(TrelloStepManager trelloStepsManager) {
		this.trelloStepsManager = trelloStepsManager;
	}

	@Before
	public void setUp() {
		trelloStepsManager.tokenize();
	}	

	@Then("^I should see name of (?:the|a) (?:board|card|list|checklist) with (?i)id (.*?) is (.*?)$")
	public void shouldSeeEntityWithName(String entityID, String entityName) {
		TrelloEntity trelloEntity = trelloStepsManager.getContext().get(new Key(entityID));
		Assert.assertEquals("Trello Entity doesn't have the expected name.", entityName, trelloEntity.getName());
	}
	
	@Then("^I should see (?:board|card|list|checklist) with (?i)id (.*?) is (present$|absent$)")
	public void shouldSeeEntityWithIDPresence(String entityID, String status) {
		TrelloEntity trelloEntity = trelloStepsManager.getContext().get(new Key(entityID, ""));
		trelloStepsManager.checkPresence(trelloEntity, status);
	}
	
	@Then("^I should see (?:board|card|list|checklist) with (?i)name (.*?) is (present$|absent$)")
	public void shouldSeeEntityWithNamePresence(String entityName, String status) {
		TrelloEntity trelloEntity = trelloStepsManager.getContext().get(new Key("", entityName));
		trelloStepsManager.checkPresence(trelloEntity, status);
	}
	
	@Then("^I should see there are (\\d+) (?:boards|cards|lists|checklists) with (?i)name (.*?)$")
	public void shouldSeeEntitiesWithNameOfRequiredAmount(int amount, String name) {
		Assert.assertEquals(String.format("Amount of entities with name %s is not as expected.", name), amount, trelloStepsManager.getTrelloEntitiesByNameFromContext(name).size());
	}
	
	@After (order = 500)
	public void tearDown() {
		trelloStepsManager.getContext().clear();
	}
	
}
