package org.yoni.validators;

import static org.yoni.utils.TimeUtils.toInstant;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import org.yoni.models.Repository;
import org.yoni.models.WebhookEvent;

public class RepositoryQuickDeleteValidator extends WebhookEventValidator {

  @Override
  public String getName() {
    return "Repository deleted less than 10 minutes after creation";
  }

  @Override
  public boolean validate(WebhookEvent webhookEvent) {
    return Optional.ofNullable(webhookEvent)
        .filter(RepositoryQuickDeleteValidator::repositoryDeleted)
        .map(WebhookEvent::getRepository)
        .map(RepositoryQuickDeleteValidator::validateDeletionTime)
        .orElse(true);
  }

  private static boolean repositoryDeleted(WebhookEvent webhookEvent) {
    return "deleted".equals(webhookEvent.getAction());
  }

  public static boolean validateDeletionTime(Repository repository) {
    Instant creation = toInstant(repository.getCreatedAt());
    Instant deletion = toInstant(repository.getUpdatedAt());

    return deletion.minus(10, ChronoUnit.MINUTES).isAfter(creation);
  }
}
