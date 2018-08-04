package com.plin.hangman.main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.plin.hangman.constants.Constants;
import com.plin.hangman.pojo.ReqGiveGetSubmitPojo;
import com.plin.hangman.pojo.ReqMakeGuessPojo;
import com.plin.hangman.pojo.ReqStartPojo;
import com.plin.hangman.pojo.ResGetResultPojo;
import com.plin.hangman.pojo.ResGivePojo;
import com.plin.hangman.pojo.ResMakeGuessPojo;
import com.plin.hangman.pojo.ResStartGameOnPojo;
import com.plin.hangman.pojo.ResSubmitPojo;
import com.plin.hangman.utils.HttpClientUtils;
import com.plin.hangman.utils.JsonUtils;
import com.plin.hangman.utils.SearchWordsUtils;

public class HangmanMain {
	
	String filePath = Constants.filePath;
	File file = new File(filePath+"saveGame.properties");
	Properties pps = new Properties();
	static int guessTimes=0;
	
	/**
	 * @param playerId
	 * @return:开始游戏返回的pojo
	 * @throws IOException
	 */
	public ResStartGameOnPojo startGame(String playerId) throws IOException {
		//创建请求的pojo
		ReqStartPojo startPojo = new ReqStartPojo();
		//设置属性值
		startPojo.setPlayerId(playerId);
		startPojo.setAction("startGame");
		//转换为json
		String startReq = JsonUtils.objectToJson(startPojo);
		//发送http请求
		String doPostResult = HttpClientUtils.hangManPost(Constants.HANG_MAN_URL, startReq);
		//将请求结果转换为对象
		ResStartGameOnPojo gameOn = JsonUtils.jsonToPojo(doPostResult, ResStartGameOnPojo.class);
		//clear saveGame.properties
		delGameInfo();
		//将sessionId存入bin/saveGame.properties中
		saveSessionId(gameOn.getSessionId());
		return gameOn;
	}

	/**
	 * @param sessionId:
	 * @return 请求给一个word返回的pojo
	 * @throws IOException 
	 */
	public ResGivePojo giveWord(String sessionId) throws IOException {
		ReqGiveGetSubmitPojo give = new ReqGiveGetSubmitPojo();
		// 封装请求的对象
		give.setSessionId(sessionId);
		give.setAction("nextWord");
		// 将对象转换为json格式String
		String giveReq = JsonUtils.objectToJson(give);
		// 发送post请求
		String giveRes = HttpClientUtils.hangManPost(Constants.HANG_MAN_URL, giveReq);
		// 将得到json转换为对象
		if(giveRes!=null&&!giveRes.equals("")) {
			ResGivePojo giveResPojo = JsonUtils.jsonToPojo(giveRes, ResGivePojo.class);
			//将game信息存入bin/saveGame.properties中
			String []saveGame = {giveResPojo.getSessionId(),giveResPojo.getData().getWord(),giveResPojo.getData().getTotalWordCount(),
					giveResPojo.getData().getWrongGuessCountOfCurrentWord()};
			saveGame(saveGame);
			guessTimes=0;
			saveGuessTimes(guessTimes);
			return giveResPojo;
		}else {
			return null;
		}
			
	}

	public ResMakeGuessPojo makeGuess(String sessionId, String guessAWord) throws IOException {
		ReqMakeGuessPojo makeGuess = new ReqMakeGuessPojo();
		makeGuess.setSessionId(sessionId);
		makeGuess.setAction("guessWord");
		makeGuess.setGuess(guessAWord);
		// 将对象转换为json格式String
		String guessReq = JsonUtils.objectToJson(makeGuess);
		// 发送post请求
		String giveRes = HttpClientUtils.hangManPost(Constants.HANG_MAN_URL, guessReq);
		// 将得到json转换为对象
		ResMakeGuessPojo resMakeGuessPojo = JsonUtils.jsonToPojo(giveRes, ResMakeGuessPojo.class);
		//将game信息存入bin/saveGame.properties中
		String []saveGame = {resMakeGuessPojo.getSessionId(),resMakeGuessPojo.getData().getWord(),resMakeGuessPojo.getData().getTotalWordCount(),
				resMakeGuessPojo.getData().getWrongGuessCountOfCurrentWord()};
		saveGame(saveGame);
		guessTimes+=1;
		saveGuessTimes(guessTimes);
		return resMakeGuessPojo;
	}



	public ResGetResultPojo getRusult(String sessionId) {
		ReqGiveGetSubmitPojo reqGet = new ReqGiveGetSubmitPojo();
		// 封装请求的对象
		reqGet.setSessionId(sessionId);
		reqGet.setAction("getResult");
		// 将对象转换为json格式String
		String getResultReq = JsonUtils.objectToJson(reqGet);
		// 发送post请求
		String giveRes = HttpClientUtils.hangManPost(Constants.HANG_MAN_URL, getResultReq);
		// 将得到json转换为对象
		ResGetResultPojo resGetResultPojo = JsonUtils.jsonToPojo(giveRes, ResGetResultPojo.class);
		return resGetResultPojo;
	}

	public ResSubmitPojo submitResult(String sessionId) {
		ReqGiveGetSubmitPojo reqSubmit = new ReqGiveGetSubmitPojo();
		// 封装请求的对象
		reqSubmit.setSessionId(sessionId);
		reqSubmit.setAction("getResult");
		// 将对象转换为json格式String
		String submit = JsonUtils.objectToJson(reqSubmit);
		// 发送post请求
		String giveRes = HttpClientUtils.hangManPost(Constants.HANG_MAN_URL, submit);
		// 将得到json转换为对象
		ResSubmitPojo resGetResultPojo = JsonUtils.jsonToPojo(giveRes, ResSubmitPojo.class);
		return resGetResultPojo;
	}
	
	/**
	 * @author plin
	 * @return 从bin/savaGame.properties中取出sessionId
	 * @throws IOException
	 */
	public String[] getGameInfo() throws IOException{
		if(file.exists()) {
			InputStream in = new BufferedInputStream (new FileInputStream(file));
			pps.load(in);
			String sessionId = pps.getProperty("sessionId");
			String word=pps.getProperty("word");
			String totalWordCount =pps.getProperty("totalWordCount");
			String wrongGuessCountOfCurrentWord =pps.getProperty("wrongGuessCountOfCurrentWord");
			String guessTimes = pps.getProperty("guessTimes");
			String []resumedGame={sessionId,word,totalWordCount,wrongGuessCountOfCurrentWord,guessTimes};
			in.close();
			return resumedGame;
		}else {
			return null;
		}
	}
	
	public void delGameInfo() throws IOException {
		if(!file.exists()) {
			file.createNewFile();
		}
		InputStream in = new FileInputStream(file);
		pps.load(in);
		pps.clear();
		OutputStream out = new FileOutputStream(file);
		pps.store(out, "delete " + "gameInfo");
		in.close();
		out.close();
	}
	
	/**
	 * @author plin
	 * @param sessionId 将sessionId存入bin/save.properties
	 */
	public void saveSessionId(String sessionId) throws IOException {
		if(!file.exists()) {
			file.createNewFile();
		}
		InputStream in = new FileInputStream(file);
		pps.load(in);
		OutputStream out = new FileOutputStream(file);
		pps.setProperty("sessionId", sessionId);
		pps.store(out, "Update " + "sessionId" + " value");
		in.close();
		out.close();
	}
	
	
	public void saveGame(String[] saveGame) throws IOException {
		if(!file.exists()) {
			file.createNewFile();
		}
		InputStream in = new FileInputStream(file);
		pps.load(in);
		OutputStream out = new FileOutputStream(file);
		String sessionId=saveGame[0];
		String word=saveGame[1];
		String totalWordCount = saveGame[2];
		String wrongGuessCountOfCurrentWord = saveGame[3];
		pps.setProperty("sessionId", sessionId);
		pps.setProperty("word", word);
		pps.setProperty("totalWordCount", totalWordCount);
		pps.setProperty("wrongGuessCountOfCurrentWord", wrongGuessCountOfCurrentWord);
		pps.store(out, "Update " + "giveWord" + " Info");
		in.close();
		out.close();
	}
	
	public void saveGuessTimes(int guessTimes2) throws IOException {
		if(!file.exists()) {
			file.createNewFile();
		}
		InputStream in = new FileInputStream(file);
		pps.load(in);
		OutputStream out = new FileOutputStream(file);
		pps.setProperty("guessTimes", Integer.toString(guessTimes2));
		pps.store(out, "Update " + "guessTimes" + " value");
		in.close();
		out.close();
	}
	//找出可能前6个字母
	public List<Map.Entry<Character,Integer>> getPossibleLetter(String word){
		SearchWordsUtils search = new SearchWordsUtils();
		List<String> possibleWordsList=search.getPossibleWordsList(word);
		
		Map<Character, Integer> letterNumMap = search.findWordsByMostLetter(possibleWordsList);
		
		List<Map.Entry<Character,Integer>> sortedList = search.getSortedMapList(letterNumMap);
		//打印前6个可能的字母
		Iterator<Entry<Character, Integer>> iterator = sortedList.iterator();
		for (int i = 0; i < 6&&iterator.hasNext(); i++) {
			Entry<Character, Integer> entry = iterator.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		return sortedList;
	}
}
