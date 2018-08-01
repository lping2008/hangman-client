package com.plin.hangman.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.plin.hangman.constants.Constants;

public class SearchWordsUtils {
	
	private List<String> words;
	
	public SearchWordsUtils() {
		
	}

	/**
	 * @author plin
	 * @param word
	 * @return 根据word的长度找到所有符合word长度的word，并根据word里面的已有的letter位置找到对应的word
	 */
	public List<String> searchWords(String word) {
		List<String> findWordsbyLetter = new LinkedList<>();
		List<String> findWordsbyLen = findWordsbyLetterNum(word.length());
		for (int i = 0; i < word.length(); i++) {
			for (Iterator iterator = findWordsbyLen.iterator(); iterator.hasNext();) {
				String s= (String) iterator.next();
				if(word.charAt(i)==s.charAt(i)) {
					findWordsbyLetter.add(s);
				}
			}
		}
		return findWordsbyLetter;
	}

	public List<String> findWordsbyLetterNum(int letterNum) {
		List<String> findWordsbyLen = new LinkedList<>();
		try {
//			Path path = Paths.get(getClass().getClassLoader().getResource("/resources/words.txt").toURI());
			Path path = Paths.get(Constants.class.getResource("/resources/words.txt").toURI());
			words = Files.readAllLines(path);
			words.stream().filter(s -> s.matches("^[a-zA-Z]+$")).map(String::toUpperCase).forEach(word -> {
				if (word.length() == letterNum) {
					findWordsbyLen.add(word);
				}
			});
			return findWordsbyLen;
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException("import words fails");
		}
	}
	
	public List<String> findWordsByMostLetter(List<String> findWordsbyLetter){
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		
		List<String> allLetter = new ArrayList<String>();
		for (char c = 'A'; c < 'Z'; c++) {
			map.put(c, 0);
		}
		
		for (Iterator iterator = findWordsbyLetter.iterator(); iterator.hasNext();) {
			int m=0;
			String s = (String) iterator.next();
			for (int i = 0; i < s.length(); i++) {
				for (char c = 'A'; c < 'Z'; c++) {
					if(s.charAt(i)==c) {
						map.put(c, m++);
					}
				}
			}
		}
		
		return null;
	}
}
