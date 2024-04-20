package org.yoni;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import org.yoni.listener.WebhookListener;

public class Main {

  public static void main(String[] args) throws Exception {
    HttpServer server = HttpServer.create(new InetSocketAddress(3000), 0);
    server.createContext("/", new WebhookListener());
    server.setExecutor(Executors.newCachedThreadPool());
    server.start();
  }
}
