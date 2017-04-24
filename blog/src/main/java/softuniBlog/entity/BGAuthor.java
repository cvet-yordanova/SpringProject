package softuniBlog.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class BGAuthor {

    private Integer id;

    private String name;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }


}
