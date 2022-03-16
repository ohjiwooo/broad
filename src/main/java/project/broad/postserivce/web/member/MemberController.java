package project.broad.postserivce.web.member;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project.broad.postserivce.domain.member.Member;
import project.broad.postserivce.domain.member.MemberRepo;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberRepo memberRepo;

    @GetMapping("/add")
    public String addForm(){
        return "members/addForm";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute Member member, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "members/addForm";
        }
        memberRepo.save(member);
        return "redirect:/";

    }
}
