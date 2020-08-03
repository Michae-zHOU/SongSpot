package com.songspot.server.repository.model;

import com.songspot.server.controller.model.User;
import com.songspot.server.controller.model.UserType;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "curators", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Curator extends UserModel {

    @Id
    @GeneratedValue(generator = "curator_generator")
    @SequenceGenerator(
            name = "curator_generator",
            sequenceName = "curator_sequence",
            initialValue = 100
    )
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
