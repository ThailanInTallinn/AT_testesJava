package org.example.Etapa1;

import io.javalin.Javalin;

public class exercicio1 {

    public void sayHello(Javalin app){
        
        app.get("/hello", ctx -> {
            ctx.result("Hello, Javalin!");
            ctx.status(200);
        });

    }

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);
        exercicio1 e1 = new exercicio1();
        e1.sayHello(app);

    }
}
