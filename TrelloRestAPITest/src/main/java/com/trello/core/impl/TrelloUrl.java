package com.trello.core.impl;

import com.trello.core.Parameter;

public class TrelloUrl {

	public static final String API_URL = "https://api.trello.com/1";
	public static final String API_KEY_TOKEN_PARAM = "key={applicationKey}&token={userToken}";

	//BOARD
	public static final String GET_BOARD = "/boards/{boardId}?";
	public static final String CREATE_BOARD = "/boards?";
	public static final String CREATE_BOARD_LIST = "/boards/{boardId}/lists?";
	public static final String GET_BOARD_CARDS = "/boards/{boardId}/cards?";
	public static final String GET_BOARD_CARD = "/boards/{boardId}/cards/{cardId}?";
	public static final String GET_BOARD_LISTS = "/boards/{boardId}/lists?";
	public static final String GET_BOARD_CHECKLISTS = "/boards/{boardId}/checklists?";

	private String baseUrl;
	private Parameter[] args = {};

	private TrelloUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public static TrelloUrl createUrl(String baseUrl) {
		return new TrelloUrl(baseUrl);
	}

	public TrelloUrl params(Parameter... args) {
		this.args = args;
		return this;
	}

	public String asString() {
		StringBuilder builder = new StringBuilder(API_URL);
		builder.append(baseUrl);
		builder.append(API_KEY_TOKEN_PARAM);
		for (Parameter arg : args) {
			builder.append("&");
			builder.append(arg.getName());
			builder.append("=");
			builder.append(arg.geValue());
		}
		return builder.toString();
	}
}