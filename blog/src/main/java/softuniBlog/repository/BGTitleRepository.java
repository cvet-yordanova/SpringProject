package softuniBlog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import softuniBlog.entity.BGTitle;

public interface BGTitleRepository extends JpaRepository<BGTitle, Integer>{
    BGTitle findByTitle(String title);
}
