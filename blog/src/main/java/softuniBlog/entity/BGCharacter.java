package softuniBlog.entity;

import javax.persistence.*;

@Entity
public class BGCharacter {

    private Integer id;

    private String name;

    private BGTitle bgTitle;

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
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name="bgtitleid")
    public BGTitle getBgTitle() {
        return bgTitle;
    }

    public void setBgTitle(BGTitle bgTitle) {
        this.bgTitle = bgTitle;
    }
}
