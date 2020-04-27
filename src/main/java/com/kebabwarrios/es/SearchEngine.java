package com.kebabwarrios.es;

import java.util.HashMap;

public class SearchEngine {
	static HashMap<String, HashMap<String, Double>> documentsOrder = new HashMap<String, HashMap<String, Double>>();
	
	/**
	 * 
	 * @param query
	 * @param documents
	 */
	public static void searchByQuery(String query, HashMap<String, HashMap<String[],Double[]>> documents) {
		FileIndexer processor = new FileIndexer();
		String[] arrayQuery = processor.convertTextIntoWordsArray(processor.preProcessingText(query));
		for(String word : arrayQuery) {
			for(String document : documents.keySet()) {
				String[] words;
				Double[] values;
				for(String[] insideWord : documents.get(document).keySet()) {
					words = insideWord;
					values = documents.get(document).get(insideWord);
					for(int i = 0; i<=words.length-1;i++) {
						if(words[i].equals(word)) {
							System.out.println("Hey encontramos algo parecidooooo! "+document+" "+ words[i]+" " +values[i]);
							verifyWordInMap(document, words[i], values[i]);
						}
					}
				}
				
			}
		}
	}
	
	public static void verifyWordInMap(String document, String word, Double value) {
		if(documentsOrder.containsKey(document)) {
			if(!documentsOrder.get(document).containsKey(word)) {
				addInnerWordToDocumentInsideMap(document,word,value);
			}else {
				System.out.println("Ya estaba");
			}
		}
		else {
			System.out.println("No estaba");
			addWordInMap(document,word,value);
		}
	}
	public static void addWordInMap(String document, String word, Double value) {
		HashMap<String, Double> innerMap = new HashMap<String, Double>();
		innerMap.put(word,value);
		documentsOrder.put(document,innerMap);
	}
	public static void addInnerWordToDocumentInsideMap(String document,String word, Double value) {
		documentsOrder.get(document).put(word,value);
	}
	
}
