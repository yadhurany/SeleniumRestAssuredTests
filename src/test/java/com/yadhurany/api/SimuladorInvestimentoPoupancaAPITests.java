package com.yadhurany.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class SimuladorInvestimentoPoupancaAPITests {

    private static final String ENDPOINT = "http://5b847b30db24a100142dce1b.mockapi.io/api/v1/simulador";

    @Test
    public void verificarStatusCodeRetornaOK() {
        given().
                when().
                get(ENDPOINT).
                then().
                assertThat().
                statusCode(200);
    }

    @Test
    public void verificarRespostaJson() {
        given().
                when().
                get(ENDPOINT).
                then().
                assertThat().
                contentType(ContentType.JSON);
    }


    @Test
    public void verificarTamanhoCamposMesesValor() {
        given().
                when().
                get(ENDPOINT).
                then().
                assertThat().
                body("meses", hasSize(4)).
                body("valor", hasSize(4));
    }



    @Test
    public void testarValoresTabela() {
        given().
                when().
                get(ENDPOINT).
                then().
                assertThat().
                body("id", equalTo(1)).
                body("meses", equalTo(Arrays.asList("112","124","136","148"))).
                body("valor", equalTo(Arrays.asList("2.802","3.174","3.564","3.971")));
    }
}
