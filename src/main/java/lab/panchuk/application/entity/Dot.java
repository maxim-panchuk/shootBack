package lab.panchuk.application.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
@Scope("prototype")
@Table(name = "dots")
public class Dot {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id_dot", length = 6, nullable = false)
    private Long id;
    private Double x;
    private Double y;
    private Double r;
    private String result;
    private String time;
    private Long id_user;
}
