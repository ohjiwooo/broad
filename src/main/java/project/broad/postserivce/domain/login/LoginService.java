package project.broad.postserivce.domain.login;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.broad.postserivce.domain.member.Member;
import project.broad.postserivce.domain.member.MemberRepo;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepo memberRepo;

    public Member login(String id, String password){
        return memberRepo.findByLoginId(id)
                .filter(m->m.getPassword().equals(password))
                .stream().findFirst()
                .orElse(null);
    }
}
