package com.demo.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "persons")
@Where(clause = "deleted = false")
@SQLDelete(sql = "UPDATE persons SET deleted = true WHERE person_id = ?")
@EntityListeners(AuditingEntityListener.class)
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @NotNull(message = "EL NOMBRE NO PUEDE SER NULO")
    private String name;

    @NotNull(message = "EL APELLIDO NO PUEDE SER NULO")
    private String surname;

    @NotNull(message = "EL DNI NO PUEDE SER NULO")
    private String identification;

    @NotNull(message = "EL ESTADO DE EMPLEADO NO PUEDE SER NULO")
    private boolean employeed = false;

    @NotNull
    @JsonIgnore
    private boolean deleted = false;
}
