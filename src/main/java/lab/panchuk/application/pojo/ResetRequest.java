package lab.panchuk.application.pojo;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ResetRequest implements Serializable {
    private static final long serialVersionUID = 93344L;

    private String username;
}
