package indexer;

import static org.junit.Assert.assertTrue;

import com.kebabwarrios.es.*;
import com.kebabwarrios.es.FileIndexer;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }
    
    @Test
    public void testAddDocumentsToArray() {
    	Reader reader = new Reader();
    	reader.addDocumentToArray(new String[] {"Documento1"});
    	reader.addDocumentToArray(new String[] {"Documento2"});
    	
    	assertTrue(reader.getAllDocuments().size() == 2);
    }
    @Test
    public void testAddToIndexedDocumentsMap() {
    	FileIndexer processor = new FileIndexer();
    	HashMap<String, String[]> documentsMap = new HashMap<String, String[]>();
		 documentsMap.put("primerDocumento", new String[] {"amame","losasdf","vikingos" ,"explorando" ,"las" ,"americas" ,"en","en","en","barcas ","amame","amame","amame","amame","amame","amame","amame","amame","amame","amame","amame"}); 
		 documentsMap.put("SegundoDocumento", new String[]{"los" ,"vis","en" ,"baras ", "different" });
		 documentsMap.put("TercerDocumento", new String[] {"los" ,"vik","ls" ,"am","pa","losasdf","losasdf" }); 
		 documentsMap.put("cuartoDocumento", new String[] {"los" ,"vikingos","en" ,"baras ","losasdf","losasdf" });
		 documentsMap.put("quintoDocumento", new String[] {"los" ,"ingos","e" ,"rcas ","losasdf","losasdf"}); 
		 documentsMap.put("SextoDocumento", new String[]{"los" ,"v","ls" ,"americas" ,"losasdf","losasdf" });
		 documentsMap.put("TercerDocumento 1", new String[] { "vikios","e" ,"barcas ","losasdf" }); 
		 documentsMap.put("TercerDocumento 2", new String[] { "vingos","las" ,"acas" ,"losasdf"}); 
		 documentsMap.put("TercerDocumento 3",new String[] {"lo" ,"vikingos","ls" ,"ricas" ,"losasdf" });
		 processor.indexDocuments(documentsMap);
    	assertTrue(processor.getIndexedDocumentsMap().size() == 9);
    	
    }
    @Test
    public void testIndexedDocumentsWithWordsValue() {
    	 FileIndexer processor = new FileIndexer();
    	 HashMap<String, String[]> documentsMap = new HashMap<String, String[]>();
		 documentsMap.put("primerDocumento", new String[] {"amame","losasdf","vikingos" ,"explorando" ,"las" ,"americas" ,"en","en","en","barcas ","amame","amame","amame","amame","amame","amame","amame","amame","amame","amame","amame"}); 
		 documentsMap.put("SegundoDocumento", new String[]{"los" ,"vis","en" ,"baras ", "different" });
		 documentsMap.put("TercerDocumento", new String[] {"los" ,"vik","ls" ,"am","pa","losasdf","losasdf" }); 
		 documentsMap.put("cuartoDocumento", new String[] {"los" ,"vikingos","en" ,"baras ","losasdf","losasdf" });
		 documentsMap.put("quintoDocumento", new String[] {"los" ,"ingos","e" ,"rcas ","losasdf","losasdf"}); 
		 documentsMap.put("SextoDocumento", new String[]{"los" ,"v","ls" ,"americas" ,"losasdf","losasdf" });
		 documentsMap.put("TercerDocumento 1", new String[] { "vikios","e" ,"barcas ","losasdf" }); 
		 documentsMap.put("TercerDocumento 2", new String[] { "vingos","las" ,"acas" ,"losasdf"}); 
		 documentsMap.put("TercerDocumento 3",new String[] {"lo" ,"vikingos","ls" ,"ricas" ,"losasdf" });
		 processor.indexDocuments(documentsMap);
		 double total = 0;
		 for(String document : processor.getIndexedDocumentsMap().keySet()) {
			 for(String[] words : processor.getIndexedDocumentsMap().get(document).keySet()) {
				 Double[] values = processor.getIndexedDocumentsMap().get(document).get(words);
				 for(int i=0;i<=processor.getIndexedDocumentsMap().get(document).get(words).length-1;i++) {
					 total += values[i]; 
				 }
				
			 }
		 }
    	 
    	 assertTrue( total == 11.199579834337861 );
    }
    
}
