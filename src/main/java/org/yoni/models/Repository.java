package org.yoni.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Repository {
  @JsonProperty("pushed_at")
  String pushedAt;

  @JsonProperty("created_at")
  String createdAt;

  @JsonProperty("updated_at")
  String updatedAt;
}
