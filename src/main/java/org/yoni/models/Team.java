package org.yoni.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Team {
  @JsonProperty("name")
  String name;
}
