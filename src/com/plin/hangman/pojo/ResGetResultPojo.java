package com.plin.hangman.pojo;

public class ResGetResultPojo {
	private String sessionId;
	private ResData data;
	
	public class ResData{
		private String score;
		private String wrongGuessCountOfCurrentWord;
		private String totalWordCount;
		private String word;
		public String getScore() {
			return score;
		}
		public void setScore(String score) {
			this.score = score;
		}
		public String getWrongGuessCountOfCurrentWord() {
			return wrongGuessCountOfCurrentWord;
		}
		public void setWrongGuessCountOfCurrentWord(String wrongGuessCountOfCurrentWord) {
			this.wrongGuessCountOfCurrentWord = wrongGuessCountOfCurrentWord;
		}
		public String getTotalWordCount() {
			return totalWordCount;
		}
		public void setTotalWordCount(String totalWordCount) {
			this.totalWordCount = totalWordCount;
		}
		public String getWord() {
			return word;
		}
		public void setWord(String word) {
			this.word = word;
		}
		@Override
		public String toString() {
			return "ResData [score=" + score + ", wrongGuessCountOfCurrentWord=" + wrongGuessCountOfCurrentWord
					+ ", totalWordCount=" + totalWordCount + ", word=" + word + "]";
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
