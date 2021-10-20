package com.example.websockets;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @OneToMany
    Set<Users> followers;

    @OneToMany
    Set<Users> following;

    public Long getId() {
        return id;
    }

}
