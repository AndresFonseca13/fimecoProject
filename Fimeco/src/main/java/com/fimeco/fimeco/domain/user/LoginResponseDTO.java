package com.fimeco.fimeco.domain.user;

import com.fimeco.fimeco.domain.Role.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class LoginResponseDTO {

    private Integer id;
    private String username;
    private Set<?> authorities;
    private String jwt;
    private String message;

    public LoginResponseDTO(){super();}

    public LoginResponseDTO(Integer id,String username, Set<?> authorities, String jwt, String message){
        this.id = id;
        this.username = username;
        this.authorities = authorities;
        this.jwt = jwt;
        this.message = message;
    }


}
