package jpabook.jpashop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest //이 어노테이션이 없으면 멤버값이 들어가지 않아서 NPE오류 발생
class MemberCheckRepositoryCheckTest {

    @Autowired
    MemberRepositoryCheck memberRepositoryCheck;
    
    @Test
    @Transactional //이 어노테이션이 있을 경우 테스트 후에 전부 Rollback 시켜버림
    @Rollback(false) //이 어노테이션이 있을 경우 롤백하지 않고 데이터를 insert함
    @DisplayName("멤버가 잘 저장됐는지 확인")
    void testMember() {
        //given
        MemberCheck memberCheck = new MemberCheck();
        memberCheck.setUsername("memberA");
        
        //when
        Long saveId = memberRepositoryCheck.save(memberCheck);
        MemberCheck findMemberCheck = memberRepositoryCheck.find(saveId);

        //then
        assertThat(findMemberCheck.getId()).isEqualTo(memberCheck.getId());
        assertThat(findMemberCheck.getUsername()).isEqualTo(memberCheck.getUsername());
        assertThat(findMemberCheck).isEqualTo(memberCheck);
        System.out.println("findMember == member: " + (findMemberCheck == memberCheck));
    }

}