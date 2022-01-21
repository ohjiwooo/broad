package project.broad.postserivce.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter @Setter
@Entity
public class Post {
    @Id
    private Long id;
    private String title;
    private String userid;
    private String content;
    private String password;
    public int hits;

    public Post(){


    }

    public Post(String title, String userid, String content, String password, int hits) {
        this.title = title;
        this.userid = userid;
        this.content = content;
        this.password = password;
        this.hits = hits;
    }
}
