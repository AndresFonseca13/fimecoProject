package com.fimeco.fimeco.domain.permission;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    private UUID id;

    @Column(unique = true, nullable = false, updatable = false)
    private String name;

    @PrePersist
    public void generatedUuid(){
        if (id == null){
            id = UUID.randomUUID();
        }
    }
}
