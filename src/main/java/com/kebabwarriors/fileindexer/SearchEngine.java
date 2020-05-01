package com.kebabwarriors.fileindexer;

import java.util.ArrayList;
import java.util.HashMap;

public final class SearchEngine {
  public static HashMap<String, Double> search(
    String query,
    HashMap<String, String[]> documents,
    HashMap<String, HashMap<String[], Double[]>> indexedDocuments
  ) {
    Main.logger.debug("Received query " + "'" + query + "'" + ".");

    HashMap<String, Double> vectorQuery = tdIdfVector(query, documents);
    
    Main.logger.trace("Vectorizing query " + "'" + query + "'" + ".");
    
    HashMap<String, Double> result = new HashMap<>();
    ArrayList<Double> vectorQueryTerms = new ArrayList<>();
    Double[] vectorQueryTermsArray;

    vectorQuery.forEach((key, value) -> vectorQueryTerms.add(value));

    vectorQueryTermsArray = vectorQueryTerms.toArray(new Double[vectorQueryTerms.size()]);
    
    Main.logger.debug("Calculating cosine similarity between the query and the indexed documents.");

    indexedDocuments.forEach((keyOne, valueOne) -> {
      valueOne.forEach((keyTwo, valueTwo) -> {
        result.put(keyOne, Procedures.cosineSimilarity(vectorQueryTermsArray, valueTwo));
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
      tfIdfVector.put(word, Procedures.tfIdf(vector, documents, word));
    }

    return tfIdfVector;
  }
}
