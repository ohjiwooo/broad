package project.broad.postserivce.web.login;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class loginForm {

    @NotEmpty
    private String id;
    @NotEmpty
    private String password;
}
