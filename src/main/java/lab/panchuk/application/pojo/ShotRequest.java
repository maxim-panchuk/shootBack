package lab.panchuk.application.pojo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ShotRequest implements Serializable {
    private static final long serialVersionUID = 393844L;

    private String x;
    private String y;
    private String r;
    private String username;
}
