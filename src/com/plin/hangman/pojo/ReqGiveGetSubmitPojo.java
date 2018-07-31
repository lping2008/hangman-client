package com.plin.hangman.pojo;

public class ReqGiveGetSubmitPojo {
	private String sessionId ;
	private String action;
	
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
