package com.kebabwarrios.es;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class App 
{
  public static void main( String[] args ) throws FileNotFoundException, IOException
  {
    Reader reader = new Reader();
    reader.readDocumentsPath("src/documents");

    FileIndexer fileIndexer = new FileIndexer(reader.getAllDocuments());

    SearchEngine.searchByQuery("Abraham es una obra ", fileIndexer.getIndexedDocumentsMap());
  }
}
