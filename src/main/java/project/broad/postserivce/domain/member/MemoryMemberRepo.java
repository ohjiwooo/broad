package project.broad.postserivce.domain.member;


import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepo implements MemberRepo{

    private static Map<Long,Member>  store = new HashMap<>();
    private static long sequence = 0L;

    /*
    * 회원가입
    * */
    public Member save(Member member){

        member.setId(++sequence);
        store.put(member.getId(),member);
        return member;

    }
    /*
    * 아이디로 회원찾기
    * */
    public Member findById(Long id){
        return store.get(id);
    }

    /*
    * 전체회원 반환
    * */
    public List<Member> findAll(){
        return new ArrayList<Member>(store.values());
    }
    /*
    * 회원아이디로 회원찾기
    * */

    public Optional<Member> findByLoginId(String loginId){

        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

}
