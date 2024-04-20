package org.yoni.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.yoni.models.WebhookEvent;
import org.yoni.validators.WebHookEventValidators;

@RequiredArgsConstructor
public class WebhookListener implements HttpHandler {
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  private static final ObjectMapper OBJECT_MAPPER =
      new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @Override
  public void handle(HttpExchange exchange) {
    if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
      return;
    }

    LOGGER.fine("Got a POST request!");
    var requestBody = exchange.getRequestBody();
    LOGGER.finer(String.format("Body is %s", requestBody));

    createWebhookEvent(requestBody).ifPresent(WebHookEventValidators::run);
  }

  private static Optional<WebhookEvent> createWebhookEvent(InputStream requestBody) {
    var scanner = new Scanner(requestBody).useDelimiter("\\A");
    if (!scanner.hasNext()) {
      return Optional.empty();
    }

    var body = scanner.next();
    try {
      LOGGER.finer("Converting body to WeHookEvent");

      var webhookEvent = OBJECT_MAPPER.readValue(body, WebhookEvent.class);

      return Optional.of(webhookEvent);
    } catch (JsonProcessingException e) {
      LOGGER.log(Level.SEVERE, "Failed to convert body", e);

      return Optional.empty();
    }
  }
}
