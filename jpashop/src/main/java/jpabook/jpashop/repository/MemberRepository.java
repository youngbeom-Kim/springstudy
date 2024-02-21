package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor //@PersistenceContext를 사용하지 않아도 됨
public class MemberRepository {

//    @PersistenceContext //@Autowired //Springboot + jpa가 autowired를 사용해도 되도록 지원해줌
//    private EntityManager em;

    //Springboot + JPA가 지원해줌
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member); //영속성Context에 Member 주입 > 트랜잭션 commit 시점에 db에 반영 > db에 insert 쿼리
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id); //단건 조회 > 첫번째는 Type 두번째는 PK를 넣기
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name =: name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
