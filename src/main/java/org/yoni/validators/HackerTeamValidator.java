package org.yoni.validators;

import java.util.Optional;
import org.yoni.models.Team;
import org.yoni.models.WebhookEvent;

public class HackerTeamValidator extends WebhookEventValidator {
  @Override
  public boolean validate(WebhookEvent webhookEvent) {
    return Optional.ofNullable(webhookEvent)
        .filter(HackerTeamValidator::teamCreated)
        .map(WebhookEvent::getTeam)
        .map(Team::getName)
        .map(HackerTeamValidator::validateName)
        .orElse(true);
  }

  @Override
  public String getName() {
    return "Team name starts with hacker";
  }

  private static boolean teamCreated(WebhookEvent webhookEvent) {
    return "created".equals(webhookEvent.getAction());
  }

  private static Boolean validateName(String name) {
    return !name.startsWith("hacker");
  }
}
