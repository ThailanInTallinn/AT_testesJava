package org.example.Etapa1;

import io.javalin.Javalin;

public class exercicio3 {
    public void EndpointEcho(Javalin app){
        app.post("/echo", ctx -> {
            String msg = ctx.body();
            ctx.result(msg);
        });
    }
}
