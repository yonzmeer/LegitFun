package org.yoni.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WebhookEvent {
  @JsonProperty("action")
  String action;

  @JsonProperty("repository")
  Repository repository;

  @JsonProperty("team")
  Team team;
}
