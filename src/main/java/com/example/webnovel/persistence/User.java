package com.example.webnovel.persistence;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "novel_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long point;

    @Column(name="create_dt", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createDt;


    @Column(name="update_dt", nullable = false)
    @UpdateTimestamp
    private LocalDateTime updateDt;

    @Version
    @Column(columnDefinition = "INT DEFAULT 0")
    private int version;

    public User(String name, Long point) {
        this.name = name;
        this.point = point;
    }

    public User() {

    }

    public User(Long id, String name, Long point) {
        this.id = id;
        this.name = name;
        this.point = point;
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

    public Long getId() {
        return id;
    }

    public void chargePoint(Long points) {
        this.point += points;
    }
}
