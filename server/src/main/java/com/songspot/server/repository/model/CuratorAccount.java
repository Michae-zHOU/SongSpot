package com.songspot.server.repository.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "curator_accounts")
public class CuratorAccount {

    @EmbeddedId
    private CuratorAccountKey id;

    @ManyToOne
    @MapsId("curator_id")
    @JoinColumn(name = "curator_id")
    private Curator curator;

    @OneToOne
    @MapsId("account_id")
    @JoinColumn(name = "account_id")
    private AccountModel account;

    private boolean validated;

    public CuratorAccount() {
    }

    public CuratorAccount(CuratorAccountKey id, Curator curator, AccountModel account) {
        this.id = id;
        this.curator = curator;
        this.account = account;
        this.validated = false;
    }

    public CuratorAccount(CuratorAccountKey id, Curator curator, AccountModel account, boolean validated) {
        this.id = id;
        this.curator = curator;
        this.account = account;
        this.validated = validated;
    }

    public CuratorAccountKey getId() {
        return id;
    }

    public void setId(CuratorAccountKey id) {
        this.id = id;
    }

    public Curator getCurator() {
        return curator;
    }

    public void setCurator(Curator curator) {
        this.curator = curator;
    }

    public AccountModel getAccount() {
        return account;
    }

    public void setAccount(AccountModel account) {
        this.account = account;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
