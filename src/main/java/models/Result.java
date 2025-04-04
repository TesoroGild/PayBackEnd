package models;

import java.util.List;

public record Result (String client, String month, List<Reclamation> reclamations) {
}
