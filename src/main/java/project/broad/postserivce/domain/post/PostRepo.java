package project.broad.postserivce.domain.post;


import project.broad.postserivce.domain.post.Post;

import java.util.List;

public interface PostRepo {

    public Post findPost(Long id);
    public List<Post> findAll();
    public Post save(Post post);
    public boolean delete(Long id,String password);

}
