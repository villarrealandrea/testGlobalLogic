package com.globallogic.evaluacion.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    @UuidGenerator
    private UUID id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    @NotBlank
    @Email(message = "Email debe ser válido")
    private String email;
    @NotBlank
    @Size(min = 8, max = 12, message = "Password debe tener entre 8 y 12 caracteres")
    private String password;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime lastLogin;
    @Column(columnDefinition = "boolean default true")
    private boolean isActive;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Phone> phones;
}
