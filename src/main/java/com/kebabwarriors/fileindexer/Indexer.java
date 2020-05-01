package com.kebabwarriors.fileindexer;

import java.util.ArrayList;
import java.util.HashMap;

public final class Indexer {
  //This comes in this way [Path, name, extension, content]
  private ArrayList<String[]> documents = new ArrayList<String[]>();

  HashMap<String, String[]> documentsMap = new HashMap<String, String[]>();
  HashMap<String, HashMap<String[], Double[]>> indexedDocumentsMap = new HashMap<>();
  
  public Indexer(ArrayList<String[]> documents) {
    this.documents = documents;
    this.setHashMapDocuments(this.documents);
    this.indexDocuments(this.documentsMap);
  }
  
  public void setHashMapDocuments(ArrayList<String[]> documents) {
    String[] newContent;

    for(String[] document : documents) {
      newContent = Preprocessing.convertTextIntoWordsArray(
          Preprocessing.preProcessingText(document[3])); 

      this.addToDocumentsMap(document[1], newContent);
    }
  }

  public void addToDocumentsMap(String documentName, String[] documentContent){
    this.documentsMap.put(documentName, documentContent);
  }

  private void indexDocuments(HashMap<String, String[]> documents ){
    Double[] wordsValue;
    String[] documentsName = getDocumentsName(documents);
    int iterator = 0;
    int iteratorNames = 0;

    for (String[] document : documents.values()) {
      wordsValue = new Double[document.length]; 

      for (String word : document) {
        wordsValue[iterator] = Procedures.tfIdf(document, documents,word);
        iterator += 1;
      }

      iterator = 0;

      this.addToIndexedDocumentsMap(documentsName[iteratorNames], document, wordsValue);

      iteratorNames += 1;
    }
  }

  public static String[] getDocumentsName(HashMap<String, String[]> documents) {
    String[] names = new String[documents.size()];
    int iterator = 0;

    for(String name : documents.keySet()) {
      names[iterator] = name;
      iterator += 1;
    }

    return names;
  }

  private void addToIndexedDocumentsMap(String document, String[] documentWords, Double[] wordsValue) {
    HashMap<String[], Double[]> innerHash = new HashMap<String[], Double[]>();

    innerHash.put(documentWords, wordsValue);

    this.indexedDocumentsMap.put(document, innerHash);
  }

  public HashMap<String, HashMap<String[], Double[]>> getIndexedDocumentsMap(){
    return this.indexedDocumentsMap;
  }
  
  public HashMap<String, String[]> getDocumentsMap() {
    return this.documentsMap;
  }
}
