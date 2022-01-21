package project.broad.postserivce.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.broad.postserivce.domain.Post;
import project.broad.postserivce.repo.JpaPostRepo;
import project.broad.postserivce.repo.MemoryPostRepo;
import project.broad.postserivce.repo.PostRepo;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Transactional
public class PostController {

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

}
