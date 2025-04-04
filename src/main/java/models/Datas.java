package models;

import calcul.Dollar;
import exceptions.InvalidDataException;

import java.util.ArrayList;
import java.util.List;

public record Datas (String client, String contract, String month, List<Reclamation> reclamations) {
    public List<Dollar> extractAmounts(List<Reclamation> reclamations) throws InvalidDataException {
        List<Dollar> amounts = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            Dollar amount = new Dollar(reclamation.amount());
            amounts.add(amount);
        }
        return amounts;
    }
}
