package com.songspot.server.repository.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "curators")
public class Curator extends UserModel {

    @Id
    @GeneratedValue(generator = "curator_generator")
    @SequenceGenerator(
            name = "curator_generator",
            sequenceName = "curator_sequence",
            initialValue = 100
    )
    private Long id;

    @OneToMany(mappedBy = "curator", fetch = FetchType.LAZY)
    private Set<CuratorAccount> accounts = new HashSet<>();

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<CuratorAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<CuratorAccount> accounts) {
        this.accounts = accounts;
    }
}
