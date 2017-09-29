package com.trello.core;

import com.trello.domain.entity.*;

import java.util.List;

public interface TrelloAPI {
	
	//BOARD
	
	public Board getBoard(String boardID, Parameter ... args);
	
	public List<TList> getBoardLists(String boardId, Parameter ... args);
	
	List<Card> getBoardCards(String boardId, Parameter... args);

    Card getBoardCard(String boardId, String cardId, Parameter... args);
   
    List<CheckList> getBoardChecklists(String boardId, Parameter... args);
	
    public Board createBoard(String boardName);
    
    public TList createTList(String checkListName, String boardId);

	public Board updateBoard(Board board);

	public void deleteBoard(String board);
	
}
