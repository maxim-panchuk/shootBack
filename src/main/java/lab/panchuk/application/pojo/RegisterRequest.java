package lab.panchuk.application.pojo;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegisterRequest implements Serializable {
    private static final long serialVersionUID = 93844L;

    private String username;
    private String password;
}
