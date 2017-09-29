package com.trello.steps.manager;

import java.util.ArrayList;
import java.util.List;

import com.trello.core.Parameter;
import com.trello.core.context.Context;
import com.trello.core.context.EntityType;
import com.trello.core.context.Key;
import com.trello.domain.entity.Board;
import com.trello.domain.entity.Card;
import com.trello.domain.entity.CheckList;
import com.trello.domain.entity.TList;
import com.trello.exception.TrelloHttpException;

public class BoardStepManager extends TrelloStepManager {

	public BoardStepManager(Context context) {
		super(context);
	}

	// POST
	public Board createNewBoard(String boardName) {
		Board board = null;
		try {
			board = getContext().getTrelloAPI().createBoard(boardName);
			getContext().put(new Key(board.getId(), board.getName(), EntityType.BOARD), board);
		} catch (TrelloHttpException ex) {

		}
		return board;
	}

	public TList createNewBoardList(String tListName, String boardID) {
		TList tList = null;
		try {
			tList = getContext().getTrelloAPI().createTList(tListName, boardID);
			getContext().put(new Key(tList.getId(), tList.getName(), EntityType.LIST), tList);
		} catch (TrelloHttpException ex) {

		}
		return tList;
	}

	// GET
	public Board getBoardByID(String boardID) {
		getContext().clear();
		Board board = null;
		try {
			board = getContext().getTrelloAPI().getBoard(boardID);
			getContext().put(new Key(board.getId(), board.getName(), EntityType.BOARD), board);
		} catch (TrelloHttpException ex) {

		}
		return board;
	}

	public List<TList> obtainBoardLists(String boardID, Parameter... args) {
		getContext().clear();
		Board board = getContext().getTrelloAPI().getBoard(boardID);
		List<TList> tLists = board.obtainTLists(args);// differ from getBoardLists
		for (TList tList : tLists) {
			getContext().put(new Key(tList.getId(), tList.getName(), EntityType.LIST), tList);
		}
		return tLists;
	}

	public List<CheckList> obtainBoardCheckLists(String boardID, Parameter... args) {
		getContext().clear();
		Board board = getContext().getTrelloAPI().getBoard(boardID);
		List<CheckList> cLists = board.obtainCheckLists(args);
		for (CheckList cList : cLists) {
			getContext().put(new Key(cList.getId(), cList.getName(), EntityType.CHECK_LIST), cList);
		}
		return cLists;
	}

	public List<Card> obtainBoardCards(String boardID, Parameter... args) {
		getContext().clear();
		Board board = getContext().getTrelloAPI().getBoard(boardID);
		List<Card> cards = board.obtainCards(args);
		for (Card card : cards) {
			getContext().put(new Key(card.getId(), card.getName(), EntityType.CARD), card);
		}
		return cards;
	}
	

	public Card obtainBoardCard(String boardID, String cardID, Parameter... args) {
		getContext().clear();
		Board board = getContext().getTrelloAPI().getBoard(boardID);
		Card card = null;
		try {
			card = board.obtainCard(cardID, args);
			getContext().put(new Key(card.getId(), card.getName(), EntityType.CARD), card);
		} catch (TrelloHttpException ex) {
			
		}
		return card;
	}

	public List<TList> getBoardLists(String boardID, Parameter... args) {
		getContext().clear();
		List<TList> tLists = getContext().getTrelloAPI().getBoardLists(boardID, args);
		for (TList tList : tLists) {
			getContext().put(new Key(tList.getId(), tList.getName(), EntityType.LIST), tList);
		}
		return tLists;
	}
	
	public void deleteAllBoard() {
		List<Board> boardsToDelete = getBoardsFromContext();
		for(Board board : boardsToDelete) {
			board.setClosed(true);
			board = getContext().getTrelloAPI().updateBoard(board);
			getContext().getTrelloAPI().deleteBoard(board.getId());
		}
	}

	// CONTEXT
	// TODO Create separate class to direct interaction with Context data
	public List<Card> getBoardListCardsFromContext(TList boardList) {
		getContext().clear();
		List<Card> listCards = boardList.getCards();
		for (Card card : listCards) {
			getContext().put(new Key(card.getId(), card.getName(), EntityType.CARD), card);
		}
		return listCards;
	}

	public TList getTListByCriteriaFromContext(String parameterName,  String parameterValue) {
		TList boardListToCheck = null;
		try {
			if ("Name".equalsIgnoreCase(parameterName)) {
				boardListToCheck = (TList) getContext().getAllEntities().entrySet().stream()
						.filter(e -> parameterValue.equals(e.getKey().getName())).findFirst().get().getValue();
			} else if ("Id".equalsIgnoreCase(parameterName)) {
				boardListToCheck = (TList) getContext().getAllEntities().entrySet().stream()
						.filter(e -> parameterValue.equals(e.getKey().getId())).findFirst().get().getValue();
			}
		} catch (Exception e) {

		}
		return boardListToCheck;
	}

	public List<Board> getBoardsFromContext() {
		List<Board> boards = new ArrayList<>();
		getContext().getAllEntities().entrySet().stream().filter(e -> EntityType.BOARD.equals(e.getKey().getType()))
				.forEach(e -> boards.add((Board)e.getValue()));//filter all entities by type = BOARD
		return boards;
	}
}
