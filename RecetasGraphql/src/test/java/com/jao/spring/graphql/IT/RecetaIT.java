package com.jao.spring.graphql.IT;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.annotation.DirtiesContext;

import com.jao.spring.graphql.entity.Receta;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
// Esto asegura que la lista del Repository se limpie/reinicie entre tests
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class RecetaIT {

    @Autowired
    private GraphQlTester graphQlTester;

    @Test
    void debeListarTodasLasRecetas() {
        String query = """
                query {
                    listarTodasLasRecetas {
                        id
                        titulo
                        autor
                        dificultad
                    }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .path("data.listarTodasLasRecetas")
                .entityList(Receta.class)
                .hasSize(2); // Tenemos la Tortilla y la Paella por defecto
    }

    @Test
    void debeCrearUnaNuevaReceta() {
        String mutation = """
                mutation {
                    guardarReceta(recetaDto: {
                        titulo: "Tarta de Queso",
                        autor: "Bakery Master",
                        dificultad: "Media",
                        ingredientes: ["Queso crema", "Huevos", "Azúcar"]
                    }) {
                        id
                        titulo
                        autor
                    }
                }
                """;

        graphQlTester.document(mutation)
                .execute()
                .path("data.guardarReceta")
                .entity(Receta.class)
                .satisfies(receta -> {
                    assertNotNull(receta.getId());
                    assertEquals("Tarta de Queso", receta.getTitulo());
                    assertEquals("Bakery Master", receta.getAutor());
                });
    }

    @Test
    void debeObtenerRecetaPorId() {
        String query = """
                query {
                    obtenerRecetaPorId(id: 1) {
                        titulo
                        dificultad
                    }
                }
                """;

        graphQlTester.document(query)
                .execute()
                .path("data.obtenerRecetaPorId")
                .entity(Receta.class)
                .satisfies(receta -> {
                    assertEquals("Tortilla de Patatas", receta.getTitulo());
                    assertEquals("Fácil", receta.getDificultad());
                });
    }
}
