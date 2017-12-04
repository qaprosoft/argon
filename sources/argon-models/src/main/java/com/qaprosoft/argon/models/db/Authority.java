package com.qaprosoft.argon.models.db;

public class Authority extends AbstractEntity {

    private AuthorityType authority;

    private enum AuthorityType {
        USER, ADMIN
    }

    public AuthorityType getAuthority() {
        return authority;
    }

    public void setAuthority(AuthorityType authority) {
        this.authority = authority;
    }
}
