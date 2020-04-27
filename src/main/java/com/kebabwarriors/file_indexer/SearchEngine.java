package com.kebabwarriors.file_indexer;

import java.util.ArrayList;
import java.util.HashMap;

public final class SearchEngine {
  public static HashMap<String, Double> search(
    String query,
    HashMap<String, String[]> documents,
    HashMap<String, HashMap<String[], Double[]>> indexedDocuments
  ) {
    HashMap<String, Double> vectorQuery = tdIdfVector(query, documents);
    HashMap<String, Double> result = new HashMap<>();
    ArrayList<Double> vectorQueryTerms = new ArrayList<>();
    Double[] vectorQueryTermsArray;
    
    vectorQuery.forEach((key, value) -> vectorQueryTerms.add(value));
    
    vectorQueryTermsArray = vectorQueryTerms.toArray(new Double[vectorQueryTerms.size()]);
    
    indexedDocuments.forEach((keyOne, valueOne) -> {
      valueOne.forEach((keyTwo, valueTwo) -> {
        result.put(keyOne, MathProcedures.cosineSimilarity(vectorQueryTermsArray, valueTwo));
      });
    });
    return result;
  }

  private static String[] vectorize(String query) {
    return Preprocessing.convertTextIntoWordsArray(
      Preprocessing.preProcessingText(query));
  }
  
  private static HashMap<String, Double> tdIdfVector(String query, HashMap<String, String[]> documents) {
    String[] vector = vectorize(query);
    HashMap<String, Double> tfIdfVector = new HashMap<>();
    
    for (String word : vector) {
      tfIdfVector.put(word, MathProcedures.tfIdf(vector, documents, word));
    }
    
    return tfIdfVector;
  }
}
