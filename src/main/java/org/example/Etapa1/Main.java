package org.example.Etapa1;

import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create().start(7000);
        exercicio1 e1 = new exercicio1();
        e1.sayHello(app);
        exercicio2 e2 = new exercicio2();
        e2.EndpointStatus(app);
        exercicio3 e3 = new exercicio3();
        e3.EndpointEcho(app);
        exercicio4 e4 = new exercicio4();
        e4.EndpointSaudacao(app);
        exercicio5e6 e5e6 = new exercicio5e6();
        ArrayList<Tarefa> tarefas = new ArrayList<>();
        AtomicInteger taskId = new AtomicInteger(1);
        e5e6.adicionaELista(app, tarefas, taskId);
    }
}
