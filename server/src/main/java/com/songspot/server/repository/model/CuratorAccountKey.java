package com.songspot.server.repository.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Embeddable
public class CuratorAccountKey {

    @NotNull
    @Column(name = "curator_id")
    private Long curatorId;

    @NotNull
    @Column(name = "account_id")
    private Long accountId;

    public CuratorAccountKey() {
    }

    public CuratorAccountKey(@NotNull Long curatorId, @NotNull Long accountId) {
        this.curatorId = curatorId;
        this.accountId = accountId;
    }

    public Long getCuratorId() {
        return curatorId;
    }

    public void setCuratorId(Long curatorId) {
        this.curatorId = curatorId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CuratorAccountKey)) return false;
        CuratorAccountKey that = (CuratorAccountKey) o;
        return Objects.equals(getCuratorId(), that.getCuratorId()) &&
                Objects.equals(getAccountId(), that.getAccountId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCuratorId(), getAccountId());
    }
}
