package com.fimeco.fimeco.domain.pqrs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fimeco.fimeco.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Pqrs")
@Table(name = "pqrs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pqrs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String header;
    private String message;
    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @JsonBackReference
    private User user;


}
