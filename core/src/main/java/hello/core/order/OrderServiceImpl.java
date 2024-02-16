package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //필수 값(final)이 붙은 객체에 생성자를 자동으로 생성해줌
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy;
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;

    //필드 주입은 권장하지 않음 (외부에서 변경이 불가능하기 때문에 테스트하기 힘듬)
    //사용하기 위해서는 setter를 생성해야함 결국 수정자 주입을 하는 경우랑 같음
    //테스트 목적으로만 사용 추천
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    //@Autowired //생성자가 하나 일 경우에는 @Autowired가 붙지 않아도 됨
    //@Qualifier가 이름을 찾이못하면 같은 이름을 가진 클래스를 찾는다.
    public OrderServiceImpl(MemberRepository memberRepository, /* @Qualifier("mainDiscountPolicy") */ @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    //일반 메서드 주입(거의 사용하지 않음)
    //Spring Bean이 아닌 경우에 autowired를 사용해도 아무런 작동을 하지 않음
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
