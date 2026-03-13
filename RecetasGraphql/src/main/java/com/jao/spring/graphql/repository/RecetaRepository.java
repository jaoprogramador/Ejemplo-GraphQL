package com.jao.spring.graphql.repository;

import com.jao.spring.graphql.entity.Receta;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RecetaRepository {

    private final List<Receta> RECETAS = new ArrayList<>(
            List.of(
                Receta.builder()
                        .id(1L)
                        .titulo("Tortilla de Patatas")
                        .descripcion("La clásica receta española con o sin cebolla.")
                        .elaboracion("Cortar patatas, freír, mezclar con huevo y cuajar.")
                        .tiempoPreparacion(15)
                        .tiempoCoccion(20)
                        .comensales(4)
                        .dificultad("Fácil")
                        .categoria("Tradicional")
                        .ingredientes(List.of("Patatas", "Huevos", "Cebolla", "Aceite", "Sal"))
                        .autor("Chef Juan")
                        .fechaCreacion(LocalDateTime.now())
                        .build(),
                Receta.builder()
                        .id(2L)
                        .titulo("Paella Valenciana")
                        .descripcion("El plato estrella de la gastronomía española.")
                        .elaboracion("Sofreír carne, añadir verdura, arroz y azafrán.")
                        .tiempoPreparacion(30)
                        .tiempoCoccion(45)
                        .comensales(6)
                        .dificultad("Media")
                        .categoria("Arroces")
                        .ingredientes(List.of("Arroz", "Pollo", "Conejo", "Judía verde", "Garrofó"))
                        .autor("Maestro Arrocero")
                        .fechaCreacion(LocalDateTime.now())
                        .build()
            )
    );

    public List<Receta> getAll() {
        return RECETAS.stream()
                .filter(r -> r.getFechaEliminacion() == null)
                .toList();
    }

    public Optional<Receta> getById(Long id) {
        return RECETAS.stream()
                .filter(r -> r.getId().equals(id) && r.getFechaEliminacion() == null)
                .findFirst();
    }

    public Receta save(Receta receta) {
        receta.setId(this.getNextId());
        receta.setFechaCreacion(LocalDateTime.now());
        RECETAS.add(receta);
        return receta;
    }

    public Optional<Receta> delete(Long id) {
        Optional<Receta> recetaParaEliminar = getById(id);
        recetaParaEliminar.ifPresent(r -> r.setFechaEliminacion(LocalDateTime.now()));
        return recetaParaEliminar;
    }

    private Long getNextId() {
        return RECETAS.stream()
                .mapToLong(Receta::getId)
                .max()
                .orElse(0L) + 1L;
    }

    // Método útil para el esquema de paginación si lo necesitas
    public List<Receta> getRecentRecetas(int count, int offset) {
        List<Receta> activas = getAll();
        if (offset >= activas.size()) return List.of();
        return activas.subList(offset, Math.min(offset + count, activas.size()));
    }
}
