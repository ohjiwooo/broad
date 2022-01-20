package project.broad.postserivce.repo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import project.broad.postserivce.domain.Post;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemoryPostRepoTest {
    MemoryPostRepo memoryPostRepo = new MemoryPostRepo();

    @AfterEach
    void afterEach(){
        memoryPostRepo.clear();
    }

    @Test
    void findAll() {
        //given
        Post post1 = new Post("title","jiwoo","hi jiwoo","123",0);
        Post post2 = new Post("title2","ojw","hi ojw","567",0);
        memoryPostRepo.save(post1);
        memoryPostRepo.save(post2);
        //when
        List<Post> result = memoryPostRepo.findAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(2);
        Assertions.assertThat(result).contains(post1,post2);
    }

    @Test
    void save() {
        //given  public Post(String title, String userId, String content, String password, int hits) {
        Post post = new Post("title","jiwoo","hi jiwoo","123",0);
        //when
        Post savePost = memoryPostRepo.save(post);
        Post findPost = memoryPostRepo.findPost(savePost.getId());
        //then
        Assertions.assertThat(savePost).isEqualTo(findPost);
    }

    @Test
    void delete() {
        //given
        Post post1 = new Post("title","jiwoo","hi jiwoo","123",0);
        Post post2 = new Post("title2","ojw","hi ojw","567",0);
        memoryPostRepo.save(post1);
        memoryPostRepo.save(post2);
        //when
        memoryPostRepo.delete(post1.getId(), post1.getPassword());
        List<Post> result = memoryPostRepo.findAll();
        //then
        Assertions.assertThat(result.size()).isEqualTo(1);
        Assertions.assertThat(result).doesNotContain(post1);
    }

}