package com.plin.hangman.pojo;

public class ReqMakeGuessPojo {
	private String sessionId;
	private String action;
	private String guess;
	
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
	public String getGuess() {
		return guess;
	}
	public void setGuess(String guess) {
		this.guess = guess;
	}
	@Override
	public String toString() {
		return "ReqMakeGuessPojo [sessionId=" + sessionId + ", action=" + action + ", guess=" + guess + "]";
	}
	
}
