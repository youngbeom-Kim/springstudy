package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //읽기가 아닌 곳에 @Transactional을 사용하여 사용하는걸 권장
@RequiredArgsConstructor //final이 있는 객체의 생성자를 알아서 생성해줌 > Lombok
public class MemberService {

    //@Autowired
    //이렇게 사용할 경우 데이터 변경이 불가능함
    private final MemberRepository memberRepository;

//    @Autowired //생성자가 하나만 있을 경우 @Autowired가 없어도 알아서 인식함
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원 가입
    @Transactional //읽기가 아닌 곳은 절대 사용 하면 안됨 (넣을 경우 데이터 변경이 안됨)
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //중복 아이디 검사
    private void validateDuplicateMember(Member member) {
        //이렇게 사용할 경우 동일한 이름이 동시에 가입 시 문제가 발 생하기 때문에 name에 unique 제약을 거는것이 좋음
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    //회원 전체 조회
    //@Transactional(readOnly = true) //조회하는 메서드에서는 좀 더 최적화 되어 있음
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    //회원 단일 조회
    //@Transactional(readOnly = true) //읽기에는 가급적 readOnly = true를 넣어줄 것
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
