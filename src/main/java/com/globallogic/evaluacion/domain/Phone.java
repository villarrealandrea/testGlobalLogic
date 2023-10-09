package com.globallogic.evaluacion.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
public class Phone implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @Column(columnDefinition = "uuid")
    @UuidGenerator
    private UUID id;
    private Long number;
    private int citycode;
    private String contrycode;
}
