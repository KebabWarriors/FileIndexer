package indexer;

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
    	//System.out.println(reader.getPlainTextContent("src/documents/ejemplo2.txt"));
    	//System.out.println(reader.getDocxTextContent("src/documents/EjemploOffice.docx"));
    	//System.out.println(reader.getDocxTextContent("src/documents/Ejemplodoc.doc"));
    	//System.out.println(reader.getPDFContent("src/documents/ejemplopdf.pdf"));
    	
    	
    }
}
