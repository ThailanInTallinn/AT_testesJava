package org.example.Etapa1;

import com.google.gson.Gson;
import io.javalin.Javalin;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class exercicio11 {
    public static void main(String[] args) {
        try {
            URL url = new URI("http://localhost:7000/tarefas").toURL();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            conn.setRequestProperty("Accept", "application/json");

            conn.setDoOutput(true);
            Gson gson = new Gson();
            TarefaDTO postRequest = new TarefaDTO();
            postRequest.setConcluido(true);
            postRequest.setTitulo("Arrumar a cama");
            postRequest.setDescricao("Arrumar a cama eh um porre");
            String json = gson.toJson(postRequest);
            try(OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }

            int status = conn.getResponseCode();
            System.out.println("Código de resposta: " + status);
            String response = new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println("Resposta: " + response);

        } catch(URISyntaxException | IOException e){
            throw new RuntimeException(e);
        }
    }
}
