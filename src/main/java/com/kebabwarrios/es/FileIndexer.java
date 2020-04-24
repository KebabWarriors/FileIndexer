package com.kebabwarrios.es;

import java.util.ArrayList;

public class FileIndexer {
	//This comes in this way [Path, name, extension, content]
	private ArrayList<String[]> documents = new ArrayList<String[]>();
	//this has to be another that become in [name, [strings content]]
	
	private String regularExpression = "[^a-zA-Z0-9]";
	
	public FileIndexer(ArrayList<String[]> documents){
		this.documents = documents;
	}
	
	public String preProcessingText(String oldText) {
		String newText = "";
		newText = oldText.replaceAll(this.regularExpression, "");
		return newText;
	}
	public String[] convertTextIntoWordsArray(String textToConvert) {
		String[] wordsArray = textToConvert.split(" ");
		return wordsArray;
		
	}
 }
