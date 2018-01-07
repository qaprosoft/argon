package com.qaprosoft.argon.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatType extends AbstractType {

    private static final long serialVersionUID = -2439074733604141523L;

    @NotNull(message = "Name required")
    private String name;
    @NotNull(message = "Private required")
    private boolean privateEnabled;
    @NotNull(message = "Owner id required")
    private Long ownerId;
    @NotNull(message = "Users id cant be null")
    private List<Long> usersId = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrivateEnabled() {
        return privateEnabled;
    }

    public void setPrivateEnabled(boolean privateEnabled) {
        this.privateEnabled = privateEnabled;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<Long> getUsersId() {
        return usersId;
    }

    public void setUsersId(List<Long> usersId) {
        this.usersId = usersId;
    }
}
