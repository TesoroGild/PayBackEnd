package calcul;

import exceptions.ApplicationException;
import models.Reclamation;

import java.util.ArrayList;
import java.util.List;

public class Refund {
    public static List<Dollar> amountRefund(String contract, List<Reclamation> reclamations) throws ApplicationException {
        if (contract.equals("A")) return contractA(reclamations);
        if (contract.equals("B")) return contractB(treatmentNumber, amountSpent);
        if (contract.equals("C")) return contractC(treatmentNumber, amountSpent);
        if (contract.equals("D")) return contractD(treatmentNumber, amountSpent);
        throw new ApplicationException("Contrat de soin inattendue.");
    }

    private static List<Dollar> contractA(List<Reclamation> reclamations) throws ApplicationException {
        List<Dollar> amountsRef = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if ("0".equals(reclamation.healthCare()) || "100".equals(reclamation.healthCare())
                    || "200".equals(reclamation.healthCare()) || "500".equals(reclamation.healthCare())) amountsRef.add(treatmentA025Percent(reclamation.amount()));
            if ((300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399)
                    || "400".equals(reclamation.healthCare()) || "700".equals(reclamation.healthCare())) amountsRef.add(new Dollar("00.00$"));
            if ("600".equals(reclamation.healthCare())) amountsRef.add(treatmentA040Percent(reclamation.amount()));
            else throw new ApplicationException("Catégorie de soin inattendue.");
        }
        return amountsRef;
    }

    private static Dollar treatmentA025Percent(String amountSpent) throws ApplicationException {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(25);
    }

    private static Dollar treatmentA040Percent (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(40);
    }

    private Dollar treatmentA2 (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        Dollar refund = reclamation.calculatePercentage(25);
        if (refund.isGreaterThan(5000)) return new Dollar("50.00$");
        return refund;
    }

    private Dollar treatmentA3 (String amountSpent) throws ApplicationException  {

    }

    private Dollar treatmentA4 (String amountSpent) throws ApplicationException  {

    }

    private Dollar treatmentA5 (String amountSpent) throws ApplicationException  {

    }

    private Dollar treatmentA6 (String amountSpent) throws ApplicationException  {

    }

    private Dollar treatmentA7 (String amountSpent) throws ApplicationException  {

    }

    private void contractAOsteopathy0 () throws ApplicationException  {

    }

    private void contractAIndividualPsychology () throws ApplicationException  {

    }

    private void contractADentalCare () throws ApplicationException  {

    }

    private void contractANaturopathyAcupuncture () throws ApplicationException  {

    }

    private void contractAChiropractic () throws ApplicationException  {

    }

    private void contractAPhysicalTherapy () throws ApplicationException  {

    }

    private void contractASpeechOccupationalTherapy () throws ApplicationException  {

    }

    private Dollar contractAOsteopathy0 () throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        Dollar refund = reclamation.calculatePercentage(25);
        if (refund.isGreaterThan(5000)) return new Dollar("50.00$");
        return refund;
    }

}
