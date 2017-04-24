package softuniBlog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import softuniBlog.entity.Essay;

public interface EssayRepository extends JpaRepository<Essay, Integer>{
}
