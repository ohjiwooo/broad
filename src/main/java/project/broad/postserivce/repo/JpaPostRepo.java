package project.broad.postserivce.repo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import project.broad.postserivce.domain.Post;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaPostRepo implements PostRepo{

    private final EntityManager em;
    private static long sequence = 0L;

    public JpaPostRepo(EntityManager em) {
        this.em = em;
    }

    @Override
    public Post findPost(Long id) {
        Post post = em.find(Post.class, id);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return em.createQuery("select p from Post p", Post.class)
                .getResultList();
    }

    @Override
    public Post save(Post post) {
        post.setId(++sequence);
        em.persist(post);
        return post;
    }

    @Override
    public boolean delete(Long id, String password) {
        Post post = em.find(Post.class,id);
        String pass = post.getPassword();
        if(pass.equals(password)){
            em.remove(post);
            return true;
        }
       return false;
    }

}
