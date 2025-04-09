package models;

import calcul.Dollar;
import exceptions.ApplicationException;

import java.util.ArrayList;
import java.util.List;

public record Datas (String directory, String month, List<Reclamation> reclamations) {
    public List<Dollar> extractAmounts(List<Reclamation> reclamations) throws ApplicationException {
        List<Dollar> amounts = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            Dollar amount = new Dollar(reclamation.amount());
            amounts.add(amount);
        }
        return amounts;
    }
}
