package org.example.Etapa1;

import io.javalin.Javalin;

import io.javalin.testtools.JavalinTest;

import org.junit.jupiter.api.Test;


import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class exercicio5e6PostTest {

    public AtomicInteger taskId = new AtomicInteger(1);
    public ArrayList<Tarefa> tarefas = new ArrayList<>();

    @Test
    public void TestaPostRequest(){
        Javalin app = Javalin.create();
        exercicio5e6 instanciaExercicio = new exercicio5e6();
        instanciaExercicio.adicionaELista(app, tarefas, taskId);
        JavalinTest.test(app, (server, client) -> {
            String jsonTeste = """
        {
            "titulo": "Lavar a louça",
            "descricao": "Lavar a louça é bem legal",
            "concluido": true
        }
        """;

            URL url = new URL("http://localhost:" + app.port() + "/tarefas");
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);


            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonTeste.getBytes(StandardCharsets.UTF_8);
                os.write(input);
            }

            int status = conn.getResponseCode();
            assertEquals(201, status);

        });
    }

}