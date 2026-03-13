package com.jao.spring.graphql.mapper;

import com.jao.spring.graphql.dto.RecetaDto;
import com.jao.spring.graphql.entity.Receta;
import org.springframework.stereotype.Component;
import java.util.function.Function;

@Component
public class RecetaMapper implements Function<RecetaDto, Receta> {

    @Override
    public Receta apply(RecetaDto dto) {
        return Receta.builder()
                .titulo(dto.getTitulo())
                .descripcion(dto.getDescripcion())
                .elaboracion(dto.getElaboracion())
                .tiempoPreparacion(dto.getTiempoPreparacion())
                .tiempoCoccion(dto.getTiempoCoccion())
                .comensales(dto.getComensales())
                .dificultad(dto.getDificultad())
                .categoria(dto.getCategoria())
                .ingredientes(dto.getIngredientes())
                .urlImagen(dto.getUrlImagen())
                .autor(dto.getAutor())
                .build();
    }
}
