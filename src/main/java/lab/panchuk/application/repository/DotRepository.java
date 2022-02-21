package lab.panchuk.application.repository;

import lab.panchuk.application.entity.Dot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DotRepository extends JpaRepository<Dot, Long> {
}
