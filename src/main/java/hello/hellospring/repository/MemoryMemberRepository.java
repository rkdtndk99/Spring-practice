package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    //실무에서는 java쓸 때 list많이 씀
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);  //Map에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //Null일 가능성을 생각해서 Optional로 감싸서 반환함
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        //찾아지면 반환
        //아니면 null값이 optional로 둘러쌓여서 반환됨
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());  //member들 다 반환
    }

    public void clearStore(){
        store.clear();
    }
}
