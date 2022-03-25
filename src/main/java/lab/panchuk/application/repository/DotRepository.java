package lab.panchuk.application.repository;

import lab.panchuk.application.entity.Dot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DotRepository extends JpaRepository<Dot, Long> {

    @Query(value = "SELECT * from dots d where d.id_user = :id_user",
    nativeQuery = true)
    List<Dot> findAllByUser_id (@Param("id_user") long user_id);

    @Query(value = "delete from dots d where d.id_user = :id_user",
    nativeQuery = true)
    void deleteAllById_user(@Param("id_user") long user_id);
}
