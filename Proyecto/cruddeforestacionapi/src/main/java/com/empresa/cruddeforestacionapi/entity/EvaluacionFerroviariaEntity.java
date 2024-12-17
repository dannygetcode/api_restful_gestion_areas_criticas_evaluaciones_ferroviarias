package com.empresa.cruddeforestacionapi.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "evaluaciones_ferroviarias")
public class EvaluacionFerroviariaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(nullable = false)
    @Size(min = 2, max = 200)
    private String nombreRuta;

    @NotNull
    @Column(nullable = false)
    @Size(min = 10, max = 500)
    private String informeViabilidad;

    @NotNull
    @Column(nullable = false)
    @PastOrPresent
    private LocalDateTime fechaEvaluacion;

    @ManyToOne
    @JoinColumn(name = "area_critica_id", nullable = false)
    private AreaCriticaEntity areaCriticaEntity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreRuta() {
        return nombreRuta;
    }

    public void setNombreRuta(String nombreRuta) {
        this.nombreRuta = nombreRuta;
    }

    public String getInformeViabilidad() {
        return informeViabilidad;
    }

    public void setInformeViabilidad(String informeViabilidad) {
        this.informeViabilidad = informeViabilidad;
    }

    public LocalDateTime getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(LocalDateTime fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public AreaCriticaEntity getAreaCriticaEntity() {
        return areaCriticaEntity;
    }

    public void setAreaCriticaEntity(AreaCriticaEntity areaCriticaEntity) {
        this.areaCriticaEntity = areaCriticaEntity;
    }

    
}
