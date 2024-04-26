package org.project.bankingapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TransactionRequest(@JsonProperty("to") String to,@JsonProperty("amount") Integer amount) {
}
