package calcul;

import exceptions.ApplicationException;
import models.Reclamation;

import java.util.ArrayList;
import java.util.List;

public class Refund {
    public static List<Dollar> amountRefund(String contract, List<Reclamation> reclamations) throws ApplicationException {
        if (contract.equals("A")) return contractA(reclamations);
        /*if (contract.equals("B")) return contractB(treatmentNumber, amountSpent);
        if (contract.equals("C")) return contractC(treatmentNumber, amountSpent);
        if (contract.equals("D")) return contractD(treatmentNumber, amountSpent);*/
        throw new ApplicationException("Contrat de soin inattendue.");
    }

    private static List<Dollar> contractA(List<Reclamation> reclamations) throws ApplicationException {
        List<Dollar> amountsRef = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if ("0".equals(reclamation.healthCare())) amountsRef.add(treatmentA0(reclamation.amount()));
            else throw new ApplicationException("Catégorie de soin inattendue.");
        }
        return amountsRef;
        /*if (treatmentNumber == 100) return treatmentA1(amountSpent);
        if (treatmentNumber == 200) return treatmentA2(amountSpent);
        if (300 <= treatmentNumber && treatmentNumber <= 399) return treatmentA3(amountSpent);
        if (treatmentNumber == 400) return treatmentA4(amountSpent);
        if (treatmentNumber == 500) return treatmentA5(amountSpent);
        if (treatmentNumber == 600) return treatmentA6(amountSpent);
        if (treatmentNumber == 700) return treatmentA7(amountSpent);*/
    }

    private static Dollar treatmentA0(String amountSpent) throws ApplicationException {
        Dollar reclamation = new Dollar(amountSpent);
        Dollar refund = reclamation.calculatePercentage(25);
        if (refund.isGreaterThan(4000)) return new Dollar("40.00$");
        return refund;
    }

    /*private Dollar treatmentA1 (String amountSpent) {

    }

    private Dollar treatmentA2 (String amountSpent) {

    }

    private Dollar treatmentA3 (String amountSpent) {

    }

    private Dollar treatmentA4 (String amountSpent) {

    }

    private Dollar treatmentA5 (String amountSpent) {

    }

    private Dollar treatmentA6 (String amountSpent) {

    }

    private Dollar treatmentA7 (String amountSpent) {

    }

    private void contractAOsteopathy0 () {

    }

    private void contractAIndividualPsychology () {

    }

    private void contractADentalCare () {

    }

    private void contractANaturopathyAcupuncture () {

    }

    private void contractAChiropractic () {

    }

    private void contractAPhysicalTherapy () {

    }

    private void contractASpeechOccupationalTherapy () {

    }*/

}
