package project.broad.postserivce.web.login;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.broad.postserivce.domain.login.LoginService;
import project.broad.postserivce.domain.member.Member;
import project.broad.postserivce.domain.member.MemberRepo;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {

   private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute loginForm form, BindingResult bindingResult){

        if(bindingResult.hasErrors()){ //오류시 다시
            return "login/loginForm";
        }
        Member member = loginService.login(form.getId(), form.getPassword());

        if(member==null){ //로그인 실패
            bindingResult.reject("loginError","아이디나 비밀번호를 확인해주세요.");
        }
        //로그인 성공
        return "redirect:/";
    }


}
