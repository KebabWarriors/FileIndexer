package com.kebabwarriors.fileindexer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public final class Procedures {
  /**
   * @param doc  list of strings
   * @param term String represents a term
   * @return term frequency of term in document
   */
  public static double tf(String[] doc, String term) { 
    double result = 0; 

    for (String word : doc)
      if (term.equalsIgnoreCase(word))
        result += 1;

    return (result / doc.length); 
  }

  /**
   * @param docs list of list of strings represents the dataset
   * @param term String represents a term
   * @return the inverse term frequency of term in documents
   */
  private static double idf(HashMap<String, String[]> docs, String term) { 
    double total = 0; 

    for (String[] words : docs.values())
      for (String word : words)
        if (term.equals(word)) { 
          total += 1; 

          break;   
        }
    
    return idfValidator(docs.size(), total); 
  }

  private static double idfValidator(int docsSize, double total) {
    return (Math.log(docsSize/ total) + 1);
  }

  /**
   * @param doc  a text document
   * @param docs all documents
   * @param term term
   * @return the TF-IDF of term
   */
  public static double tfIdf(String[] doc, HashMap<String, String[]> docs, String term) {
    return (tf(doc, term) * idf(docs, term));
  }
  
  public static double cosineSimilarity(Double[] vectorOne, Double[] vectorTwo) {
    List<Double> vectorOneList = new ArrayList<>(Arrays.asList(vectorOne));
    List<Double> vectorTwoList = new ArrayList<>(Arrays.asList(vectorTwo));
    Double dotProduct = 0.0;
    Double normalVectorOne = 0.0;
    Double normalVectorTwo = 0.0;
    
    if (vectorOneList.size() > vectorTwoList.size())
      while (vectorOneList.size() != vectorTwoList.size())
        vectorTwoList.add((double) 0);
    else
      while (vectorOneList.size() != vectorTwoList.size())
        vectorOneList.add((double) 0);
    
    for (int x = 0; x < vectorOne.length; x += 1) {
      dotProduct = vectorOneList.get(x) * vectorTwoList.get(x);
      normalVectorOne += Math.pow(vectorOneList.get(x), 2);
      normalVectorTwo += Math.pow(vectorTwoList.get(x), 2);
    }
    
    return dotProduct / (Math.sqrt(normalVectorOne) * Math.sqrt(normalVectorTwo));
  }
}
