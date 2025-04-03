package jsonprocessing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.IntStream;


public class ValidateInputFile {
    public static boolean isJsonFile(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.getName().endsWith(".json");
    }

    public static boolean validations(String filePath) {
        try {
            String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));
            ObjectMapper cf = new ObjectMapper();
            JsonNode node = cf.readTree(jsonString);
            boolean b1 = isValidClientId(node.get("client").asText());
            boolean b2 = isContractValid(node.get("contrat").asText());
            String monthOfTreatment = node.get("mois").asText();
            boolean b3 = isMonthValid(monthOfTreatment);
            JsonNode reclamations = node.get("reclamations");
            if (!(b1 && b2 && b3)) return false;
            for (JsonNode reclamation : reclamations) {
                System.out.println("Reclamation : " + reclamation);
                boolean b4 = isTreatmentValid(reclamation.get("soin").asInt());
                String dateOfTreatment = reclamation.get("date").asText();
                boolean b5 = isDateValid(dateOfTreatment);
                boolean b6 = isDateInMonth(dateOfTreatment, monthOfTreatment);
                boolean b7 = isValidAmount(reclamation.get("montant").asText());
                if (!(b4 && b5 && b6 && b7)) return false;
            }
            return true;
        } catch (Exception ex) {
            System.out.println("Error validations : " + ex.getMessage());
            return false;
        }
    }

    private static boolean isValidClientId(String clientId) {
        return clientId.matches("\\d{6}");
    }

    private static boolean isContractValid(String contract) {
        return contract.matches("^[ABCD]$");
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

    public static boolean isValidAmount(String amount) {
        return amount.matches("^\\d+(\\.\\d{2})?\\$$");
    }

    public static boolean isTreatmentValid(int treatmentNumnber) {
        int[] validTreatment = {0, 100, 200, 400, 500, 600, 700};
        if (IntStream.of(validTreatment).anyMatch(x -> x == treatmentNumnber)) return true;
        else return 300 <= treatmentNumnber && treatmentNumnber <= 399;
    }
}
