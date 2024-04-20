package org.yoni.validators;

import java.time.ZoneOffset;
import java.util.Optional;
import org.yoni.models.Repository;
import org.yoni.models.WebhookEvent;
import org.yoni.utils.TimeUtils;

public class SiestaPushValidator extends WebhookEventValidator {

  @Override
  public String getName() {
    return "Pushed to repository between 14:00-16:00";
  }

  @Override
  public boolean validate(WebhookEvent webhookEvent) {
    return Optional.ofNullable(webhookEvent)
        .map(WebhookEvent::getRepository)
        .map(Repository::getPushedAt)
        .map(TimeUtils::toInstant)
        .map(instant -> instant.atOffset(ZoneOffset.UTC).getHour())
        .map(hour -> hour < 14 || 16 < hour)
        .orElse(true);
  }
}
