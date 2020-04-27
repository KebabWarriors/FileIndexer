package com.kebabwarrios.es;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public class App 
{
  public static void main( String[] args ) throws FileNotFoundException, IOException
  {
    Reader reader = new Reader();
    reader.readDocumentsPath("src/documents");

    FileIndexer fileIndexer = new FileIndexer(reader.getAllDocuments());

    HashMap<String, Double> lol = Searcher.search("Abraham es una obra", fileIndexer.getDocumentsMap());
    HashMap<String, Double> lolSquared = new HashMap<>();
    HashMap<String, HashMap<String[], Double[]>> indexedDocuments = fileIndexer.getIndexedDocumentsMap();
    HashMap<String, Double[]> cosineVectors = new HashMap<>();
    HashMap<String, Double> cosineVectorsDenominator = new HashMap<>();
    HashMap<String, Double> similarity = new HashMap<>();
    
    lol.forEach((key, value) -> {
      lolSquared.put(key, Math.pow(value, 2));
    });
    
    indexedDocuments.forEach((key, value) -> {
      System.out.println("Similarity with: " + key);
      ArrayList<Double> documentSquared = new ArrayList<>();
      ArrayList<Double> multiplicationProduct = new ArrayList<>();
      
      value.forEach((x, y) -> {
        for (int j = 0; j < y.length; j++)
          documentSquared.add(Math.pow(j, 2));
      });
      
      lolSquared.forEach((z, l) -> {
        documentSquared.forEach(square -> {
          multiplicationProduct.add(l * square);
        });
      });
      
      AtomicLong product = new AtomicLong(Double.doubleToLongBits(0));
      
      multiplicationProduct.forEach(o -> {
        product.set(product.get() + Double.doubleToLongBits(o));
      });
      
      cosineVectorsDenominator.put(key, product.doubleValue());
    });
    
    cosineVectorsDenominator.forEach((key, value) -> {
      System.out.println(key + ": " + value);
    });
  }
}
