package com.kebabwarriors.file_indexer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import py4j.GatewayServer;

public class App 
{
  public static void main( String[] args ) throws FileNotFoundException, IOException
  {
    java.util.logging.Logger
      .getLogger("org.apache.fontbox").setLevel(java.util.logging.Level.SEVERE);    
    
    GatewayServer gatewayServer = new GatewayServer(null);
    gatewayServer.start();
    System.out.println("Gateway started at: " + gatewayServer.getPort());
  }
}
