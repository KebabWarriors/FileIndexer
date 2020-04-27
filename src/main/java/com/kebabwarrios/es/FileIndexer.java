package com.kebabwarrios.es;

import java.util.ArrayList;
import java.util.HashMap;


public class FileIndexer {
  //This comes in this way [Path, name, extension, content]
  private ArrayList<String[]> documents = new ArrayList<String[]>();

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

  /**
   * 
   * @param documents
   */
  public void setHashMapDocuments(ArrayList<String[]> documents) {
    String[] newContent;

    for(String[] document : documents) {
      newContent = Preprocessing.convertTextIntoWordsArray(
          Preprocessing.preProcessingText(document[3])); 

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
   * @param documents
   */
  private void indexDocuments(HashMap<String, String[]> documents ){
    Double[] wordsValue;
    String[] documentsName = this.getDocumentsName(documents);

    int iterator = 0, iteratorNames = 0;

    for (String[] document : documents.values()) {
      wordsValue = new Double[document.length]; 

      for (String word : document) {
        wordsValue[iterator] = MathProcedures.tfIdf(document, documents,word);
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
  private String[] getDocumentsName(HashMap<String, String[]> documents) {
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
  private void addToIndexedDocumentsMap(String document, String[] documentWords, Double[] wordsValue) {
    HashMap<String[], Double[]> innerHash = new HashMap<String[], Double[]>();

    innerHash.put(documentWords, wordsValue);

    this.indexedDocumentsMap.put(document, innerHash);
  }

  /**
   * 
   * @return
   */
  public HashMap<String, HashMap<String[], Double[]>> getIndexedDocumentsMap(){
    return this.indexedDocumentsMap;
  }
}
