package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    @DisplayName("회원가입이 정상적으로 작동하는지")
    //@Rollback(value = false) //Transactional어노테이션이 있으면 데이터가 들어가지 않고 롤백이 된다.
    void 회원가입() {
        //given  > 이런게 주어지고
        Member member = new Member();
        member.setName("kim"); //persist 한다고 insert문이 들어가지는 않는다.

        //when > 이렇게 하면
        Long saveId = memberService.join(member);

        //then > 결과는 이렇게
        //em.flush(); //데이터가 들어가는게 보고 싶으면 이렇게 해서 사용이 가능하다. 실제로는 insert 되었다가 롤백이 된다.
        assertEquals(member, memberService.findOne(saveId));
    }

    @Test
    @DisplayName("동일한 이름의 회원은 가입이 불가능하다.")
    void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        try {
            memberService.join(member2); //예외가 발생해야 한다.
        } catch (IllegalStateException e) {
            return;
        }

        //then
        fail("예외가 발생해야 한다.");
    }
}