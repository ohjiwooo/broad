package project.broad.postserivce.domain.post;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemoryPostRepo implements PostRepo {

    private static Map<Long, Post> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Post findPost(Long id) {
        return store.get(id);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }


    @Override
    public Post save(Post post) {
        post.setId(++sequence);
        store.put(post.getId(),post);
        return post;
    }

    @Override
    public boolean delete(Long id, String password) {
        Post find = store.get(id);
        String findPass = find.getPassword();
        if(password.equals(findPass)){
            store.remove(id);
            return true;
        }
        return false;
    }

    public void clear() {
        store.clear();
    }

}
