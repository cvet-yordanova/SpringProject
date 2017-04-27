package softuniBlog.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bgtitles")
public class BGTitle {

    private Integer id;

    private String title;

    private Set<Essay> essays;

    public BGTitle() {
    }

    public BGTitle(String title){
        this.title=title;
        this.essays = new HashSet<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @OneToMany(mappedBy = "bgTitle")
    public Set<Essay> getEssays() {
        return essays;
    }

    public void setEssays(Set<Essay> essays) {
        this.essays = essays;
    }
    

}
