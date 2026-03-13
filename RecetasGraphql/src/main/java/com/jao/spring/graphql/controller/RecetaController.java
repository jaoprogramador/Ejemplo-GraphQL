package com.jao.spring.graphql.controller;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.jao.spring.graphql.dto.RecetaDto;
import com.jao.spring.graphql.entity.Receta;
import com.jao.spring.graphql.service.RecetaService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecetaController {

	private final RecetaService recetaService;

    // QUERIES
    @QueryMapping
    public List<Receta> listarTodasLasRecetas() {
        return recetaService.listarTodas();
    }

    @QueryMapping
    public List<Receta> obtenerRecetasRecientes(@Argument int conteo, @Argument int compensacion) {
        return recetaService.listarRecientes(conteo, compensacion);
    }

    @QueryMapping
    public Receta obtenerRecetaPorId(@Argument Long id) {
        return recetaService.buscarPorId(id);
    }

    // MUTATIONS
    @MutationMapping
    public Receta guardarReceta(@Argument RecetaDto recetaDto) {
        return recetaService.crear(recetaDto);
    }

    @MutationMapping
    public Receta eliminarRecetaPorId(@Argument Long id) {
        return recetaService.eliminar(id);
    }
}