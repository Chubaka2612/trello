package com.trello.core.cucumber;

import java.util.List;

import org.junit.Assert;

import com.trello.core.Parameter;
import com.trello.domain.entity.Card;
import com.trello.domain.entity.TList;
import com.trello.steps.manager.BoardStepManager;

import cucumber.api.java.After;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BoardStepDefinition {

	private BoardStepManager boardSteps;

	public BoardStepDefinition(BoardStepManager boardSteps) {
		this.boardSteps = boardSteps;
	}

	@When("^I get board by (?i)ID (.*?)$")
	public void shouldGetBoardByID(String boardID) {
		boardSteps.getBoardByID(boardID);
	}

	@When("^I get cards of board with (?i)ID (.*?)$")
	public void shouldObtainCardsOfBoard(String boardID) {
		boardSteps.obtainBoardCards(boardID);
	}

	@When("^I get card by (?i)ID (.*?) of board with (?i)ID (.*?)$")
	public void shouldObtainCardOfBoard(String cardID, String boardID) {
		boardSteps.obtainBoardCard(boardID, cardID);
	}

	@When("^I get lists of board with (?i)ID (.*?)$")
	public void shouldObtainListsOfBoard(String boardID) {
		boardSteps.obtainBoardLists(boardID);
	}

	@When("^I get checklists of board with (?i)ID (.*?)$")
	public void shouldObtainCheckListsOfBoard(String boardID) {
		boardSteps.obtainBoardCheckLists(boardID);
	}

	@When("^I get lists with filter = (none|all|open|closed) of board with (?i)ID (.*?)$")
	public void shouldGetListsOfBoardByFilter(String filter, String boardID) {
		boardSteps.getBoardLists(boardID, new Parameter("filter", filter));
	}

	@When("^I get cards with filter = (none|all|open|closed|visible) of board with (?i)ID (.*?)$")
	public void shouldGetCardsOfBoardByFilter(String filter, String boardID) {
		boardSteps.obtainBoardCards(boardID, new Parameter("filter", filter));
	}

	@When("^I get lists by fields = (name|closed) of (?:the|a) board with (?i)ID (.*?)$")
	public void shouldGetListsOfBoardByField(String fieldName, String boardID) {
		boardSteps.getBoardLists(boardID, new Parameter("fields", fieldName));
	}

	@When("^I get lists by cards = (all|closed|none|open|visible) of (?:the|a) board with (?i)ID (.*?)$")
	public void shouldGetListsOfBoardByCards(String cardsName, String boardID) {
		boardSteps.getBoardLists(boardID, new Parameter("cards", cardsName));
	}

	@When("^I create new board with name (.*?)$")
	public void createNewBoard(String boardName) {
		boardSteps.createNewBoard(boardName);
	}
	
	@When("^I create new list with name (.*?) on board with (?i)ID (.*?)$")
	public void createNewBoardList(String listName, String boardID) {
		boardSteps.createNewBoardList(listName, boardID);
	}

	@Then("^I should see board has (\\d+) (lists$|cards$|checklists$)")
	public void shouldSeeBoardHasEntitiesOfRequiredAmount(int expectedAmount, String type) {
		Assert.assertEquals(String.format("The amount of the %s in the board is not as expected.", type),
				expectedAmount, boardSteps.getContext().getSize());
	}
	
	@Then("^I should see list with (?i)(ID|name) (.*?) has (\\d+) cards$")
	public void shouldSeeListHasCardsWithFilterOfRequiredAmount(String parameterName, String parameterValue, int expectedAmount) {
		TList boardListToCheck = boardSteps.getTListByCriteriaFromContext(parameterName, parameterValue);
		List<Card> cards = boardSteps.getBoardListCardsFromContext(boardListToCheck);
		Assert.assertEquals(String.format("The amount of the cards in list %s is not as expected.", parameterValue),
				expectedAmount, cards.size());
	}
	
	@After("@RemoveBoard")
	public void removeBoard() {
		boardSteps.deleteAllBoard();
	}
	
}
