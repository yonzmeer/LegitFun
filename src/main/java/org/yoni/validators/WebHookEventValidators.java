package org.yoni.validators;

import java.util.List;
import lombok.experimental.UtilityClass;
import org.yoni.models.WebhookEvent;

@UtilityClass
public class WebHookEventValidators {
  private static final List<WebhookEventValidator> VALIDATORS =
      List.of(
          new SiestaPushValidator(),
          new HackerTeamValidator(),
          new RepositoryQuickDeleteValidator());

  public static void run(WebhookEvent webhookEvent) {
    VALIDATORS.forEach(
        validator -> {
          boolean isValid = validator.validate(webhookEvent);

          System.out.printf("%s. valid: %b%n", validator.getName(), isValid);
        });
  }
}
