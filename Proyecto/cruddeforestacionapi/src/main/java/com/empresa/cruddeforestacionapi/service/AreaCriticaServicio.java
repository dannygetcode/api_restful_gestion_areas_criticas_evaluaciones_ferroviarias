package com.empresa.cruddeforestacionapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empresa.cruddeforestacionapi.dto.AreaCriticaDTO;
import com.empresa.cruddeforestacionapi.entity.AreaCriticaEntity;
import com.empresa.cruddeforestacionapi.excepcion.RecursoNoEncontradoExcepcion;
import com.empresa.cruddeforestacionapi.repository.AreaCriticaRepositorio;

@Service
public class AreaCriticaServicio {
    
    @Autowired
    private AreaCriticaRepositorio areaCriticaRepositorio;


    public List<AreaCriticaDTO> obtenerTodasAreasCriticas() {

        List<AreaCriticaEntity> areaCriticaEntities= areaCriticaRepositorio.findAll();

        List<AreaCriticaDTO> areaCriticaDTOs= areaCriticaEntities.stream().map(this::convertirDTO).collect(Collectors.toList());
        
        return areaCriticaDTOs;
    }

    public AreaCriticaDTO obtenerAreaCriticaPorId(Long id) {

        AreaCriticaEntity areaCriticaEntity= areaCriticaRepositorio.findById(id).orElseThrow(() -> new RecursoNoEncontradoExcepcion("Area critica no encontrada"));

        return convertirDTO(areaCriticaEntity);
        
    }

    public AreaCriticaDTO crearAreaCritica(AreaCriticaDTO areaCriticaDTO) {
        AreaCriticaEntity areaCriticaEntity= convertirEntity(areaCriticaDTO);
        AreaCriticaEntity areaCriticaEntityCreada= areaCriticaRepositorio.save(areaCriticaEntity);
        return convertirDTO(areaCriticaEntityCreada);
    }
 
    public AreaCriticaDTO actualizarAreaCriticaPorId(Long id, AreaCriticaDTO areaCriticaDTO) {
        AreaCriticaEntity areaCriticaEntity= areaCriticaRepositorio.findById(id).orElseThrow(() -> new RecursoNoEncontradoExcepcion("Area critica no encontrada"));
        areaCriticaEntity.setNombre(areaCriticaDTO.getNombre());
        areaCriticaEntity.setDescripcion(areaCriticaDTO.getDescripcion());
        areaCriticaEntity.setLatitud(areaCriticaDTO.getLatitud());
        areaCriticaEntity.setLongitud(areaCriticaDTO.getLongitud());
        areaCriticaEntity.setIndiceDeforestacion(areaCriticaDTO.getIndiceDeforestacion());
        AreaCriticaEntity areaCriticaEntityActualizada= areaCriticaRepositorio.save(areaCriticaEntity);
        return convertirDTO(areaCriticaEntityActualizada);
    }
       
    public void eliminarAreaCriticaPorId(Long id) {
        AreaCriticaEntity areaCriticaEntity= areaCriticaRepositorio.findById(id).orElseThrow(() -> new RecursoNoEncontradoExcepcion("Area critica no encontrada"));        
        areaCriticaRepositorio.delete(areaCriticaEntity);
    }

    private AreaCriticaDTO convertirDTO(AreaCriticaEntity areaCriticaEntity){

        AreaCriticaDTO areaCriticaDTO= new AreaCriticaDTO();
        areaCriticaDTO.setId(areaCriticaEntity.getId());
        areaCriticaDTO.setNombre(areaCriticaEntity.getNombre());
        areaCriticaDTO.setDescripcion(areaCriticaEntity.getDescripcion());
        areaCriticaDTO.setLatitud(areaCriticaEntity.getLatitud());
        areaCriticaDTO.setLongitud(areaCriticaEntity.getLongitud());
        areaCriticaDTO.setIndiceDeforestacion(areaCriticaEntity.getIndiceDeforestacion());
        
        return areaCriticaDTO;
    }

    private AreaCriticaEntity convertirEntity(AreaCriticaDTO areaCriticaDTO){

        AreaCriticaEntity areaCriticaEntity= new AreaCriticaEntity();        
        areaCriticaEntity.setNombre(areaCriticaDTO.getNombre());
        areaCriticaEntity.setDescripcion(areaCriticaDTO.getDescripcion());
        areaCriticaEntity.setLatitud(areaCriticaDTO.getLatitud());
        areaCriticaEntity.setLongitud(areaCriticaDTO.getLongitud());
        areaCriticaEntity.setIndiceDeforestacion(areaCriticaDTO.getIndiceDeforestacion());
        
        return areaCriticaEntity;
    }

  
}
