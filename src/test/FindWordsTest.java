package test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.plin.hangman.utils.SearchWordsUtils;

public class FindWordsTest {
	@Test
	public void findWordsTest() {
		String word = "AA**";
		SearchWordsUtils search = new SearchWordsUtils();
		List<String> findWordsbyLetterNum = search.findAllWordLenthList(word.length());
		System.out.println("findWordsbyLetterNum="+findWordsbyLetterNum);
		List<String> possibleWordsList=search.getPossibleWordsList(word);
		System.out.println("search words by word="+possibleWordsList);
		
		Map<Character, Integer> letterNumMap = search.findWordsByMostLetter(possibleWordsList);
		System.out.println("letterNumMap="+letterNumMap);
		
		List<Map.Entry<Character,Integer>> sortedList = search.getSortedMapList(letterNumMap);
		//打印前6个可能的字母
		Iterator<Entry<Character, Integer>> iterator = sortedList.iterator();
		for (int i = 0; i < 6&&iterator.hasNext(); i++) {
			Entry<Character, Integer> entry = iterator.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
	}
}
