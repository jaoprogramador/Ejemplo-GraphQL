package com.jao.spring.graphql.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Receta {

    private Long id;
    private String titulo;
    private String descripcion;
    private String elaboracion;      // El "paso a paso" de la receta
    
    private Integer tiempoPreparacion; // En minutos
    private Integer tiempoCoccion;     // En minutos
    private Integer comensales;        // Número de raciones/personas
    
    private String dificultad;         // Fácil, Media, Difícil
    private String categoria;          // Entrantes, Postres, Carnes, etc.
    
    private List<String> ingredientes; // Lista de ingredientes necesarios
    
    private String urlImagen;
    private String autor;
    
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private LocalDateTime fechaEliminacion;

}
