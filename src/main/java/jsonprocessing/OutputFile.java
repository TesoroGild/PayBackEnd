package jsonprocessing;

import calcul.Dollar;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import exceptions.ApplicationException;
import models.Datas;
import models.Reclamation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    public static void refundOutput (String outputFile, Datas datas, List<Dollar> amountsRef) throws IOException, ApplicationException {
        List<Reclamation> refunds = new ArrayList<>();
        int i = 0;
        Dollar total = new Dollar("00.00$");
        long tmp;
        for (Reclamation reclamation : datas.reclamations()) {
            refunds.add(new Reclamation(reclamation.healthCare(), reclamation.date(), amountsRef.get(i).toString()));
            tmp = total.add(amountsRef.get(i));
            total.setCent(tmp);
            i++;
        }
        Datas refundDatas = new Datas(datas.directory(), datas.month(), refunds, total.toString());
        ObjectMapper om = new ObjectMapper();
        om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(refundDatas);
        try (FileWriter fileWriter = new FileWriter(outputFile)) {
            fileWriter.write(jsonOutput);
            System.out.println("Le sortie a été correctement consignée dans le fichier : " + outputFile);
        } catch (IOException e) {
            System.err.println("Erreur lors de l'écriture dans le fichier : " + e.getMessage());
        }
    }
}
