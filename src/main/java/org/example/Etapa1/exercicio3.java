package org.example.Etapa1;

import io.javalin.Javalin;

public class exercicio3 {
    public static void main(String[] args){
        Javalin app = Javalin.create().start(7000);
        app.post("/echo", ctx -> {
            String msg = ctx.body();
            ctx.result(msg);
        });
    }
}
