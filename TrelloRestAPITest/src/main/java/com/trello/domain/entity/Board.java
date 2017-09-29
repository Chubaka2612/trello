package com.trello.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.trello.core.Parameter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Board extends TrelloEntity {

    private String desc;
    private boolean closed;
    private String idOrganization;
    private Map<String, String> labelNames;
    private boolean invited;
    private List<String> invitations;
    private boolean subscribed;
    private List<TList> lists  = new ArrayList<TList>();

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public String getIdOrganization() {
        return idOrganization;
    }

    public void setIdOrganization(String idOrganization) {
        this.idOrganization = idOrganization;
    }

    public Map<String, String> getLabelNames() {
        return labelNames;
    }

    public void setLabelNames(Map<String, String> labelNames) {
        this.labelNames = labelNames;
    }

    public boolean isInvited() {
        return invited;
    }

    public void setInvited(boolean invited) {
        this.invited = invited;
    }

    public List<String> getInvitations() {
        return invitations;
    }

    public void setInvitations(List<String> invitations) {
        this.invitations = invitations;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
    
    public List<TList> getTLists() {
        return lists;
    }

    public void setTLists(List<TList> lists) {
        this.lists = lists;
    }
    
    //API
    public List<TList> obtainTLists(Parameter ... args) {
        return trelloService.getBoardLists(getId(), args);
    }

    public List<Card> obtainCards(Parameter... args) {
        return trelloService.getBoardCards(getId(), args);
    }

    public Card obtainCard(String cardId, Parameter... args) {
        return trelloService.getBoardCard(getId(), cardId, args);
    }
    
    public List<CheckList> obtainCheckLists(Parameter... args) {
        return trelloService.getBoardChecklists(getId(), args);
    }
}
