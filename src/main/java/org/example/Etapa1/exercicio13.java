package org.example.Etapa1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class exercicio13 {
    public static void main(String[] args) {
        try {
            URL url = new URI("http://localhost:7000/tarefas/1A").toURL();

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            int status = conn.getResponseCode();
            BufferedReader input = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = input.readLine()) != null) {
                content.append(inputLine);
            }

            input.close();
            conn.disconnect();
            System.out.println(content);
            System.out.println("Codigo de status: " + status);
        } catch(URISyntaxException | IOException e){
            throw new RuntimeException(e);
        }
    }
}
