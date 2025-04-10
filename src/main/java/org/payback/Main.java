package org.payback;

import calcul.Dollar;
import calcul.Refund;
import exceptions.ApplicationException;
import jsonprocessing.*;
import models.Datas;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java -jar PayBackEnd.jar <fichier_entree.json> <fichier_sortie.json>");
            System.exit(1);
        }

        String inputFile = args[0];
        String outputFile = args[1];

        try {
            InputFile.isJsonFile(inputFile);
            Datas datas = InputFile.extractAndValidateFields(inputFile);
            /*Ca a servi a quoi d'extraire les dollars?*/
            //List<Dollar> dollarsRec = datas.extractAmounts(datas.reclamations());
            InputFile.validateDatas(datas);
            List<Dollar> amountsRef = Refund.amountRefund(datas.directory().charAt(0)+"", datas.reclamations());
            System.out.println("4");
            OutputFile.refundOutput(outputFile, datas, amountsRef);
        } catch (ApplicationException invEx) {
            OutputFile.invalidDataOutput(outputFile, invEx.getMessage());
            System.exit(1);
        } catch (Exception ex) {
            System.out.println("ERREUR A GERER");
            System.exit(1);
        }
    }
}