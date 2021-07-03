package br.com.pedrodavi.tasksapitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

public class APITest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";
    }

    @Test
    public void retornaTarefas() {
        RestAssured
                .given()
                .when()
                    .get("/todo")
                .then()
                    .statusCode(200)
        ;
    }

    @Test
    public void adicionarTarefas() {
        RestAssured
                .given()
                    .body("{ \"task\": \"Teste api\", \"dueDate\": \"2021-07-03\" }")
                    .contentType(ContentType.JSON)
                .when()
                    .post("/todo")
                .then()
                    .statusCode(201)
        ;
    }

    @Test
    public void naoAdicionarTarefaInvalida() {
        RestAssured
                .given()
                .body("{ \"task\": \"Teste api\", \"dueDate\": \"2010-07-03\" }")
                .contentType(ContentType.JSON)
                .when()
                .post("/todo")
                .then()
                .statusCode(400)
        ;
    }

}
