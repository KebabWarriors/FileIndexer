package com.kebabwarrios.es;

import java.util.ArrayList;
import java.util.HashMap;

public final class Searcher {
  public static HashMap<String, Double> search(
    String query,
    HashMap<String, String[]> documents,
    HashMap<String, HashMap<String[], Double[]>> indexedDocuments
  ) {
    HashMap<String, Double> vectorQuery = tdIdfVector(query, documents);
    HashMap<String, Double> vectorQuerySquared = new HashMap<>();

    Double numerator;
    Double denominator;

    vectorQuery.forEach((keyOne, valueOne) -> {
      vectorQuerySquared.put(keyOne, Math.pow(valueOne, 2));
    });
    
    indexedDocuments.forEach((keyOne, valueOne) -> {
      ArrayList<Double> documentVectorSquared = new ArrayList<>();
      
      valueOne.forEach((keyTwo, valueTwo) -> {
        for (int x = 0; x < valueTwo.length; x++)
          documentVectorSquared.add(Math.pow(valueTwo[x], 2));
      });
      
      vectorQuerySquared.forEach((keyThree, valueThree) -> {
        documentVectorSquared.forEach((value) -> {
          numerator.add(valueThree * value);
        });
      });
    });
  }
  
  private static HashMap<String, Double> makeDenominator(
    HashMap<String, Double> vectorOne,
    HashMap<String, Double> vectorTwo
  ) {
    HashMap<String, Double> vectorOneSquared = new HashMap<String, Double>();
    HashMap<String, Double> vectorTwoSquared = new HashMap<String, Double>();
    
    vectorOne.forEach((key, value) -> {
      vectorOneSquared.put(key, Math.pow(value, 2));
    });
    
    vectorTwo.forEach((key, value) -> {
      vectorTwoSquared.put(key, Math.pow(value, 2));
    });
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
