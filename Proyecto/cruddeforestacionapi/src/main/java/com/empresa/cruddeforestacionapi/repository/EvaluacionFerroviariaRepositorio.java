package com.empresa.cruddeforestacionapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresa.cruddeforestacionapi.entity.EvaluacionFerroviariaEntity;

@Repository
public interface EvaluacionFerroviariaRepositorio extends JpaRepository<EvaluacionFerroviariaEntity, Long>{
    
}
