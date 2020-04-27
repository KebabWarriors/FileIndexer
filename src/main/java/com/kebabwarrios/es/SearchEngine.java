package com.kebabwarrios.es;

import java.util.HashMap;

public class SearchEngine {
	public HashMap<String, Double> documentsOrder;
	
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
							System.out.println("Hey encontramos algo parecidooooo!");
						}
					}
				}
				
			}
		}
	}
}
