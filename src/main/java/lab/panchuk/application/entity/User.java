package lab.panchuk.application.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id_user", length = 6, nullable = false)
    private Long id;

    @NotEmpty(message = "username shouldn't be empty")
    @Size(min = 5, max = 17, message = "username should be between 5 and 17 characters")
    private String username;

    @NotEmpty(message = "password shouldn't be empty")
    @Min(value = 5, message = "password shouldn't  be less than 5 characters")
    private String password;
}
