package com.java.siit.licenta.domain.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {


    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String typeOfUser;
    private boolean enabled;

    public boolean getEnabled() {
        return enabled;
    }
}
