package org.example.Etapa1;

import io.javalin.Javalin;

import java.util.Map;

public class exercicio4 {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        app.get("saudacao/{nome}", ctx -> {
            String nome = ctx.pathParam("nome");
            var result = Map.of("Mensagem", String.format("Olá, %s", nome));
            ctx.json(result);
        });
    }
}
