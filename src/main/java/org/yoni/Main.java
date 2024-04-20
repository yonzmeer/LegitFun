package org.yoni;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import org.yoni.listener.WebhookListener;

public class Main {
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  public static void main(String[] args) throws Exception {
    LOGGER.info("Starting to listen to webhook messages");

    HttpServer server = HttpServer.create(new InetSocketAddress(3000), 0);

    server.createContext("/", new WebhookListener());
    server.setExecutor(Executors.newCachedThreadPool());

    server.start();
  }
}
