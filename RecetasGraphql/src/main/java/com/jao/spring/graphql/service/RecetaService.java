package com.jao.spring.graphql.service;

import com.jao.spring.graphql.dto.RecetaDto;
import com.jao.spring.graphql.entity.Receta;
import com.jao.spring.graphql.exception.RecetaNotFound;
import com.jao.spring.graphql.mapper.RecetaMapper;
import com.jao.spring.graphql.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

	private final RecetaRepository recetaRepository;
    private final RecetaMapper recetaMapper;

    public List<Receta> listarTodas() {
        return recetaRepository.getAll();
    }

    public List<Receta> listarRecientes(int conteo, int compensacion) {
        return recetaRepository.getRecentRecetas(conteo, compensacion);
    }

    public Receta buscarPorId(Long id) {
        return recetaRepository.getById(id)
                .orElseThrow(() -> new RecetaNotFound("Receta no encontrada con ID: " + id));
    }

    public Receta crear(RecetaDto dto) {
        Receta receta = recetaMapper.apply(dto);
        return recetaRepository.save(receta);
    }

    public Receta eliminar(Long id) {
        return recetaRepository.delete(id)
                .orElseThrow(() -> new RecetaNotFound("No se pudo eliminar: ID " + id + " no existe"));
    }
}
