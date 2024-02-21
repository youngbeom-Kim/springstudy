package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member); //영속성Context에 Member 주입 > 트랜잭션 commit 시점에 db에 반영 > db에 insert 쿼리
    }

    public void findOne(Long id) {
        em.find(Member.class, id); //단건 조회 > 첫번째는 Type 두번째는 PK를 넣기
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findName(String name) {
        return em.createQuery("select m from Member m where m.name =: name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
