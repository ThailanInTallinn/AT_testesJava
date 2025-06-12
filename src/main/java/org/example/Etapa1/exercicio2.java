package org.example.Etapa1;

import io.javalin.Javalin;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class exercicio2 {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        app.get("/status", ctx -> {
            ctx.status(200);
            LocalDateTime data = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String text = data.format(formatter);
            ctx.result(text);
        });
    }
}
