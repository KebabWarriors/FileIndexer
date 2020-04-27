package com.kebabwarriors.file_indexer;

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
    java.util.logging.Logger
      .getLogger("org.apache.fontbox").setLevel(java.util.logging.Level.SEVERE);    

    Reader reader = new Reader();
    reader.readDocumentsPath("src/documents");

    FileIndexer fileIndexer = new FileIndexer(reader.getAllDocuments());

    HashMap<String, Double> lol = SearchEngine.search("El Libro de Abraham es una obra de 1835 producida por el fundador del Movimiento de los Santos de los Últimos Días, Joseph Smith, que según él se basó", fileIndexer.getDocumentsMap(), fileIndexer.getIndexedDocumentsMap());
    
    lol.forEach((key, value) -> System.out.println("Documento: " + key + ", coincidencia: " + value));
  }
}
