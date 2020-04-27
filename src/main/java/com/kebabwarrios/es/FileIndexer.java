package com.kebabwarrios.es;

import java.util.ArrayList;
import java.util.HashMap;


public class FileIndexer {
	//This comes in this way [Path, name, extension, content]
	private ArrayList<String[]> documents = new ArrayList<String[]>();
	
	
	private String regularExpression = "[^a-zA-Z0-9]";
	HashMap<String, String[]> documentsMap = new HashMap<String, String[]>();
	HashMap<String, HashMap<String[], Double[]> > indexedDocumentsMap = new HashMap<>();
	
	
	/**
	 * 
	 * @param documents
	 */
	public FileIndexer(ArrayList<String[]> documents){
		this.documents = documents;
		this.setHashMapDocuments(this.documents);
		this.indexDocuments(this.documentsMap);
	}
	/*
	 * This constructor works when we need some functions but not to index a thing
	 */
	public FileIndexer() {
		
	}
	/**
	 * 
	 * @param documents
	 */
	public void setHashMapDocuments(ArrayList<String[]> documents) {
		String[] newContent;
		for(String[] document : documents) {
			newContent = this.convertTextIntoWordsArray(this.preProcessingText(document[3])); 
			this.addToDocumentsMap(document[1], newContent);
		}
	}
	/**
	 * 
	 * @param documentName
	 * @param documentContent
	 */
	public void addToDocumentsMap(String documentName, String[] documentContent){
		this.documentsMap.put(documentName, documentContent);
	}
	
	
	/**
	 * 
	 * @param oldText
	 * @return Text without special characters
	 */
	public String preProcessingText(String oldText) {
		String newText = "";
		newText = oldText.replaceAll(this.regularExpression, " ");
		return newText;
	}
	/**
	 * 
	 * @param textToConvert
	 * @return array with words of the original String
	 */
	public String[] convertTextIntoWordsArray(String textToConvert) {
		String[] wordsArray = textToConvert.toLowerCase().split(" ");
		return wordsArray;
		
	}
	
	
	
	 /**
     * @param doc  list of strings
     * @param term String represents a term
     * @return term frequency of term in document
     */
	
	  public double tf(String[] doc, String term) { 
		  double result = 0; 
		  for (String word : doc) { 
			  if (term.equalsIgnoreCase(word)) {
				  result++;
			  }
				   
		  } 
		  return result / doc.length; 
	 }
	 

    /**
     * @param docs list of list of strings represents the dataset
     * @param term String represents a term
     * @return the inverse term frequency of term in documents
     */
	
	  public double idf(HashMap<String, String[]> docs, String term) { 
		  double total = 0; 
		  for(String[] words : docs.values()) {
			  for(String word : words) {
				  if(term.equals(word)) { 
					  total++; 
					  break;   
				  }
			  }
		  }
		  return this.idfValidator(docs.size(), total); 
		}
	  
	  public double idfValidator(int docsSize, double total) {
		  double idfValue = 0;
		  idfValue = (docsSize == 1) ? 1 : Math.log10(docsSize/ total);
		  //idfValue = (idfValue < 1) ? 1 : idfValue;
		  return idfValue;
	  }

    /**
     * @param doc  a text document
     * @param docs all documents
     * @param term term
     * @return the TF-IDF of term
     */
	
	  public double tfIdf(String[] doc, HashMap<String, String[]> docs, String term) {
		  return this.tf(doc, term) * this.idf(docs, term);
	  
	  }
	  /**
	   * 
	   * @param documents
	   */
	  public void indexDocuments(HashMap<String, String[]> documents ){
		  Double[] wordsValue;
		  String[] documentsName = this.getDocumentsName(documents);
		  int iterator = 0, iteratorNames = 0;
 		  for(String[] document : documents.values()) {
 			  wordsValue = new Double[document.length]; 
			  for(String word : document) {
				  wordsValue[iterator] = this.tfIdf(document, documents,word);
				  //System.out.println(this.tfIdf(document, documents,word));
				  iterator++;
			  }
			  iterator = 0;
			  this.addToIndexedDocumentsMap(documentsName[iteratorNames], document, wordsValue);
			  iteratorNames++;
		  }
	  }
	  /**
	   * 
	   * @param documents
	   * @return
	   */
	  public String[] getDocumentsName(HashMap<String, String[]> documents) {
		  String[] names = new String[documents.size()];
		  int iterator = 0;
		  for(String name : documents.keySet()) {
			  names[iterator] = name;
			  iterator++;
		  }
		  return names;
	  }
	  /**
	   * 
	   * @param document
	   * @param documentWords
	   * @param wordsValue
	   */
	  public void addToIndexedDocumentsMap(String document, String[] documentWords, Double[] wordsValue) {
		  HashMap<String[], Double[]> innerHash = new HashMap<String[], Double[]>();
		  innerHash.put(documentWords, wordsValue);
		  this.indexedDocumentsMap.put(document, innerHash);
	  }
	 /**
	  * 
	  * @return
	  */
	 public  HashMap<String, HashMap<String[], Double[]>> getIndexedDocumentsMap(){
		 return this.indexedDocumentsMap;
	 }
 }
