package jsonprocessing;

import calcul.Dollar;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Datas;
import models.Reclamation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

    public static void refundOutput (String inputFile, String outputFile, Datas datas, List<Dollar> amountsRef) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get(inputFile)));
        List<Reclamation> refunds = new ArrayList<>();
        int i = 0;
        for (Reclamation reclamation : datas.reclamations()) {
            refunds.add(new Reclamation(reclamation.healthCare(), reclamation.date(), amountsRef.get(i).toString()));
            i++;
        }
        Datas refundDatas = new Datas(datas.client(), null, datas.month(), refunds);
        ObjectMapper om = new ObjectMapper();
        System.out.println("5");
        JsonNode root = om.readTree(jsonString);
        System.out.println("6");
        ((ObjectNode) root).remove("contrat");
        System.out.println("7");
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        System.out.println("8");
        try {
            System.out.println("9");
            om.writerWithDefaultPrettyPrinter().writeValue(new File(outputFile), refundDatas);
        } catch (IOException e) {
            System.out.println("Erreur lors de la création du fichier des réclamations");
            System.out.println(e.getMessage());
        }
    }
}
