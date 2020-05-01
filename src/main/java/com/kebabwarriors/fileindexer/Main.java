package com.kebabwarriors.fileindexer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import py4j.GatewayServer;

public class Main {
  public static final Logger logger = LogManager.getLogger("com.kebabwarriors.fileindexer.FileIndexer");

  public static void main(String[] args) {
    GatewayServer gatewayServer = new GatewayServer(null);
    gatewayServer.start();
    logger.info("Gateway started at port " + gatewayServer.getListeningPort() + ".");
    
    java.util.logging.Logger
      .getLogger("org.apache.fontbox").setLevel(java.util.logging.Level.SEVERE);
  }
}