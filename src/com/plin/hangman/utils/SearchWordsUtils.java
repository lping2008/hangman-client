package com.plin.hangman.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.plin.hangman.constants.Constants;

public class SearchWordsUtils {

	private List<String> words;

	/**
	 * @author plin
	 * @param word
	 * @return 找到word中具有相同位置的单词
	 */
	public List<String> getPossibleWordsList(String word) {
		List<String> allWordLenthList = findAllWordLenthList(word.length());
		List<String> possibleWordsList = new LinkedList<String>();
		List<Integer> indexInWord = new ArrayList<Integer>();
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
				indexInWord.add(i);
			}
		}
//		System.out.println("indexInWord=" + indexInWord);

		List<String>  tempList = new  LinkedList<String>();
		tempList = allWordLenthList;

		for (Integer index : indexInWord) {
			 possibleWordsList=new LinkedList<String>();
			for (Iterator<String> iterator = tempList.iterator(); iterator.hasNext();) {
				String s = (String) iterator.next();
				{
					if (word.charAt(index) == s.charAt(index)) {
						possibleWordsList.add(s);
						iterator.remove();
					}
				}
			}
			tempList = possibleWordsList;
		}
		return possibleWordsList;
	}

	/**
	 * @author plin
	 * @param letterNum
	 * @return 根据word的长度找到所有符合word长度的word
	 */
	public List<String> findAllWordLenthList(int letterNum) {
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

	/**
	 * @author plin
	 * @param findWordsbyLetter
	 * @return 得到26个字母的map，value为出现的个数
	 */
	public Map<Character, Integer> findWordsByMostLetter(List<String> findWordsbyLetter) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c = 'A'; c < 'Z'; c++) {
			int m = 1;
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

	// 将得到的26字符的Map进行排序
	public List<Map.Entry<Character, Integer>> getSortedMapList(Map<Character, Integer> findWordsByMostLetter) {
		List<Map.Entry<Character, Integer>> sortedList = new ArrayList<Map.Entry<Character, Integer>>(
				findWordsByMostLetter.entrySet());
		Collections.sort(sortedList, new Comparator<Map.Entry<Character, Integer>>() {
			@Override
			public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		return sortedList;
	}

}
