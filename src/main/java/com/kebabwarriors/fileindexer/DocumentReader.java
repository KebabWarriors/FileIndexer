package com.kebabwarriors.fileindexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class DocumentReader {
  private ArrayList<String[]> documents = new ArrayList<String[]>();

  /**
   * 
   * @param folder
   * @throws IOException 
   * @throws FileNotFoundException 
   */
  public void readDocumentsPath(String path) throws FileNotFoundException, IOException {
    final File folder = new File(path);
    
    Main.logger.debug("Reading documents from path " + path + ".");
    
    for (File fileEntry : folder.listFiles()) {
      if (fileEntry.isDirectory()) {
        Main.logger.trace(
            "File " + fileEntry.getName() + " at " + fileEntry.getAbsolutePath() + " is a directory.");
        
        this.readDocumentsPath(fileEntry);
      } else {
        Main.logger.trace(
            "File " + fileEntry.getName() + " at path " + fileEntry.getPath() + "is a document, adding to list.");

        this.addDocumentToArray(new String[] {
            fileEntry.getPath(),
            fileEntry.getName(),
            this.getFileExtension(fileEntry),
            this.getGeneralDocumentsContent(fileEntry.getPath(), this.getFileExtension(fileEntry))
        });
      }
    }
  }

  /*
   * Override with different parameter.
   */
  public void readDocumentsPath(File folder) throws FileNotFoundException, IOException {
    Main.logger.debug(
        "Reading documents from directory " + folder.getName() + " at path " + folder.getAbsolutePath() + ".");

    for (File fileEntry : folder.listFiles())
      if (fileEntry.isDirectory()) {
        Main.logger.trace(
            fileEntry.getName() + " at " + fileEntry.getAbsolutePath() + " is a directory.");

        this.readDocumentsPath(fileEntry);
      } else {
        Main.logger.trace(
            fileEntry.getName() + " at " + fileEntry.getAbsolutePath() + " is a document, adding to list.");

        this.addDocumentToArray(new String[]{
            fileEntry.getPath(),
            fileEntry.getName(),
            this.getFileExtension(fileEntry),
            this.getGeneralDocumentsContent(fileEntry.getPath(), this.getFileExtension(fileEntry))
        });
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
    String content = "";
    
    Main.logger.debug("Reading document at " + path + " with the extension " + extension + ".");

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
      Main.logger.debug("Document's extension is not supported. Skipping.");
      break;
    }
    return content;
  }

  /**
   * 
   * @param path
   */
  private void addDocumentToArray(String[] documents) {
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

    if (lastIndexOf == (-1))
      return ""; // empty extension

    return name.substring(lastIndexOf);
  }

  /**
   * 
   * @param path
   * @return
   * @throws FileNotFoundException
   * @throws IOException
   */
  private String getPlainTextContent(String path) throws FileNotFoundException, IOException {
    String text;

    try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
      StringBuilder builder = new StringBuilder();
      String line = reader.readLine();

      while (line != null) {
        builder.append(line);
        builder.append(System.lineSeparator());

        line = reader.readLine();
      }

      text = builder.toString();
    }

    return text;
  }

  /**
   * 
   * @param path
   * @return
   */
  private String getDocxTextContent(String path) {
    String docText = "";

    try {
      FileInputStream fis = new FileInputStream(path);
      XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
      XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);

      docText = extractor.getText();
      
      extractor.close();
    } catch(Exception ex) {
      ex.printStackTrace();
    }

    return docText;
  }

  /*
   * @throws IOException 
   */
  private String getPDFContent(String path) throws IOException {
    String content = "";
    PDDocument document = PDDocument.load(new File(path));

    if (!document.isEncrypted()) {
      PDFTextStripper stripper = new PDFTextStripper();
      String text = stripper.getText(document);

      content = text;
    }

    document.close();
    return content;
  }
}
