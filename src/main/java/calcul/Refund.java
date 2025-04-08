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

    private static List<Dollar> contractA (List<Reclamation> reclamations) throws ApplicationException {
        List<Dollar> amountsRefA = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if ("0".equals(reclamation.healthCare()) || "100".equals(reclamation.healthCare())
                    || "200".equals(reclamation.healthCare()) || "500".equals(reclamation.healthCare())) amountsRefA.add(treatmentA25Percent(reclamation.amount()));
            if ((300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399)
                    || "400".equals(reclamation.healthCare()) || "700".equals(reclamation.healthCare())) amountsRefA.add(new Dollar("00.00$"));
            if ("600".equals(reclamation.healthCare())) amountsRefA.add(treatmentA40Percent(reclamation.amount()));
            else throw new ApplicationException("Catégorie de soin inattendue.");
        }
        return amountsRefA;
    }

    private static List<Dollar> contractB (List<Reclamation> reclamations) throws ApplicationException {
        List<Dollar> amountsRefB = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if ("0".equals(reclamation.healthCare())) amountsRefB.add(treatmentB50Percent40Max(reclamation.amount()));
            if ("100".equals(reclamation.healthCare()) || "500".equals(reclamation.healthCare())) amountsRefB.add(treatmentB50Percent50Max(reclamation.amount()));
            if ("200".equals(reclamation.healthCare())) amountsRefB.add(treatmentB100Percent70Max(reclamation.amount()));
            if ((300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399)) amountsRefB.add(treatmentB50PercentNoMax(reclamation.amount()));
            if ("400".equals(reclamation.healthCare())) amountsRefB.add(new Dollar("00.00$"));
            if ("600".equals(reclamation.healthCare())) amountsRefB.add(treatmentB100PercentNoMax(reclamation.amount()));
            if ("700".equals(reclamation.healthCare())) amountsRefB.add(treatmentB70PercentNoMax(reclamation.amount()));
            else throw new ApplicationException("Catégorie de soin inattendue.");
        }
        return amountsRefB;
    }

    private static List<Dollar> contractC (List<Reclamation> reclamations) throws ApplicationException {
        String validTreatment = "0, 100, 200, 400, 500, 600, 700";
        List<Dollar> amountsRefC = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if (validTreatment.contains(reclamation.healthCare())
                    || (300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399))
                amountsRefC.add(treatmentC(reclamation.amount()));
            else throw new ApplicationException("Catégorie de soin inattendue.");
        }
        return amountsRefC;
    }

    private static List<Dollar> contractD (List<Reclamation> reclamations) throws ApplicationException {
        List<Dollar> amountsRefD = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if ("0".equals(reclamation.healthCare())) amountsRefD.add(treatmentD85Max(reclamation.amount()));
            if ("100".equals(reclamation.healthCare()) || "500".equals(reclamation.healthCare())) amountsRefD.add(treatmentD75Max(reclamation.amount()));
            if ("200".equals(reclamation.healthCare()) || "600".equals(reclamation.healthCare())) amountsRefD.add(treatmentD100Max(reclamation.amount()));
            if (300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399) amountsRefD.add(new Dollar(reclamation.amount()));
            if ("400".equals(reclamation.healthCare())) amountsRefD.add(treatmentD65Max(reclamation.amount()));
            if ("700".equals(reclamation.healthCare())) amountsRefD.add(treatmentD90Max(reclamation.amount()));
            else throw new ApplicationException("Catégorie de soin inattendue.");
        }
        return amountsRefD;
    }

    private static Dollar treatmentA25Percent(String amountSpent) throws ApplicationException {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(25);
    }

    private static Dollar treatmentA40Percent (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(40);
    }

    private static Dollar treatmentB50Percent40Max(String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        Dollar refund = reclamation.calculatePercentage(50);
        if (refund.isGreaterThan(4000)) return new Dollar("40.00$");
        return refund;
    }

    private static Dollar treatmentB50Percent50Max(String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        Dollar refund = reclamation.calculatePercentage(50);
        if (refund.isGreaterThan(5000)) return new Dollar("50.00$");
        return refund;
    }

    private static Dollar treatmentB50PercentNoMax(String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(50);
    }

    private static Dollar treatmentB70PercentNoMax(String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(70);
    }

    private static Dollar treatmentB100Percent70Max(String amountSpent) throws ApplicationException  {
        Dollar refund = new Dollar(amountSpent);
        if (refund.isGreaterThan(7000)) return new Dollar("70.00$");
        return refund;
    }

    private static Dollar treatmentB100PercentNoMax(String amountSpent) throws ApplicationException  {
        return new Dollar(amountSpent);
    }

    private static Dollar treatmentC (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(90);
    }

    private Dollar treatmentD65Max (String amountSpent) throws ApplicationException  {

    }

    private Dollar treatmentD75Max (String amountSpent) throws ApplicationException  {

    }

    private Dollar treatmentD85Max (String amountSpent) throws ApplicationException  {

    }

    private Dollar treatmentD90Max (String amountSpent) throws ApplicationException  {

    }

    private Dollar treatmentD100Max (String amountSpent) throws ApplicationException  {

    }
}
