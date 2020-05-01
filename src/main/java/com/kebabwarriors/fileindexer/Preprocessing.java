package com.kebabwarriors.fileindexer;

public final class Preprocessing {
  public static String[] convertTextIntoWordsArray(String textToConvert) {
    return textToConvert.toLowerCase().split(" ");
  }
  
  public static String preProcessingText(String oldText) {
    return oldText.replaceAll("[^a-zA-Z0-9]", " ");
  }
}
