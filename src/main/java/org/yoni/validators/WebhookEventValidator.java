package org.yoni.validators;

import org.yoni.models.WebhookEvent;

public abstract class WebhookEventValidator {

  public abstract String getName();

  public abstract boolean validate(WebhookEvent webhookEvent);
}
