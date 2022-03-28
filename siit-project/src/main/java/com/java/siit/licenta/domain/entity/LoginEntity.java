package com.java.siit.licenta.domain.entity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;


@Entity
@Table(name = "login")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Email
    @Column(name = "user_email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "type_of_user")
    private String typeOfUser;

    @Column(name ="group")
    private String loginGroup;

    @Column(name ="series")
    private String loginSeries;

    public boolean getEnabled() {
        return enabled;
    }

}




