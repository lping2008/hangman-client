package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.plin.hangman.utils.SearchWordsUtils;

public class FindWordsTest {
	
	@Test
	public void findWordsTest() {
		String word = "**AD*";
		SearchWordsUtils search = new SearchWordsUtils();
		List<String> findWordsbyLetterNum = search.findWordsbyLetterNum(word.length());
		System.out.println("findWordsbyLetterNum="+findWordsbyLetterNum);
		List<String> searchByWord=search.searchWords(word,findWordsbyLetterNum);
		System.out.println("search words by word="+searchByWord);
		
		Map<Character, Integer> letterNumMap = search.findWordsByMostLetter(searchByWord);
		
		List<Map.Entry<Character,Integer>> sortedList = new ArrayList<Map.Entry<Character,Integer>>(letterNumMap.entrySet());
        Collections.sort(sortedList,new Comparator<Map.Entry<Character,Integer>>() {
			@Override
			public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
        });
        for(Entry<Character, Integer> mapping:sortedList){ 
            System.out.println(mapping.getKey()+":"+mapping.getValue()); 
       }
	}
}
