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
    @MapsId("linked_account_id")
    @JoinColumn(name = "linked_account_id")
    private LinkedAccount account;

    private boolean validated;

    public CuratorAccount() {
    }

    public CuratorAccount(CuratorAccountKey id, Curator curator, LinkedAccount account) {
        this.id = id;
        this.curator = curator;
        this.account = account;
        this.validated = false;
    }

    public CuratorAccount(CuratorAccountKey id, Curator curator, LinkedAccount account, boolean validated) {
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

    public LinkedAccount getAccount() {
        return account;
    }

    public void setAccount(LinkedAccount account) {
        this.account = account;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuratorAccount)) return false;
        CuratorAccount that = (CuratorAccount) o;
        return isValidated() == that.isValidated() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getCurator(), that.getCurator()) &&
                Objects.equals(getAccount(), that.getAccount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCurator(), getAccount(), isValidated());
    }
}
