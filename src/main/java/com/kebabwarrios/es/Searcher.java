package com.kebabwarrios.es;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public final class Searcher {
  public static HashMap<String, Double> search(
    String query,
    HashMap<String, String[]> documents,
    HashMap<String, HashMap<String[], Double[]>> indexedDocuments
  ) {
    HashMap<String, Double> vectorQuery = tdIdfVector(query, documents);
    HashMap<String, Double> result = new HashMap<>();
    
    indexedDocuments.forEach((keyOne, valueOne) -> {
      valueOne.forEach((keyTwo, valueTwo) -> {
        HashMap<String, Double> word = new HashMap<>();

        for (int x = 0; x < valueOne.size() ; x++) {
          word.put(keyTwo[x], valueTwo[x]);
        }

        result.put(keyOne, (makeNumerator(vectorQuery, word) / makeDenominator(vectorQuery, word)));
      });
    });
    
    return result;
  }
  
  private static Double makeNumerator(
    HashMap<String, Double> vectorOne,
    HashMap<String, Double> vectorTwo
  ) {
    ArrayList<Double> vectorOneList = new ArrayList<>();
    ArrayList<Double> vectorTwoList = new ArrayList<>();
    Double[] vectorOneArray;
    Double[] vectorTwoArray;
    ArrayList<Double> vectorMultiplication = new ArrayList<>();
    
    vectorOne.forEach((key, value) -> vectorOneList.add(value));
    vectorTwo.forEach((key, value) -> vectorTwoList.add(value));
    
    vectorOneArray = vectorOneList.toArray(new Double[vectorOneList.size()]);
    vectorTwoArray = vectorTwoList.toArray(new Double[vectorTwoList.size()]);
    
    if (vectorOneArray.length >= vectorTwoArray.length) {
      for (int x = 0; x < vectorOneArray.length; x++) {
        vectorMultiplication.add(vectorOneArray[x] * vectorTwoArray[x % vectorOneArray.length]);
      }
    } else {
      for (int x = 0; x < vectorTwoArray.length; x++) {
        vectorMultiplication.add(vectorTwoArray[x] * vectorOneArray[x % vectorTwoArray.length]);
      }
    }
    
    return vectorMultiplication.stream().mapToDouble(x -> x).sum();
  }
  
  private static Double makeDenominator(
    HashMap<String, Double> vectorOne,
    HashMap<String, Double> vectorTwo
  ) {
    HashMap<String, Double> vectorOneSquared = new HashMap<String, Double>();
    HashMap<String, Double> vectorTwoSquared = new HashMap<String, Double>();
    ArrayList<Double> vectorOneSquaredList = new ArrayList<>();
    ArrayList<Double> vectorTwoSquaredList = new ArrayList<>();

    Double vectorOneSquaredSum;
    Double vectorTwoSquaredSum;
    
    vectorOne.forEach((key, value) -> {
      vectorOneSquared.put(key, Math.pow(value, 2));
    });
    
    vectorTwo.forEach((key, value) -> {
      vectorTwoSquared.put(key, Math.pow(value, 2));
    });
    
    vectorOneSquared.forEach((key, value) -> {
      vectorOneSquaredList.add(value);
    });
    
    vectorTwoSquared.forEach((key, value) -> {
      vectorTwoSquaredList.add(value);
    });
    
    vectorOneSquaredSum = vectorOneSquaredList.stream().mapToDouble(x -> x).sum();
    vectorTwoSquaredSum = vectorTwoSquaredList.stream().mapToDouble(x -> x).sum();
    
    return (Math.sqrt(vectorOneSquaredSum) * Math.sqrt(vectorTwoSquaredSum));
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
