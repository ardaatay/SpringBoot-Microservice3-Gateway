package com.sha.gateway.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "USERS")
public class User
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    @Id
    private int userId;

    @Column(nullable = false, unique = true, length = 150)
    private String username;

    @Column(nullable = false)
    private String password;

    @Temporal(TemporalType.DATE)
    private Date created;
}
