package org.example.Etapa1;

import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class exercicio1Test {
  @Test
    void deveMostrarSayHelloEStatus200(){
      Javalin app = Javalin.create();
      exercicio1 e1 = new exercicio1();
      e1.sayHello(app);
      JavalinTest.test(app, (server, client) ->{
          var response = client.get("/hello");
          Assertions.assertEquals(200, response.code());
      });
  }
}