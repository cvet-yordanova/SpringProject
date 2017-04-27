package softuniBlog.bindingModel;


import javax.validation.constraints.NotNull;

public class EssayBindingModel {

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private Integer bgtitleid;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getBgtitleid() {
        return bgtitleid;
    }

    public void setBgtitleid(Integer bgtitleid) {
        this.bgtitleid = bgtitleid;
    }
}
