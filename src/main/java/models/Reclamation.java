package models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Reclamation (@JsonProperty("soin") String healthCare, String date, @JsonProperty("montant") String amount) {
}
