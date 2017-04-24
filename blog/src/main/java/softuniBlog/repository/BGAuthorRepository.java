package softuniBlog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import softuniBlog.entity.BGAuthor;

public interface BGAuthorRepository extends JpaRepository<BGAuthor, Integer>{

    BGAuthor findByName(String name);
}
