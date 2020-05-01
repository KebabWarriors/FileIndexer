module file-indexer {
  requires java.io;
  requires java.lang;
  requires java.lang.invoke;
  requires java.util;
  requires java.util.function;
  requires java.util.logging;                                  
  requires org.apache.logging.log4j;
  requires org.apache.pdfbox.pdmodel;
  requires org.apache.pdfbox.text;
  requires org.apache.poi.hwpf;
  requires org.apache.poi.hwpf.extractor;
  requires org.apache.poi.openxml4j.opc;
  requires org.apache.poi.xwpf.extractor;
  requires org.apache.poi.xwpf.usermodel;
  requires py4j;
  exports com.kebabwarriors.fileindexer;
}