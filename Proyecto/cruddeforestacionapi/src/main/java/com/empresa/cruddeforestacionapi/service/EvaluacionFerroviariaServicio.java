package com.empresa.cruddeforestacionapi.service;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.cruddeforestacionapi.dto.EvaluacionFerroviariaDTO;
import com.empresa.cruddeforestacionapi.entity.EvaluacionFerroviariaEntity;
import com.empresa.cruddeforestacionapi.excepcion.RecursoNoEncontradoExcepcion;
import com.empresa.cruddeforestacionapi.repository.AreaCriticaRepositorio;
import com.empresa.cruddeforestacionapi.repository.EvaluacionFerroviariaRepositorio;
import com.empresa.cruddeforestacionapi.util.EvaluacionFerroviariaMapper;

@Service
public class EvaluacionFerroviariaServicio {

    @Autowired
    private EvaluacionFerroviariaRepositorio evaluacionFerroviariaRepositorio;

    @Autowired
    private AreaCriticaRepositorio areaCriticaRepositorio;

    private final EvaluacionFerroviariaMapper mapper= EvaluacionFerroviariaMapper.INSTANCE;


    public List<EvaluacionFerroviariaDTO> obtenerTodasEvaluacionFerroviarias() {
        List<EvaluacionFerroviariaEntity> evaluacionFerroviariaEntities= evaluacionFerroviariaRepositorio.findAll();
        return evaluacionFerroviariaEntities.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public EvaluacionFerroviariaDTO obtenerEvaluacionFerroviariaPorId(Long id) {
       EvaluacionFerroviariaEntity evaluacionFerroviariaEntity= evaluacionFerroviariaRepositorio.findById(id).orElseThrow(() -> new RecursoNoEncontradoExcepcion("Evaluación ferroviaria no encontrada"));
       return mapper.toDto(evaluacionFerroviariaEntity);
    }

    public EvaluacionFerroviariaDTO crearEvaluacionFerroviaria(EvaluacionFerroviariaDTO evaluacionFerroviariaDTO) {               
        if (!areaCriticaRepositorio.existsById(evaluacionFerroviariaDTO.getAreaCriticaEntityId())) {
            throw new RecursoNoEncontradoExcepcion("Area critica no encontrada");
        }

        EvaluacionFerroviariaEntity evaluacionFerroviariaEntity= mapper.toEntity(evaluacionFerroviariaDTO);
        EvaluacionFerroviariaEntity evaluacionFerroviariaEntityCreada= evaluacionFerroviariaRepositorio.save(evaluacionFerroviariaEntity);

        return mapper.toDto(evaluacionFerroviariaEntityCreada);
    }

    public EvaluacionFerroviariaDTO actualizarEvaluacionFerroviariaPorId(Long id, EvaluacionFerroviariaDTO evaluacionFerroviariaDTO) {
        EvaluacionFerroviariaEntity evaluacionFerroviariaEntity= evaluacionFerroviariaRepositorio.findById(id).orElseThrow(() -> new RecursoNoEncontradoExcepcion("Evaluación ferroviaria no encontrada"));
        evaluacionFerroviariaEntity.setNombreRuta(evaluacionFerroviariaDTO.getNombreRuta());
        evaluacionFerroviariaEntity.setInformeViabilidad(evaluacionFerroviariaDTO.getInformeViabilidad());
        evaluacionFerroviariaEntity.setFechaEvaluacion(evaluacionFerroviariaDTO.getFechaEvaluacion());

        EvaluacionFerroviariaEntity evaluacionFerroviariaEntityActualizada= evaluacionFerroviariaRepositorio.save(evaluacionFerroviariaEntity);

        return mapper.toDto(evaluacionFerroviariaEntityActualizada);
    }

    public void eliminarEvaluacionFerroviariaPorId(Long id) {
        EvaluacionFerroviariaEntity evaluacionFerroviariaEntity= evaluacionFerroviariaRepositorio.findById(id).orElseThrow(() -> new RecursoNoEncontradoExcepcion("Evaluación ferroviaria no encontrada"));
        evaluacionFerroviariaRepositorio.delete(evaluacionFerroviariaEntity);
    }
    
}
