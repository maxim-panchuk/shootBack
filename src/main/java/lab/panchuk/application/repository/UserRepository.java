package lab.panchuk.application.repository;

import lab.panchuk.application.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select user from User user where user.username = :username")
    User getByUsername(@Param("username") String username);
}
