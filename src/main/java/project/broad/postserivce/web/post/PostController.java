package project.broad.postserivce.web.post;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.broad.postserivce.domain.post.Post;
import project.broad.postserivce.domain.post.PostRepo;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Transactional
public class PostController {

    private final RedisTemplate<String, String> redisTemplate;

    private final PostRepo postRepo;

    //모든 글 보여줌
    @GetMapping("/all")
    public String showAll(Model model){
        List<Post> posts = postRepo.findAll();
        model.addAttribute("posts",posts);
        return "basic/all";
    }
    @GetMapping("/all/{id}")
    public String content(@PathVariable Long id, Model model){
        Post post = postRepo.findPost(id);
        post.setHits(post.getHits()+1);
        model.addAttribute(post);
        return "basic/content";
    }

    //글 쓰러 들어옴
    @GetMapping("/newPost")
    public String writePost(){
        return "basic/newPost";
    }

    // public Post(String title, String userId, String content, int hits)
    //게시버튼 - 게시 후 글 보여줌
    @PostMapping("/newPost")
    public String post(Post post){
        postRepo.save(post);
        return "basic/content";
    }

    @GetMapping("/delete/{id}")
    public String deleteForm(){
        return "basic/delete";
    }

    //글 삭제후 목록으로
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, String password){
        postRepo.delete(id,password);
        return "redirect:/all";
    }

    @PostMapping("/make")
    public String make(){
        for (int i=0; i<50000; i++) {
            Post post= new Post();
            post.setTitle("title"+i);
            post.setContent("content"+i);
            post.setUserid("id"+i);
            post.setPassword("11");
            postRepo.save(post);
        }
        return "basic/all";
    }

    @PostMapping("/redis")
    public String redisTest() {
        this.redisInput();
        return "basic/all";
    }

    private void redisInput() {
        final String key = "a";
        final String data = "1";

        final ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, data);

        final String s = valueOperations.get(key);
    }
}
