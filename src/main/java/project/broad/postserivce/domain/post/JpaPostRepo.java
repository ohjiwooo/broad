package project.broad.postserivce.domain.post;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaPostRepo implements PostRepo {

    private final EntityManager em;


    public JpaPostRepo(EntityManager em) {
        this.em = em;
    }

    @Override
    public Post findPost(Long id) {
        Post post = em.find(Post.class, id);
        return post;
    }

    @Override
   // @Cacheable(value = "post")
    public List<Post> findAll() {
        return em.createQuery("select p from Post p order by userid desc", Post.class)
                .getResultList();
    }

    @Override
    public Post save(Post post) {
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
