package com.kebabwarrios.es;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
    	final File folder = new File("src/documents");
    	Reader reader = new Reader();
    	reader.readDocumentsPath(folder);
    	FileIndexer fileIndexer = new FileIndexer(reader.getAllDocuments());
    	
    	for(String nuevo : fileIndexer.convertTextIntoWordsArray("Hello My name is Victor")) {
    		System.out.println(nuevo);
    	}
    	
    }
}
