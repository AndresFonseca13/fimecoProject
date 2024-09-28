package com.fimeco.fimeco.domain.pqrs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fimeco.fimeco.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "Pqrs")
@Table(name = "pqrs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pqrs {

    @Id
    private UUID id;

    private String header;

    private String message;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @JsonBackReference
    private UserEntity userEntity;

    @PrePersist
    public void generatedUuid(){
        if (id == null){
            id = UUID.randomUUID();
        }
    }
}
