package project.broad.postserivce.domain;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Post {

    private Long id;
    private String title;
    private String userId;
    private String content;
    private String password;
    public int hits;

    public Post(){

    }

    public Post(String title, String userId, String content, String password, int hits) {
        this.title = title;
        this.userId = userId;
        this.content = content;
        this.password = password;
        this.hits = hits;
    }
}
