package com.qaprosoft.argon.models.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PasswordType implements Serializable
{
    private static final long serialVersionUID = 8483235107118081307L;

    @NotNull
    private Long userId;

    @NotEmpty(message = "Password required")
    @Size(min = 5, message = "Too short password")
    private String password;

    @NotEmpty(message = "Password confirmation required")
    private String confirmPassword;

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getConfirmPassword()
    {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }

    @AssertTrue(message = "Password confirmation not matching")
    public boolean isConfirmationValid()
    {
        return password != null && password.equals(confirmPassword);
    }
}