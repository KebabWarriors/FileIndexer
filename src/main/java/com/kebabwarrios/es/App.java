package com.kebabwarrios.es;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
    	//final File folder = new File("src/documents");
    	Reader reader = new Reader();
    	reader.readDocumentsPath("src/documents");
    	FileIndexer fileIndexer = new FileIndexer(reader.getAllDocuments());
    	
    	//System.out.println(fileIndexer.preProcessingText("HEY! this is a try/?///"));
		/*
		 * HashMap<String, String[]> documentsMap = new HashMap<String, String[]>();
		 * documentsMap.put("primerDocumento", new String[] {"amame","losasdf"
		 * ,"vikingos" ,"explorando" ,"las" ,"americas" ,"en","en","en"
		 * ,"barcas ","amame","amame","amame","amame","amame","amame","amame","amame",
		 * "amame","amame","amame"}); documentsMap.put("SegundoDocumento", new String[]
		 * {"los" ,"vis","en" ,"baras ", "different" });
		 * documentsMap.put("TercerDocumento", new String[] {"los" ,"vik","ls" ,"am"
		 * ,"pa","losasdf","losasdf" }); documentsMap.put("cuartoDocumento", new
		 * String[] {"los" ,"vikingos","en" ,"baras ","losasdf","losasdf" });
		 * documentsMap.put("quintoDocumento", new String[] {"los" ,"ingos","e" ,"rcas "
		 * ,"losasdf","losasdf"}); documentsMap.put("SextoDocumento", new String[]
		 * {"los" ,"v","ls" ,"americas" ,"losasdf","losasdf" });
		 * documentsMap.put("TercerDocumento 1", new String[] { "vikios","e"
		 * ,"barcas ","losasdf" }); documentsMap.put("TercerDocumento 2", new String[] {
		 * "vingos","las" ,"acas" ,"losasdf"}); documentsMap.put("TercerDocumento 3",
		 * new String[] {"lo" ,"vikingos","ls" ,"ricas" ,"losasdf" });
		 * fileIndexer.indexDocuments(documentsMap);
		 */
		/*
		 * fileIndexer.getIndexedDocumentsMap().forEach((key,value) -> {
		 * value.forEach((key2,value2)->{ for(Double valor : value2) {
		 * System.out.println(valor); } }); });
		 */
    	
    	SearchEngine.searchByQuery("Abraham", fileIndexer.getIndexedDocumentsMap());
    	//System.out.println( fileIndexer.tfIdf(new String[] {"los" ,"vikingos" ,"explorando" ,"las" ,"americas" ,"en", "las" ,"barcas "}, documentsMap, "las"));
		/*
		 * for(String nuevo :
		 * fileIndexer.convertTextIntoWordsArray("Hello My name is VIctor")) {
		 * System.out.println(nuevo); }
		 */
    	
    }
}
