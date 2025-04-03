package jsonprocessing;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CreateOutputFile {
    public static void invalidDataOutput() {
        Map<String, String> invalidMessageMap = Map.of("message", "Données invalides");
        ObjectMapper mapper = new ObjectMapper();

        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("error.json"), invalidMessageMap);
        } catch (IOException e) {
            System.out.println("Erreur lors de la création du fichier sortie d'erreur");
            System.out.println(e.getMessage());
        }
    }

    public static void refundOutput () {
        System.out.println("MY REFUND");
    }
}
