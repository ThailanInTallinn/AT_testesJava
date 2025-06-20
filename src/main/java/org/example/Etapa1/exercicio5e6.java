package org.example.Etapa1;


import com.google.gson.JsonSyntaxException;
import io.javalin.Javalin;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class exercicio5e6 {

    public void adicionaELista(Javalin app, ArrayList<Tarefa> tarefas, AtomicInteger taskId){
        app.post("/tarefas", ctx -> {
            String msg = ctx.body();
            try{
                var DTO = ctx.bodyAsClass(TarefaDTO.class);
                Tarefa task = new Tarefa();
                task.setTitulo(DTO.titulo);
                task.setDescricao(DTO.descricao);
                if(DTO.concluido){
                    task.setConcluido(DTO.concluido);
                } else {
                    task.setConcluido(false);
                }
                task.setIdTarefa(taskId.getAndIncrement());
                task.setDataCriacao(LocalDateTime.now());
                tarefas.add(task);
                ctx.result(msg);
                ctx.status(201);
            } catch (JsonSyntaxException e) {
                ctx.status(400);
                throw new RuntimeException(e);
            }

        });

        app.get("/tarefas", ctx -> {
            ctx.json(tarefas);
        });

        app.get("/tarefas/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Tarefa foundTask = null;
            for(Tarefa task : tarefas){
                if(task.idTarefa == id){
                    foundTask = task;
                    break;
                }
            }
            if(foundTask != null){
                ctx.json(foundTask);
            } else {
                ctx.status(404);
            }
        });
    }
}
