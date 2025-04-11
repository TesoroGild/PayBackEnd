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
        Map<String, Dollar> testRefA = new HashMap<>();
        List<Dollar> amountsRefA = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if ("0".equals(reclamation.healthCare())) amountsRefA.add(getReclamationAmount(reclamation.amount(), 25));
            else if ("100".equals(reclamation.healthCare())) {
                Object[] val = getNewAmountAndTotal(testRefA, reclamation.healthCare(), reclamation.amount(), 25000, 35);
                testRefA.put(reclamation.healthCare(), (Dollar) val[1]);
                amountsRefA.add((Dollar) val[0]);
            } else if ("150".equals(reclamation.healthCare()) ||(300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399)
                    || "400".equals(reclamation.healthCare()) || "700".equals(reclamation.healthCare())) amountsRefA.add(new Dollar("00.00$"));
            else if ("175".equals(reclamation.healthCare())) {
                Object[] val = getNewAmountAndTotal(testRefA, reclamation.healthCare(), reclamation.amount(), 20000, 50);
                testRefA.put(reclamation.healthCare(), (Dollar) val[1]);
                amountsRefA.add((Dollar) val[0]);
            } else if ("200".equals(reclamation.healthCare())) {
                Object[] val = getNewAmountAndTotal(testRefA, reclamation.healthCare(), reclamation.amount(), 25000, 35);
                testRefA.put(reclamation.healthCare(), (Dollar) val[1]);
                amountsRefA.add((Dollar) val[0]);
            } else if ("500".equals(reclamation.healthCare())) {
                Object[] val = getNewAmountAndTotal(testRefA, reclamation.healthCare(), reclamation.amount(), 15000, 25);
                testRefA.put(reclamation.healthCare(), (Dollar) val[1]);
                amountsRefA.add((Dollar) val[0]);
            } else if ("600".equals(reclamation.healthCare())) {
                Object[] val = getNewAmountAndTotal(testRefA, reclamation.healthCare(), reclamation.amount(), 30000, 40);
                testRefA.put(reclamation.healthCare(), (Dollar) val[1]);
                amountsRefA.add((Dollar) val[0]);
            } else throw new ApplicationException("Numéro de soin inattendue.");
        }
        return amountsRefA;
    }

    private static List<Dollar> contractB (List<Reclamation> reclamations) throws ApplicationException {
        Map<String, Dollar> testRefB = new HashMap<>();
        List<Dollar> amountsRefB = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if ("0".equals(reclamation.healthCare())) amountsRefB.add(getReclamationAmountMax(reclamation.amount(), 50, 4000));
            else if ("100".equals(reclamation.healthCare())) {
                Object[] val = getNewMaxAmountAndTotal(testRefB, reclamation.healthCare(), reclamation.amount(), 25000,  5000,50);
                testRefB.put(reclamation.healthCare(), (Dollar) val[1]);
                amountsRefB.add((Dollar) val[0]);
            }
            else if ("150".equals(reclamation.healthCare()) || "400".equals(reclamation.healthCare())) amountsRefB.add(new Dollar("00.00$"));
            else if ("175".equals(reclamation.healthCare())) {
                Object[] val = getNewAmountAndTotal(testRefB, reclamation.healthCare(), reclamation.amount(), 20000,75);
                testRefB.put(reclamation.healthCare(), (Dollar) val[1]);
                amountsRefB.add((Dollar) val[0]);
            }
            else if ("200".equals(reclamation.healthCare())) {
                Object[] val = getNewAmountAndTotal(testRefB, reclamation.healthCare(), reclamation.amount(), 25000,  100);
                testRefB.put(reclamation.healthCare(), (Dollar) val[1]);
                amountsRefB.add((Dollar) val[0]);
            }
            else if ((300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399)) amountsRefB.add(getReclamationAmount(reclamation.amount(), 50));
            else if ("500".equals(reclamation.healthCare())) {
                Object[] val = getNewMaxAmountAndTotal(testRefB, reclamation.healthCare(), reclamation.amount(), 15000,  5000,50);
                testRefB.put(reclamation.healthCare(), (Dollar) val[1]);
                amountsRefB.add((Dollar) val[0]);
            }
            else if ("600".equals(reclamation.healthCare())) {
                Object[] val = getNewAmountAndTotal(testRefB, reclamation.healthCare(), reclamation.amount(), 30000,  100);
                testRefB.put(reclamation.healthCare(), (Dollar) val[1]);
                amountsRefB.add((Dollar) val[0]);
            }
            else if ("700".equals(reclamation.healthCare())) amountsRefB.add(getReclamationAmount(reclamation.amount(), 70));
            else throw new ApplicationException("Numéro de soin inattendue.");
        }
        return amountsRefB;
    }

    private static List<Dollar> contractC (List<Reclamation> reclamations) throws ApplicationException {
        String validTreatment = "0, 175, 200, 400, 500, 700";
        List<Dollar> amountsRefC = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if (validTreatment.contains(reclamation.healthCare())
                    || (300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399))
                amountsRefC.add(treatmentC90Percent(reclamation.amount()));
            else if ("100".equals(reclamation.healthCare())) amountsRefC.add(treatmentC95Percent(reclamation.amount()));
            else if ("150".equals(reclamation.healthCare())) amountsRefC.add(treatmentC85Percent(reclamation.amount()));
            else if ("600".equals(reclamation.healthCare())) amountsRefC.add(treatmentC75Percent(reclamation.amount()));
            else throw new ApplicationException("Numéro de soin inattendue.");
        }
        return amountsRefC;
    }

    private static List<Dollar> contractD (List<Reclamation> reclamations) throws ApplicationException {
        List<Dollar> amountsRefD = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if ("0".equals(reclamation.healthCare())) amountsRefD.add(treatmentD85Max (reclamation.amount()));
            else if ("150".equals(reclamation.healthCare())) amountsRefD.add(treatment1005Max (reclamation.amount()));
            else if ("175".equals(reclamation.healthCare())) amountsRefD.add(treatmentD95PercentNoMax (reclamation.amount()));
            else if ("100".equals(reclamation.healthCare())) amountsRefD.add(treatmentD75Max (reclamation.amount()));
            else if ("200".equals(reclamation.healthCare()) || "600".equals(reclamation.healthCare())) amountsRefD.add(treatmentD100Max (reclamation.amount()));
            else if (300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399
                    || "500".equals(reclamation.healthCare())) amountsRefD.add(new Dollar(reclamation.amount()));
            else if ("400".equals(reclamation.healthCare())) amountsRefD.add(treatmentD65Max (reclamation.amount()));
            else if ("700".equals(reclamation.healthCare())) amountsRefD.add(treatmentD90Max (reclamation.amount()));
            else throw new ApplicationException("Numéro de soin inattendue.");
        }
        return amountsRefD;
    }

    private static List<Dollar> contractE (List<Reclamation> reclamations) throws ApplicationException {
        List<Dollar> amountsRefE = new ArrayList<>();
        for (Reclamation reclamation : reclamations) {
            if ("0".equals(reclamation.healthCare()) || "150".equals(reclamation.healthCare()) || "600".equals(reclamation.healthCare())) amountsRefE.add(treatmentE15PercentNoMax (reclamation.amount()));
            else if ("100".equals(reclamation.healthCare())) amountsRefE.add(treatmentE25PercentNoMax (reclamation.amount()));
            else if ("175".equals(reclamation.healthCare())) amountsRefE.add(treatmentE25Percent20Max (reclamation.amount()));
            else if ("200".equals(reclamation.healthCare())) amountsRefE.add(treatmentE12PercentNoMax (reclamation.amount()));
            else if (300 <= Integer.parseInt(reclamation.healthCare()) && Integer.parseInt(reclamation.healthCare()) <= 399) amountsRefE.add(treatmentE60PercentNoMax (reclamation.amount()));
            else if ("400".equals(reclamation.healthCare())) amountsRefE.add(treatmentE25Percent15Max (reclamation.amount()));
            else if ("500".equals(reclamation.healthCare())) amountsRefE.add(treatmentE30Percent20Max (reclamation.amount()));
            else if ("700".equals(reclamation.healthCare())) amountsRefE.add(treatmentE22PercentNoMax (reclamation.amount()));
            else throw new ApplicationException("Numéro de soin inattendue.");
        }
        return amountsRefE;
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

    private static Object[] getNewAmountAndTotal (Map<String, Dollar> testRef, String healthCare, String amount, long max, int percent) throws ApplicationException {
        long tmp;
        Dollar maxReclamation = testRef.getOrDefault(healthCare, new Dollar("00.00$"));
        Dollar prevAmount = new Dollar(amount);
        Dollar newAmount = prevAmount.calculatePercentage(percent);
        tmp = maxReclamation.add(newAmount);
        if (tmp >= max) {
            newAmount.setCent(max - maxReclamation.getCent());
            maxReclamation.setCent(max);
        } else maxReclamation.setCent(tmp);
        return new Object[] { newAmount, maxReclamation};
    }

    private static Object[] getNewMaxAmountAndTotal (Map<String, Dollar> testRef, String healthCare, String amount, long maxTotal, long maxAmount, int percent) throws ApplicationException  {
        long tmp;
        Dollar maxReclamation = testRef.getOrDefault(healthCare, new Dollar("00.00$"));
        Dollar prevAmount = new Dollar(amount);
        Dollar newAmount = prevAmount.calculatePercentage(percent);
        if (newAmount.isGreaterThan(maxAmount)) newAmount.setCent(maxAmount);
        tmp = maxReclamation.add(newAmount);
        if (tmp >= maxTotal) {
            newAmount.setCent(maxTotal - maxReclamation.getCent());
            maxReclamation.setCent(maxTotal);
        } else maxReclamation.setCent(tmp);
        return new Object[] { newAmount, maxReclamation};
    }

    private static Dollar treatmentC75Percent (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(75);
    }

    private static Dollar treatmentC85Percent (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(85);
    }
    
    private static Dollar treatmentC90Percent (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(90);
    }

    private static Dollar treatmentC95Percent (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(95);
    }

    private static Dollar treatmentD95PercentNoMax (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(95);
    }

    private static Dollar treatmentD65Max (String amountSpent) throws ApplicationException  {
        Dollar refund = new Dollar(amountSpent);
        if (refund.isGreaterThan(6500)) return new Dollar("65.00$");
        return refund;
    }

    private static Dollar treatmentD75Max (String amountSpent) throws ApplicationException  {
        Dollar refund = new Dollar(amountSpent);
        if (refund.isGreaterThan(7500)) return new Dollar("75.00$");
        return refund;
    }

    private static Dollar treatmentD85Max (String amountSpent) throws ApplicationException  {
        Dollar refund = new Dollar(amountSpent);
        if (refund.isGreaterThan(8500)) return new Dollar("85.00$");
        return refund;
    }

    private static Dollar treatmentD90Max (String amountSpent) throws ApplicationException  {
        Dollar refund = new Dollar(amountSpent);
        if (refund.isGreaterThan(9000)) return new Dollar("90.00$");
        return refund;
    }

    private static Dollar treatmentD100Max (String amountSpent) throws ApplicationException  {
        Dollar refund = new Dollar(amountSpent);
        if (refund.isGreaterThan(15000)) return new Dollar("150.00$");
        return refund;
    }
    
    private static Dollar treatment1005Max (String amountSpent) throws ApplicationException  {
        Dollar refund = new Dollar(amountSpent);
        if (refund.isGreaterThan(10000)) return new Dollar("100.00$");
        return refund;
    }
    
    private static Dollar treatmentE12PercentNoMax (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(12);
    }

    private static Dollar treatmentE15PercentNoMax (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(15);
    }

    private static Dollar treatmentE22PercentNoMax (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(22);
    }

    private static Dollar treatmentE25Percent15Max (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        Dollar refund = reclamation.calculatePercentage(25);
        if (refund.isGreaterThan(1500)) return new Dollar("15.00$");
        return refund;
    }

    private static Dollar treatmentE25Percent20Max (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        Dollar refund = reclamation.calculatePercentage(25);
        if (refund.isGreaterThan(2000)) return new Dollar("20.00$");
        return refund;
    }

    private static Dollar treatmentE25PercentNoMax (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        return reclamation.calculatePercentage(25);
    }

    private static Dollar treatmentE30Percent20Max (String amountSpent) throws ApplicationException  {
        Dollar reclamation = new Dollar(amountSpent);
        Dollar refund = reclamation.calculatePercentage(30);
        if (refund.isGreaterThan(2000)) return new Dollar("20.00$");
        return refund;
    }

    private static Dollar treatmentE60PercentNoMax (String amountSpent) throws ApplicationException  {
        Dollar refund = new Dollar(amountSpent);
        if (refund.isGreaterThan(9000)) return new Dollar("90.00$");
        return refund;
    }
}
