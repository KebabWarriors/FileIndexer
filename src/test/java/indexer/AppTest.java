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
    public void testTF() {
    	double tf = MathProcedures.tf(new String[] {"uno","uno","cinco","cuatro","tres","dos"}, "uno");
    	
    	assertTrue( tf == 0.3333333333333333 );
    	
    }
    @Test
    public void testIDFValidator() {
    	HashMap<String, String[]> documents = new HashMap<String, String[]>();
    	documents.put("documento 1", new String[] {"uno","uno","cinco","cuatro","tres","dos"});
    	double tfidf = MathProcedures.tfIdf(new String[] {"uno","uno","cinco","cuatro","tres","dos"}, documents , "uno");
    	 
    	assertTrue( tfidf ==  0.3333333333333333);
    }
    @Test
    public void testPreProcessingTest() {
    	String text = "Hey que ondas? viejo!";
    	
    	assertTrue(Preprocessing.preProcessingText(text).contentEquals("Hey que ondas  viejo "));
    }
    
}
