package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

//엔티티 같은걸 찾아주는 역할 (DAO와 비슷)
@Repository
public class MemberRepositoryCheck {

    @PersistenceContext
    private EntityManager em;

    public Long save(MemberCheck memberCheck) {
        em.persist(memberCheck);
        return memberCheck.getId();
    }

    public MemberCheck find(Long id) {
        return em.find(MemberCheck.class, id);
    }
}
