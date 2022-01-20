package project.broad.postserivce.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.broad.postserivce.domain.Post;
import project.broad.postserivce.repo.MemoryPostRepo;
import project.broad.postserivce.repo.PostRepo;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final MemoryPostRepo memoryPostRepo;

    //모든 글 보여줌
    @GetMapping("/all")
    public String showAll(Model model){
        List<Post> resultAll = memoryPostRepo.findAll();
        model.addAttribute(resultAll);
        return "basic/all";
    }

    //글 쓰러 들어옴
    @GetMapping("/write")
    public String writePost(){
        return "write";
    }

    // public Post(String title, String userId, String content, int hits)
    //게시버튼 - 게시 후 목록으로
    @PostMapping("/post")
    public String post(Post post, Model model){
        memoryPostRepo.save(post);
        return "all";
    }

    @GetMapping("/delete")
    public String delete(){
        return "delete";
    }

    //글 삭제후 목록으로
    @PostMapping("/delete")
    public String delete(@RequestParam Long id, String password){
        memoryPostRepo.delete(id,password);
        return "all";
    }

}
