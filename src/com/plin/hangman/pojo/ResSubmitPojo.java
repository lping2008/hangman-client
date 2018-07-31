package com.plin.hangman.pojo;

public class ResSubmitPojo {
	private String message;
	private String sessionId;
	private ResData data;
	
	public class ResData{
		private String playerId;
		private String sessionId;
		private String totalWordCount;
		private String correctWordCount;
		private String totalWrongGuessCount;
		private String score;
		private String datetime;
		public String getPlayerId() {
			return playerId;
		}
		public void setPlayerId(String playerId) {
			this.playerId = playerId;
		}
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
		public String getTotalWordCount() {
			return totalWordCount;
		}
		public void setTotalWordCount(String totalWordCount) {
			this.totalWordCount = totalWordCount;
		}
		public String getCorrectWordCount() {
			return correctWordCount;
		}
		public void setCorrectWordCount(String correctWordCount) {
			this.correctWordCount = correctWordCount;
		}
		public String getTotalWrongGuessCount() {
			return totalWrongGuessCount;
		}
		public void setTotalWrongGuessCount(String totalWrongGuessCount) {
			this.totalWrongGuessCount = totalWrongGuessCount;
		}
		public String getScore() {
			return score;
		}
		public void setScore(String score) {
			this.score = score;
		}
		public String getDatetime() {
			return datetime;
		}
		public void setDatetime(String datetime) {
			this.datetime = datetime;
		}
		@Override
		public String toString() {
			return "ResDate [playerId=" + playerId + ", sessionId=" + sessionId + ", totalWordCount=" + totalWordCount
					+ ", correctWordCount=" + correctWordCount + ", totalWrongGuessCount=" + totalWrongGuessCount
					+ ", score=" + score + ", datetime=" + datetime + "]";
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
		return "ResSubmitPojo [message=" + message + ", sessionId=" + sessionId + ", data=" + data + "]";
	}

	
}
