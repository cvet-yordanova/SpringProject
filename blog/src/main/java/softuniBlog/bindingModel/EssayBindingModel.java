package softuniBlog.bindingModel;


import javax.validation.constraints.NotNull;

public class EssayBindingModel {

    @NotNull
    private String title;

    @NotNull
    private String bgtitle;

    @NotNull
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBgtitle() {
        return bgtitle;
    }

    public void setBgtitle(String bgtitle) {
        this.bgtitle = bgtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
