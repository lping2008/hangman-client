package com.plin.hangman.pojo;

public class ResGivePojo {
	private String sessionId;
	private ResData data;
	
	public class ResData{
		private String word;
		private String totalWordCount;
		private String wrongGuessCountOfCurrentWord;
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
		}
		public String getTotalWordCount() {
			return totalWordCount;
		}
		public void setTotalWordCount(String totalWordCount) {
			this.totalWordCount = totalWordCount;
		}
		public String getWrongGuessCountOfCurrentWord() {
			return wrongGuessCountOfCurrentWord;
		}
		public void setWrongGuessCountOfCurrentWord(String wrongGuessCountOfCurrentWord) {
			this.wrongGuessCountOfCurrentWord = wrongGuessCountOfCurrentWord;
		}
		@Override
		public String toString() {
			return "ResDate [word=" + word + ", totalWordCount=" + totalWordCount + ", wrongGuessCountOfCurrentWord="
					+ wrongGuessCountOfCurrentWord + "]";
		}
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
		return "ResponsePojo [sessionId=" + sessionId + ", date=" + data + "]";
	}
}
