package com.trello.exception;

public class TrelloHttpException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public TrelloHttpException(String message) {
		super(message);
	}

	public TrelloHttpException(Throwable cause) {
		super(cause);
	}
}