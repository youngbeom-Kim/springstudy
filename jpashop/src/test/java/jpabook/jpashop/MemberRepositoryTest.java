package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest //이 어노테이션이 없으면 멤버값이 들어가지 않아서 NPE오류 발생
class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository;
    
    @Test
    @Transactional //이 어노테이션이 있을 경우 테스트 후에 전부 Rollback 시켜버림
    @Rollback(false) //이 어노테이션이 있을 경우 롤백하지 않고 데이터를 insert함
    @DisplayName("멤버가 잘 저장됐는지 확인")
    void testMember() {
        //given
        Member member = new Member();
        member.setUsername("memberA");
        
        //when
        Long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
        System.out.println("findMember == member: " + (findMember == member));
    }

}