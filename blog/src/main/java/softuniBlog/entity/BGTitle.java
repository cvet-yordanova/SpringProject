package softuniBlog.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class BGTitle {

    private Integer id;

    private String title;

    private String plan;

    private String text;

    private Set<Essay> essays;

    private Set<BGCharacter> characters;

//new hash set

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(columnDefinition = "text")
    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    @Column(columnDefinition = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @OneToMany
    public Set<Essay> getEssays() {
        return essays;
    }

    public void setEssays(Set<Essay> essays) {
        this.essays = essays;
    }

    @OneToMany
    public Set<BGCharacter> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<BGCharacter> characters) {
        this.characters = characters;
    }
}
