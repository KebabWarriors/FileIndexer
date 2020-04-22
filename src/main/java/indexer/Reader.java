package indexer;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Reader {
	private ArrayList<String[]> documentsPath;
	
	public void readDocumentsPath(final File folder) {
		String[] files;
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	readDocumentsPath(fileEntry);
	        } else {
	            System.out.println(fileEntry.getPath());
	            System.out.println(fileEntry.getName());
	            System.out.println(this.getFileExtension(fileEntry));
	        }
	    }
	}
	
	public void addPathToArray(String[] path) {
		this.documentsPath.add(path);
	}
	
	public ArrayList<String[]> getAllDocumentSPath() {
		return this.documentsPath;
	}
	
	private String getFileExtension(File file) {
	    String name = file.getName();
	    int lastIndexOf = name.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return ""; // empty extension
	    }
	    return name.substring(lastIndexOf);
	}
	
	public String getPlainTextContent(String path) throws FileNotFoundException, IOException {
		String everything;
		try(BufferedReader reader = new BufferedReader(new FileReader(path))) {
		    StringBuilder builder = new StringBuilder();
		    String line = reader.readLine();
	
		    while (line != null) {
		        builder.append(line);
		        builder.append(System.lineSeparator());
		        line = reader.readLine();
		    }
		    everything = builder.toString();
		    
		}
		return everything;
		
	}
}
