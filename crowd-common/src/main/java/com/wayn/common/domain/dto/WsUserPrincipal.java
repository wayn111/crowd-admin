package com.wayn.common.domain.dto;

import lombok.Getter;

import java.util.Objects;

@Getter
public class WsUserPrincipal implements java.security.Principal {
    private String id;
    private String userNname;

    public WsUserPrincipal(String id, String userNname) {
        this.id = id;
        this.userNname = userNname;
    }


    @Override
    public String getName() {
        return getId();
    }

    @Override
    public String toString() {
        return "WsUserPrincipal{" +
                "id='" + id + '\'' +
                ", userNname='" + userNname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WsUserPrincipal that = (WsUserPrincipal) o;
        return Objects.equals(id, that.id) && Objects.equals(userNname, that.userNname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userNname);
    }
}

