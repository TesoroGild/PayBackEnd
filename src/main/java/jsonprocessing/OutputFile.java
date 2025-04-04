package jsonprocessing;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class OutputFile {
    public static void invalidDataOutput(String outputFile, String message) {
        Map<String, String> invalidMessageMap = Map.of("Message", message);
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), invalidMessageMap);
        } catch (IOException e) {
            System.out.println("Erreur lors de la création du fichier sortie d'erreur");
            System.out.println(e.getMessage());
        }
    }

    public static void refundOutput (String outputFile) {
        System.out.println("REFUND OUTPUT A GERER");
        //ObjectMapper mapper = new ObjectMapper();
        //mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), root);
    }
}
