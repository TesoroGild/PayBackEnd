package jsonprocessing;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import exceptions.ApplicationException;
import models.Datas;
import models.Reclamation;


public class InputFile {
    public static void isJsonFile(String filePath) throws ApplicationException {
        File file = new File(filePath);
        if (!(file.exists() && file.getName().endsWith(".json")))
            throw new ApplicationException("Le fichier d'entré doit être de format JSON.");
    }

    public static Datas extractAndValidateFields(String inputFile) throws IOException, ApplicationException {
        String directory;
        String month;
        JsonNode nodeReclamations;
        String jsonString = new String(Files.readAllBytes(Paths.get(inputFile)));
        ObjectMapper om = new ObjectMapper();
        JsonNode node = om.readTree(jsonString);

        if (node.has("dossier")) directory = node.get("dossier").asText();
        else throw new ApplicationException("Le champ dossier est manquant.");
        if (node.has("mois")) month = node.get("mois").asText();
        else throw new ApplicationException("Le champ mois est manquant.");
        if (node.has("reclamations")) nodeReclamations = node.get("reclamations");
        else throw new ApplicationException("Le champ réclamation est manquant.");

        List<Reclamation> reclamations = om.convertValue(nodeReclamations, new TypeReference<List<Reclamation>>() {});

        for (Reclamation reclamation : reclamations) {
            if (!om.valueToTree(reclamation).has("soin")) throw new ApplicationException("Le champ soin est manquant.");
            if (!om.valueToTree(reclamation).has("date")) throw new ApplicationException("Le champ date est manquant.");
            if (!om.valueToTree(reclamation).has("montant")) throw new ApplicationException("Le champ montant est manquant.");
            /*if (reclamation.healthCare() == null) throw new ApplicationException("Le champ soin est manquant.");
            if (reclamation.date() == null) throw new ApplicationException("Le champ date est manquant.");
            if (reclamation.amount() == null) throw new ApplicationException("Le champ montant est manquant.");*/
        }
        return new Datas(directory, month, reclamations, "00.00$");
    }

    public static void validateDatas(Datas datas) throws ApplicationException {
        if (!isValidDirectory(datas.directory())) throw new ApplicationException("Le numéro de client doit obligatoirement être composé de 6 chiffres.");
        //if (!isContractValid(datas.contract())) throw new ApplicationException("Erreur : Le contrat doit être une des quatre lettres suivantes : A, B, C, D. La lettre doit toujours être en majuscule.");
        if (!isMonthValid(datas.month())) throw new ApplicationException("Erreur : Le mois est spécifié dans le champs mois sous le format AAAA-MM.");
        for (Reclamation reclamation : datas.reclamations()) {
            if (!isTreatmentValid(reclamation.healthCare())) throw new ApplicationException("Erreur : Le numéro de soin doit être valide selon le tableau de soins.");
            if (!isDateValid(reclamation.date())) throw new ApplicationException("Erreur : La date de consommation de chaque soin est spécifiée sous le format ISO 8601 (AAAA-MM-JJ)");
            if (!isDateInMonth(reclamation.date(), datas.month())) throw new ApplicationException("Erreur : La réclamation doit être pour le mois qui est traité");
            //Ajouter la validation des dollars?
            //Ne sert a rien si on garde extract amount
            //if (!(Dollar.isValidAmount(reclamation.amount()))) throw new ApplicationException("Le signe de dollar ($) est toujours présent à la fin d'un montant.");
        }
    }

    private static boolean isValidDirectory(String clientId) {
        return clientId.matches("[A-E]\\d{6}");
    }

    public static boolean isMonthValid(String mois) {
        return mois.matches("^\\d{4}-(0[1-9]|1[0-2])$");
    }

    public static boolean isDateValid(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isDateInMonth(String date, String month) {
        try {
            LocalDate dateObj = LocalDate.parse(date);
            YearMonth monthObj = YearMonth.parse(month, DateTimeFormatter.ofPattern("yyyy-MM"));
            return (dateObj.getYear() == monthObj.getYear() && dateObj.getMonthValue() == monthObj.getMonthValue());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isTreatmentValid(String treatmentNumber) {
        String validTreatment = "0, 100, 150, 200, 400, 500, 600, 700";
        if (validTreatment.contains(treatmentNumber)) return true;
        else {
            int intTreatmentNumber = Integer.parseInt(treatmentNumber);
            return 300 <= intTreatmentNumber && intTreatmentNumber <= 399;
        }
    }
}
