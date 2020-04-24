package com.kebabwarrios.es;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper; 
import org.apache.pdfbox.text.PDFTextStripperByArea;


public class Reader {
	private ArrayList<String[]> documents = new ArrayList<String[]>();
	/**
	 * 
	 * @param folder
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public void readDocumentsPath(final File folder) throws FileNotFoundException, IOException {
		String[] files;
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	readDocumentsPath(fileEntry);
	        } else {
	        	this.addDocumentToArray(new String[]{
	        			fileEntry.getPath(),
	        			fileEntry.getName(),
	        			this.getFileExtension(fileEntry),
	        			this.getGeneralDocumentsContent(fileEntry.getPath(), this.getFileExtension(fileEntry))
	        		});
	            /*System.out.println(fileEntry.getPath());
	            System.out.println(fileEntry.getName());
	            System.out.println(this.getFileExtension(fileEntry));
	            this.getGeneralDocumentsContent(fileEntry.getPath(), this.getFileExtension(fileEntry));*/
	        }
	    }
	}
	/**
	 * 
	 * @param path
	 * @param extension
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public String getGeneralDocumentsContent(String path, String extension) throws FileNotFoundException, IOException {
		String content ="";
		switch(extension) {
		case ".docx":
			content = this.getDocxTextContent(path);
			break;
		case ".txt":
			content = this.getPlainTextContent(path);
			break;
		case ".pdf":
			content = this.getPDFContent(path);
			break;
		default:
			//has to send a log 
			break;
		}
		return content;
	}
	/**
	 * 
	 * @param path
	 */
	public void addDocumentToArray(String[] documents) {
		this.documents.add(documents);
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<String[]> getAllDocuments() {
		return this.documents;
	}
	/**
	 * 
	 * @param file
	 * @return
	 */
	private String getFileExtension(File file) {
	    String name = file.getName();
	    int lastIndexOf = name.lastIndexOf(".");
	    if (lastIndexOf == -1) {
	        return ""; // empty extension
	    }
	    return name.substring(lastIndexOf);
	}
	/**
	 * 
	 * @param path
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
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
	/**
	 * 
	 * @param path
	 * @return
	 */
	public String getDocxTextContent(String path) {
		String docText = "";
		try {
		   FileInputStream fis = new FileInputStream(path);
		   XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
		   XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
		   
		   docText = extractor.getText();
		} catch(Exception ex) {
		    ex.printStackTrace();
		}
		return docText;
	}
	/**
	 * 
	 * @param path
	 * @return
	 */
	public String getDocTextContent(String path) {
		String docText = "";
		try {
			File file = new File(path);
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());

			HWPFDocument doc = new HWPFDocument(fis);
			WordExtractor we = new WordExtractor(doc);
			String[] paragraphs = we.getParagraphText();

			for (String para : paragraphs) {
				docText += para.toString();
			}
			fis.close();
			we.close();
		} catch (Exception e) {
			//logger.error(e);
			e.printStackTrace();
		}
			
		
		return docText;
	}
	/**
	 * 
	 * @param path
	 * @return
	 * @throws IOException 
	 */
	public String getPDFContent(String path) throws IOException {
		String content = "";
		PDDocument document = PDDocument.load(new File(path));
		if (!document.isEncrypted()) {
		    PDFTextStripper stripper = new PDFTextStripper();
		    String text = stripper.getText(document);
		    content = text;
		    //System.out.println("Text:" + text);
		}
		document.close();
		return content;
	}
}
