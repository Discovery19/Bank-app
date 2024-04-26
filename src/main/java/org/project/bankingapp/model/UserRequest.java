package org.project.bankingapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserRequest(@JsonProperty("login") String login, @JsonProperty("password") String password) {
}
