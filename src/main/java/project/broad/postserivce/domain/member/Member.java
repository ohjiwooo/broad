package project.broad.postserivce.domain.member;


import lombok.Data;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {


    private Long Id;
    @NotEmpty
    private String loginId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;

}
