package com.trello.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Card extends TrelloEntity {

    private String desc;
    private boolean closed;
    private List<String> idMembers;
    private List<String> idChecklists;
    private String idAttachmentCover;
    private String idList;
    private String idBoard;
    private int pos;
    private Date due;
    private boolean dueCompleted;
    private boolean subscribed;
  
    public String getIdList() {
        return idList;
    }

    public void setIdList(String idList) {
        this.idList = idList;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Date getDue() {
        return due;
    }

    public void setDue(Date due) {
        this.due = due;
    }

    public List<String> getIdMembers() {
        return idMembers;
    }

    public void setIdMembers(List<String> idMembers) {
        this.idMembers = idMembers;
    }
  
    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getIdBoard() {
        return idBoard;
    }

    public void setIdBoard(String idBoard) {
        this.idBoard = idBoard;
    }

    public String getIdAttachmentCover() {
        return idAttachmentCover;
    }

    public void setIdAttachmentCover(String idAttachmentCover) {
        this.idAttachmentCover = idAttachmentCover;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }

	public boolean isDueCompleted() {
		return dueCompleted;
	}

	public void setDueCompleted(boolean dueCompleted) {
		this.dueCompleted = dueCompleted;
	}

	public List<String> getIdChecklists() {
		return idChecklists;
	}

	public void setIdChecklists(List<String> idChecklists) {
		this.idChecklists = idChecklists;
	}
}
