package lab.panchuk.application.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    private boolean result;
    private String time;
}
