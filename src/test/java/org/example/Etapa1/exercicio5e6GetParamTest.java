package org.example.Etapa1;

import io.javalin.Javalin;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class exercicio5e6GetParamTest {

    public AtomicInteger taskId = new AtomicInteger(1);
    public ArrayList<Tarefa> tarefas = new ArrayList<>();

    @Test
    public void TestaGetComParametro() throws IOException {
        Javalin app = Javalin.create();
        exercicio5e6 instanciaExercicio = new exercicio5e6();
        instanciaExercicio.adicionaELista(app, tarefas, taskId);
        app.start(0);
        int porta = app.port();

        String novaTarefa = """
            {
                "titulo": "Lavar a louça",
                "descricao": "Lavar a louça é bem legal",
                "concluido": true
            }
            """;

        URL urlPost = new URL("http://localhost:" + porta+ "/tarefas");
        HttpURLConnection conn = (HttpURLConnection) urlPost.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(novaTarefa.getBytes(StandardCharsets.UTF_8));
        }

        assertEquals(201, conn.getResponseCode());
        conn.disconnect();

        int tarefaId = 1;

        URL urlGet = new URL("http://localhost:" + porta + "/tarefas/" + tarefaId);
        HttpURLConnection getConn = (HttpURLConnection) urlGet.openConnection();
        getConn.setRequestMethod("GET");

        assertEquals(200, getConn.getResponseCode());

        StringBuilder response = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getConn.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
        }
        getConn.disconnect();

        String jsonResposta = response.toString();

        assertTrue(jsonResposta.contains("\"titulo\":\"Lavar a louça\""));
        assertTrue(jsonResposta.contains("\"descricao\":\"Lavar a louça é bem legal\""));
        assertTrue(jsonResposta.contains("\"concluido\":true"));

        app.stop();
    }
}