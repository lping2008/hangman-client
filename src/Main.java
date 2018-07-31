import java.io.IOException;
import java.util.Scanner;

import com.plin.hangman.pojo.ResStartGameOnPojo;
import com.plin.hangman.pojo.ResSubmitPojo;
import com.plin.hangman.main.HangmanMain;
import com.plin.hangman.pojo.ResGetResultPojo;
import com.plin.hangman.pojo.ResGivePojo;
import com.plin.hangman.pojo.ResMakeGuessPojo;

public class Main {
	static String sessionId=null;
	
	public static void guessWord(HangmanMain hmain,String guessALetter) throws IOException {
		ResMakeGuessPojo resMakeGuessPojo = hmain.makeGuess(sessionId, guessALetter);
		System.out.println(resMakeGuessPojo);
	}
	
	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		HangmanMain hmain=new HangmanMain();
		while (true) {
			System.out.println("please choose one menu :");
			System.out.println(
					" 1. Start New Game; \t\t\t 2. Give Me A Word;"
					+ "\n 3. Make A Guess;\t\t\t 4. Get My Result;"
					+ "\n 5. Submit My Result;\t\t\t 6. Resume last game;"
					+ "\n 7. Input sessionId to start game;\t 8. exit");

			String menu = scanner.next();
			String playerId=null;
			
			if ("1".equals(menu)) {
				System.out.println("please input your playerId:");
				playerId = scanner.next();
				ResStartGameOnPojo startGame = hmain.startGame(playerId);
				sessionId=startGame.getSessionId();
				System.out.println("sessionId="+sessionId);
				System.out.println("Server is giving you a word");
				ResGivePojo giveWord = hmain.giveWord(sessionId);
				System.out.println(giveWord);
				menu="3";
			}

			if ("2".equals(menu)) {
				if(sessionId!=null) {
					System.out.println("Server is giving you a word");
					ResGivePojo giveWord = hmain.giveWord(sessionId);
					System.out.println(giveWord);
				}else {
					System.out.println("please start a new game or resume last game");
				}
			}

			if ("3".equals(menu)) {
				if(sessionId!=null) {
					while(true) {
						String []savedGame = hmain.getGameInfo();
						//判断是否got一个word
						if(savedGame[1]==null) {
							System.out.println("you have not get a word, please choose menu to give a new word");
							break;
						}
						
						//判断是否guess了10次
						if("10".equals(savedGame[4])) {
							System.out.println("you have already guessed wrong 10 times on current word");
							System.out.println("Server is giving you a word");
							ResGivePojo giveWord = hmain.giveWord(sessionId);
							System.out.println(giveWord);
							break;
						}
						
						System.out.println("please guess and input a character, or input 'exit' to exit to main menu");
						String guessALetter = scanner.next();
						if(guessALetter.equals("exit")) {
							break;
						}
						
						if(guessALetter.length()==1) {
							if(guessALetter.charAt(0)>='A'&&guessALetter.charAt(0)<='Z') {
								guessWord(hmain,guessALetter);
							}else if(guessALetter.charAt(0)>='a'&&guessALetter.charAt(0)<='z'){
								char a=(char) (guessALetter.charAt(0)-32);
								guessALetter=Character.toString(a);
								guessWord(hmain,guessALetter);
							}else {
								System.out.println("you did not input a letter");
							}
						}else {
							System.out.println("you did not input a letter");
						}
					}
				}else {
					System.out.println("please start a new game or resume last game");
				}
			}
			
			if ("4".equals(menu)) {
				if(sessionId!=null) {
					ResGetResultPojo rusult = hmain.getRusult(sessionId);
					System.out.println("your result: "+ rusult);
				}else {
					System.out.println("please start a new game or resume last game");
				}
			}
			
			if ("5".equals(menu)) {
				if(sessionId!=null) {
					ResSubmitPojo submitResult = hmain.submitResult(sessionId);
					System.out.println(submitResult);
				}
				else {
					System.out.println("please start a new game or resume last game");
				}
			}
			
			if("6".equals(menu)) {
				String []savedGame = hmain.getGameInfo();
				if(savedGame!=null) {
					sessionId = savedGame[0];
					System.out.println("sessionId="+savedGame[0]+",word="+savedGame[1]+",totalWordCount="+savedGame[2]+",wrongGuessCountOfCurrentWord="+savedGame[3]);
				}else {
					System.out.println("you did not start a game");
				}
			}
			
			if("7".equals(menu)) {
				System.out.println("please input your sessionId:");
				String inputedSessionId = scanner.next();
				if(inputedSessionId.length()<32) {
					System.out.println("The lenth of your sessionId is not correct");
				}else {
					sessionId = inputedSessionId;
					System.out.println("Server is giving you a word");
					ResGivePojo giveWord = hmain.giveWord(sessionId);
					if(giveWord!=null) {
						System.out.println(giveWord);
						hmain.saveSessionId(sessionId);
					}else {
						System.out.println("Your sessionId maybe incorrect");
					}
				}
			}
			
			if ("8".equals(menu)) {
				System.out.println("Hangman game exited");
				break;
			}
		}
		scanner.close();
	}
}
