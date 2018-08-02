package com.plin.hangman.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
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
	 * @return 根据word里面的已有的letter位置找到对应的word
	 */
	public List<String> searchWords(String word, List<String> findWordsbyLen) {
		List<String> findWordsbyLetter = new LinkedList<>();
		for (int i = 0; i < word.length(); i++) {
			
			if(word.charAt(i) != '*') {
				for (String s : findWordsbyLen) {
					if (word.charAt(i)==s.charAt(i)) {
							findWordsbyLetter.add(s);
					}
				}
			}
		}
		return findWordsbyLetter;
	}

	/**
	 * @author plin
	 * @param letterNum
	 * @return 根据word的长度找到所有符合word长度的word
	 */
	public List<String> findWordsbyLetterNum(int letterNum) {
		List<String> findWordsbyLen = new LinkedList<>();
		try {
			// Path path =
			// Paths.get(getClass().getClassLoader().getResource("/resources/words.txt").toURI());
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

	public Map<Character, Integer> findWordsByMostLetter(List<String> findWordsbyLetter) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c = 'A'; c < 'Z'; c++) {
			int m = 0;
			for (String s : findWordsbyLetter) {
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == c) {
						map.put(c, m++);
					}
				}
			}
		}
		return map;
	}
}
