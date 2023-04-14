package com.example.webnovel.persistence;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long point;

    public User(String name, Long point) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.point = point;
    }

    public User() {

    }

    public Long getPoint() {
        return point;
    }

    public void usePoint(Long pointToUse) throws PointNotEnoughException {
        if (point < pointToUse) {
            throw new PointNotEnoughException();
        }

        this.point -= pointToUse;

    }

    public UUID getId() {
        return id;
    }
}
