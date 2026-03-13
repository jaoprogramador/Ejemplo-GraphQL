package com.jao.spring.graphql.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecetaDto {

    private String titulo;
    private String descripcion;
    private String elaboracion;
    
    private Integer tiempoPreparacion;
    private Integer tiempoCoccion;
    private Integer comensales;
    
    private String dificultad;
    private String categoria;
    
    private List<String> ingredientes;
    
    private String urlImagen;
    private String autor;

}