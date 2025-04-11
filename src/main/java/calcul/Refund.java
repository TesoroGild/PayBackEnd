package calcul;

import exceptions.ApplicationException;
import models.Reclamation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Refund {
    public static List<Dollar> amountRefund(String contract, List<Reclamation> reclamations) throws ApplicationException {
        if (contract.equals("A")) return contractA(reclamations);
        if (contract.equals("B")) return contractB(reclamations);
        if (contract.equals("C")) return contractC(reclamations);
        if (contract.equals("D")) return contractD(reclamations);
        if (contract.equals("E")) return contractE(reclamations);
        throw new ApplicationException("Contrat de soin inattendue.");
    }

    private static List<Dollar> contractA (List<Reclamation> reclamations) throws ApplicationException {
        Map<String, Dollar> totalRefundA = new HashMap<>();
        List<Dollar> amountsRefundA = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if ("0".equals(reclamation.healthCare())) amountsRefundA.add(getReclamationAmount(reclamation.amount(), 25));
            else if ("100".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundA, amountsRefundA, reclamation.healthCare(), reclamation.amount(), 25000, 35);
            } else if ("150".equals(reclamation.healthCare()) ||(300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399)
                    || "400".equals(reclamation.healthCare()) || "700".equals(reclamation.healthCare())) amountsRefundA.add(new Dollar("00.00$"));
            else if ("175".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundA, amountsRefundA, reclamation.healthCare(), reclamation.amount(), 20000, 50);
            } else if ("200".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundA, amountsRefundA, reclamation.healthCare(), reclamation.amount(), 25000, 35);
            } else if ("500".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundA, amountsRefundA, reclamation.healthCare(), reclamation.amount(), 15000, 25);
            } else if ("600".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundA, amountsRefundA, reclamation.healthCare(), reclamation.amount(), 30000, 40);
            } else throw new ApplicationException("Numéro de soin inattendue.");
        }
        return amountsRefundA;
    }

    private static List<Dollar> contractB (List<Reclamation> reclamations) throws ApplicationException {
        Map<String, Dollar> totalRefundB = new HashMap<>();
        List<Dollar> amountsRefundB = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if ("0".equals(reclamation.healthCare())) amountsRefundB.add(getReclamationAmountMax(reclamation.amount(), 50, 4000));
            else if ("100".equals(reclamation.healthCare())) {
                getMaxRefundAndTotal(totalRefundB, amountsRefundB, reclamation.healthCare(), reclamation.amount(), 25000,  5000,50);
            } else if ("150".equals(reclamation.healthCare()) || "400".equals(reclamation.healthCare())) amountsRefundB.add(new Dollar("00.00$"));
            else if ("175".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundB, amountsRefundB, reclamation.healthCare(), reclamation.amount(), 20000,75);
            } else if ("200".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundB, amountsRefundB, reclamation.healthCare(), reclamation.amount(), 25000,  100);
            } else if ((300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399)) amountsRefundB.add(getReclamationAmount(reclamation.amount(), 50));
            else if ("500".equals(reclamation.healthCare())) {
                getMaxRefundAndTotal(totalRefundB, amountsRefundB, reclamation.healthCare(), reclamation.amount(), 15000,  5000,50);
            } else if ("600".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundB, amountsRefundB, reclamation.healthCare(), reclamation.amount(), 30000,  100);
            } else if ("700".equals(reclamation.healthCare())) amountsRefundB.add(getReclamationAmount(reclamation.amount(), 70));
            else throw new ApplicationException("Numéro de soin inattendue.");
        }
        return amountsRefundB;
    }

    private static List<Dollar> contractC (List<Reclamation> reclamations) throws ApplicationException {
        String validTreatment = "0, 400, 700";
        Map<String, Dollar> totalRefundC = new HashMap<>();
        List<Dollar> amountsRefundC = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if (validTreatment.contains(reclamation.healthCare())
                    || (300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399))
                amountsRefundC.add(getReclamationAmount(reclamation.amount(), 90));
            else if ("100".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundC, amountsRefundC, reclamation.healthCare(), reclamation.amount(), 25000, 95);
            } else if ("150".equals(reclamation.healthCare())) amountsRefundC.add(getReclamationAmount(reclamation.amount(), 85));
            else if ("175".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundC, amountsRefundC, reclamation.healthCare(), reclamation.amount(), 20000, 90);
            } else if ("200".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundC, amountsRefundC, reclamation.healthCare(), reclamation.amount(), 25000, 90);
            } else if ("500".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundC, amountsRefundC, reclamation.healthCare(), reclamation.amount(), 15000, 90);
            } else if ("600".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundC, amountsRefundC, reclamation.healthCare(), reclamation.amount(), 30000, 75);
            } else throw new ApplicationException("Numéro de soin inattendue.");
        }
        return amountsRefundC;
    }

    private static List<Dollar> contractD (List<Reclamation> reclamations) throws ApplicationException {
        Map<String, Dollar> totalRefundD = new HashMap<>();
        List<Dollar> amountsRefundD = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if ("0".equals(reclamation.healthCare())) amountsRefundD.add(getReclamationAmountMax(reclamation.amount(), 100, 8500));
            else if ("100".equals(reclamation.healthCare())) {
                getMaxRefundAndTotal(totalRefundD, amountsRefundD, reclamation.healthCare(), reclamation.amount(), 25000, 7500, 100);
            } else if ("150".equals(reclamation.healthCare())) amountsRefundD.add(getReclamationAmountMax(reclamation.amount(), 100, 15000));
            else if ("175".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundD, amountsRefundD, reclamation.healthCare(), reclamation.amount(), 20000, 95);
            } else if ("200".equals(reclamation.healthCare())) {
                getMaxRefundAndTotal(totalRefundD, amountsRefundD, reclamation.healthCare(), reclamation.amount(), 25000, 10000, 100);
            } else if (300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399) amountsRefundD.add(new Dollar(reclamation.amount()));
            else if ("400".equals(reclamation.healthCare())) amountsRefundD.add(getReclamationAmountMax(reclamation.amount(), 100, 6500));
            else if ("500".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundD, amountsRefundD, reclamation.healthCare(), reclamation.amount(), 15000, 100);
            } else if ("600".equals(reclamation.healthCare())) {
                getMaxRefundAndTotal(totalRefundD, amountsRefundD, reclamation.healthCare(), reclamation.amount(), 30000, 10000, 100);
            } else if ("700".equals(reclamation.healthCare())) amountsRefundD.add(getReclamationAmountMax(reclamation.amount(), 100, 9000));
            else throw new ApplicationException("Numéro de soin inattendue.");
        }
        return amountsRefundD;
    }

    private static List<Dollar> contractE (List<Reclamation> reclamations) throws ApplicationException {
        Map<String, Dollar> totalRefundE = new HashMap<>();
        List<Dollar> amountsRefundE = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if ("0".equals(reclamation.healthCare()) || "150".equals(reclamation.healthCare())) amountsRefundE.add(getReclamationAmount(reclamation.amount(), 15));
            else if ("100".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundE, amountsRefundE, reclamation.healthCare(), reclamation.amount(), 25000, 25);
            } else if ("175".equals(reclamation.healthCare())) {
                getMaxRefundAndTotal(totalRefundE, amountsRefundE, reclamation.healthCare(), reclamation.amount(), 20000, 2000, 25);
            } else if ("200".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundE, amountsRefundE, reclamation.healthCare(), reclamation.amount(), 25000,120);
            } else if (300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399) amountsRefundE.add(getReclamationAmount(reclamation.amount(), 60));
            else if ("400".equals(reclamation.healthCare())) amountsRefundE.add(getReclamationAmountMax(reclamation.amount(), 25, 1500));
            else if ("500".equals(reclamation.healthCare())) {
                getMaxRefundAndTotal(totalRefundE, amountsRefundE, reclamation.healthCare(), reclamation.amount(), 15000, 2000, 30);
            } else if ("600".equals(reclamation.healthCare())) {
                getRefundAndTotal(totalRefundE, amountsRefundE, reclamation.healthCare(), reclamation.amount(), 30000, 15);
            } else if ("700".equals(reclamation.healthCare())) amountsRefundE.add(getReclamationAmount(reclamation.amount(), 22));
            else throw new ApplicationException("Numéro de soin inattendue.");
        }
        return amountsRefundE;
    }

    private static Dollar getReclamationAmount (String amount, int percent) throws ApplicationException {
        Dollar newAmount = new Dollar(amount);
        return newAmount.calculatePercentage(percent);
    }

    private static Dollar getReclamationAmountMax (String amountSpent, int percent, long max) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        Dollar refund = reclamation.calculatePercentage(percent);
        if (refund.isGreaterThan(max)) refund.setCent(max);
        return refund;
    }

    private static void getRefundAndTotal (Map<String, Dollar> totalRefund, List<Dollar> amountsRefund, String healthCare, String amount, long max, int percent) throws ApplicationException {
        long tmp;
        Dollar maxReclamation = totalRefund.getOrDefault(healthCare, new Dollar("00.00$"));
        Dollar prevAmount = new Dollar(amount);
        Dollar newAmount = prevAmount.calculatePercentage(percent);
        tmp = maxReclamation.add(newAmount);
        if (tmp >= max) {
            newAmount.setCent(max - maxReclamation.getCent());
            maxReclamation.setCent(max);
        } else maxReclamation.setCent(tmp);
        totalRefund.put(healthCare, maxReclamation);
        amountsRefund.add(newAmount);
    }

    private static void getMaxRefundAndTotal (Map<String, Dollar> totalRefund, List<Dollar> amountsRefund, String healthCare, String amount, long maxTotal, long maxAmount, int percent) throws ApplicationException  {
        long tmp;
        Dollar maxReclamation = totalRefund.getOrDefault(healthCare, new Dollar("00.00$"));
        Dollar prevAmount = new Dollar(amount);
        Dollar newAmount = prevAmount.calculatePercentage(percent);
        if (newAmount.isGreaterThan(maxAmount)) newAmount.setCent(maxAmount);
        tmp = maxReclamation.add(newAmount);
        if (tmp >= maxTotal) {
            newAmount.setCent(maxTotal - maxReclamation.getCent());
            maxReclamation.setCent(maxTotal);
        } else maxReclamation.setCent(tmp);
        totalRefund.put(healthCare, maxReclamation);
        amountsRefund.add(newAmount);
    }
}
