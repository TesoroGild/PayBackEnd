package calcul;

import exceptions.InvalidDataException;

public class Dollar {
    private long cent;

    public Dollar (String amountInStr) throws InvalidDataException {
        if (amountInStr == null || !isValidAmount(amountInStr))
            throw new InvalidDataException("Le signe de dollar ($) est toujours présent à la fin d'un montant.");
        else
            this.cent = convertirStringToLong(amountInStr);
    }

    public long getCent () {
        return cent;
    }

    public void setCent (long cent) {
        this.cent = cent;
    }

    public long convertirStringToLong (String chaineNum) throws InvalidDataException {
        long montant = -1;
        if (isValidAmount(chaineNum)) {
            if (chaineNum.contains(".")) {
                montant = Long.parseLong(chaineNum.substring(0,chaineNum.indexOf("."))
                        + chaineNum.substring(chaineNum.indexOf(".")+1,chaineNum.indexOf("$")));
            }else if (chaineNum.contains(",")) {
                montant = Long.parseLong(chaineNum.substring(0,chaineNum.indexOf(","))
                        + chaineNum.substring(chaineNum.indexOf(",")+1,chaineNum.indexOf("$")));
            }

        } else
            throw new InvalidDataException("Conversion en dollar impossible");
        return montant;
    }

    //enlever static si on ne verifie plus le dollar dans aredatavalid
    //static
    public boolean isValidAmount(String amount) {
        return amount.matches("^\\d+(\\.\\d{2})?\\$$");
    }

    private long convertStringToLong (String amountInStr) {
        String tmp = amountInStr.replace(".", "");
        String tmp1 = tmp.replace("$", "");
        return Long.parseLong(tmp1);
    }

    private long add () {
        return 0;
    }

    public Dollar calculatePercentage (int multiple) throws InvalidDataException {
        double cents = Math.round(this.getCent() * (multiple/100.0));
        double dollar = cents / 100;
        String amountFormat = String.format("%.2f$", dollar);
        return new Dollar(amountFormat);
    }

    boolean isGreaterThan(long number) {
        if (this.cent > number) return true;
        return false;
    }

    @Override
    public String toString() {
        String chaineNum = "";
        if (this.cent % 100 == 0) chaineNum = (cent / 100) + "." + (cent % 100) + "0$";
        else if (this.cent % 100 > 0 && this.cent % 100 < 10) chaineNum = (cent / 100) + ".0" + (cent % 100 )+ "$";
        else chaineNum = (cent / 100) + "." + (cent % 100 )+ "$";
        return chaineNum;
    }
}
