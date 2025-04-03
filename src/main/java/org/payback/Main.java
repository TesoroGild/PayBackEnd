package org.payback;

import jsonprocessing.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Aucun fichier spécifié en paramètre.");
            return;
        }

        String filePath = args[0];

        if (ValidateInputFile.isJsonFile(filePath)
            && ValidateInputFile.validations(filePath)) {
            CreateOutputFile.refundOutput();
        } else CreateOutputFile.invalidDataOutput();
    }
}