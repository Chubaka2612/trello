package com.trello.core.impl;

import static com.trello.core.impl.TrelloUrl.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.trello.core.Parameter;
import com.trello.core.TrelloAPI;
import com.trello.core.TrelloHttpClient;
import com.trello.domain.entity.Board;
import com.trello.domain.entity.Card;
import com.trello.domain.entity.CheckList;
import com.trello.domain.entity.TList;

public class TrelloAPIImpl implements TrelloAPI {

	private TrelloHttpClient httpClient;
	private String applicationKey;
	private String accessToken;
	private static Logger logger = LoggerFactory.getLogger(TrelloAPIImpl.class);

	public TrelloAPIImpl(String applicationKey, String accessToken, TrelloHttpClient httpClient) {
		this.applicationKey = applicationKey;
		this.accessToken = accessToken;
		this.httpClient = httpClient;
	}

	// BOARD SERVICE
	@Override
	public Board getBoard(String boardID, Parameter... args) {
		Board createdBoard = get(createUrl(GET_BOARD).params(args).asString(), Board.class, boardID);
		createdBoard.setInternalTrello(this);
		for (TList list : createdBoard.getTLists()) {
			list.setInternalTrello(this);
		}
		return createdBoard;
	}

	@Override
	public List<TList> getBoardLists(String boardId, Parameter... args) {
		List<TList> tLists = Arrays
				.asList(get(createUrl(GET_BOARD_LISTS).params(args).asString(), TList[].class, boardId));
		for (TList list : tLists) {
			list.setInternalTrello(this);
			for (Card card : list.getCards()) {
				card.setInternalTrello(this);
			}
		}
		return tLists;
	}

	@Override
	public List<Card> getBoardCards(String boardId, Parameter... args) {
		List<Card> cards = Arrays
				.asList(get(createUrl(GET_BOARD_CARDS).params(args).asString(), Card[].class, boardId));
		for (Card card : cards) {
			card.setInternalTrello(this);
		}
		return cards;
	}

	@Override
	public Card getBoardCard(String boardId, String cardId, Parameter... args) {
		Card card = get(createUrl(GET_BOARD_CARD).params(args).asString(), Card.class, boardId, cardId);
		card.setInternalTrello(this);
		return card;
	}

	@Override
	public List<CheckList> getBoardChecklists(String boardId, Parameter... args) {
		List<CheckList> checkLists = Arrays
				.asList(get(createUrl(GET_BOARD_CHECKLISTS).params(args).asString(), CheckList[].class, boardId));
		for (CheckList checkList : checkLists) {
			checkList.setInternalTrello(this);
		}
		return checkLists;
	}

	@Override
	public Board createBoard(String boardName) {
		Board board = new Board();
		board.setName(boardName);
		Board createdBoard = postForObject(createUrl(CREATE_BOARD).asString(), board, Board.class);
		createdBoard.setInternalTrello(this);
		return createdBoard;
	}

	@Override
	public TList createTList(String tListName, String boardId) {
		TList tList = new TList();
		tList.setName(tListName);
		tList.setIdBoard(boardId);
		TList createdList = postForObject(createUrl(CREATE_BOARD_LIST).asString(), tList, TList.class, boardId);
		createdList.setInternalTrello(this);
		return createdList;
	}

	@Override
	public Board updateBoard(Board board) {
		Board put = put(createUrl(GET_BOARD).asString(), board, Board.class, board.getId());
		put.setInternalTrello(this);
		return put;
	}

	@Override
	public void deleteBoard(String boardID) {
		delete(createUrl(GET_BOARD).asString(), boardID);
	}

	private <T> T postForObject(String url, T object, Class<T> objectClass, String... params) {
		logger.info("PostForObject request on Trello API at url {} for class {} with params {}", url,
				objectClass.getCanonicalName(), params);
		return httpClient.postForObject(url, object, objectClass, tokenizeParams(params));
	}

	private void postForLocation(String url, Object object, String... params) {
		logger.info("PostForLocation request on Trello API at url {} for class {} with params {}", url,
				object.getClass().getCanonicalName(), params);
		System.out.println(httpClient.postForLocation(url, object, tokenizeParams(params)));
	}

	private void delete(String url, String... params) {
		logger.info("Delete request on Trello API at url {} with params {}", url, params);
		httpClient.delete(url, tokenizeParams(params));
	}

	protected <T> T get(String url, Class<T> objectClass, String... params) {
		logger.info("Get request on Trello API at url {} for class {} with params {}", url,
				objectClass.getCanonicalName(), params);
		return httpClient.get(url, objectClass, tokenizeParams(params));
	}

	private <T> T put(String url, T object, Class<T> objectClass, String... params) {
		logger.info("Put request on Trello API at url {} for class {} with params {}", url,
				object.getClass().getCanonicalName(), params);
		return httpClient.putForObject(url, object, objectClass, tokenizeParams(params));
	}

	private String[] tokenizeParams(String[] params) {
		List<String> paramList = new ArrayList<String>(Arrays.asList(params));
		paramList.add(applicationKey);
		paramList.add(accessToken);
		return paramList.toArray(new String[paramList.size()]);
	}

}
