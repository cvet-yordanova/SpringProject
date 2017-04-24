package softuniBlog.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import softuniBlog.entity.BGWord;

public interface BGWordRepository extends JpaRepository<BGWord, Integer>{

    BGWord findByWord(String word);
}
