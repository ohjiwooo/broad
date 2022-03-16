package project.broad.postserivce.domain.member;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface MemberRepo {

    public Member save(Member member);
    public Member findById(Long id);
    public List<Member> findAll();
    public Optional<Member> findByLoginId(String loginId);
}
