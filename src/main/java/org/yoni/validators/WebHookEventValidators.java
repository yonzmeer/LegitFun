package org.yoni.validators;

import java.util.List;
import java.util.logging.Logger;
import lombok.experimental.UtilityClass;
import org.yoni.models.WebhookEvent;

@UtilityClass
public class WebHookEventValidators {
  private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  private static final List<WebhookEventValidator> VALIDATORS =
      List.of(
          new SiestaPushValidator(),
          new HackerTeamValidator(),
          new RepositoryQuickDeleteValidator());

  public static void run(WebhookEvent webhookEvent) {
    LOGGER.info(String.format("Running all validators on %s", webhookEvent));

    VALIDATORS.forEach(
        validator -> {
          boolean isValid = validator.validate(webhookEvent);

          LOGGER.info(
              String.format("Validator: %s%nResult is: %b%n", validator.getName(), isValid));
        });
  }
}
