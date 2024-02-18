package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
//엔티티 설계시 가급적 Setter를 사용하지 말 것.
//모든 연관관계는 지연로딩으로 설정 (EAGAR X > LAZY O)
//ManyToOne의 default는 EAGAR, OneToMany의 default는 LAZY
//컬렉션은 필드에서 초기화 하자. (컬렉션 자체는 수정(변경)은 최소화하자 > 하이버네이트가 원하는 방향으로 안굴러감)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
