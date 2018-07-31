package com.plin.hangman.pojo;

public class ResStartGameOnPojo {
	private String message;
	private String sessionId;
	private ResData data;
	
	public class ResData{
		private String numberOfWordsToGuess;
		private String numberOfGuessAllowedForEachWord;
		public String getNumberOfWordsToGuess() {
			return numberOfWordsToGuess;
		}
		public void setNumberOfWordsToGuess(String numberOfWordsToGuess) {
			this.numberOfWordsToGuess = numberOfWordsToGuess;
		}
		public String getNumberOfGuessAllowedForEachWord() {
			return numberOfGuessAllowedForEachWord;
		}
		public void setNumberOfGuessAllowedForEachWord(String numberOfGuessAllowedForEachWord) {
			this.numberOfGuessAllowedForEachWord = numberOfGuessAllowedForEachWord;
		}
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public ResData getData() {
		return data;
	}

	public void setData(ResData data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ResStartGameOnPojo [message=" + message + ", sessionId=" + sessionId + ", data=" + data + "]";
	}

}
