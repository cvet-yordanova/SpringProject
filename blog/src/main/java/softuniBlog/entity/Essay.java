package softuniBlog.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Essay {

    private Integer Id;

    private String title;

    private User author;

    private String content;

    public BGTitle bgTitle;

    private Date date = new Date();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @Column
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne
    @JoinColumn(name="authorId", nullable = false)
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Column(columnDefinition = "text", nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ManyToOne
    @JoinColumn(name="bgtitleid", nullable = false)
    public BGTitle getBgTitle() {
        return bgTitle;
    }

    public void setBgTitle(BGTitle bgTitle) {
        this.bgTitle = bgTitle;
    }

    @Column
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Essay() {}

    public Essay(String title, BGTitle bgtitle, String content, User author) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.bgTitle = bgtitle;
    }

    @Transient
    public String getSummary(){
        return this.getContent().substring(0,this.getContent().length()/2)+"...";
    }
}
