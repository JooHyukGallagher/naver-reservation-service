package me.weekbelt.naverreservation.domain.display;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class DisplayInfoRepositoryImpl {

    private final EntityManager em;

    public List<DisplayInfo> findDisplayInfoWithProduct(Integer start, Integer limit) {
        return em.createQuery("select di from DisplayInfo di" +
                " join fetch di.product p" +
                " join fetch p.category c")
                .setFirstResult(start)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<DisplayInfo> findDisplayInfoWithProductByCategoryId(Long categoryId, Integer start, Integer limit) {
        return em.createQuery("select di from DisplayInfo di" +
                " join fetch di.product p" +
                " join fetch p.category c" +
                " where c.id = :categoryId")
                .setParameter("categoryId", categoryId)
                .setFirstResult(start)
                .setMaxResults(limit)
                .getResultList();
    }

    public Integer countDisplayInfoNumber() {
        return em.createQuery(
                "select di from DisplayInfo di" +
                        " join fetch di.product p" +
                        " join fetch p.category c")
                .getResultList().size();
    }

    public Integer countDisplayInfoNumberByCategoryId(Long categoryId) {
        return em.createQuery(
                "select di from DisplayInfo di" +
                        " join fetch di.product p" +
                        " join fetch p.category c" +
                        " where c.id = :categoryId")
                .setParameter("categoryId", categoryId)
                .getResultList().size();
    }
//
//    public List<DisplayInfo> findDisplayInfoWithProductAndCategoryByDisplayInfoId(Long displayInfoId) {
//        return em.createQuery("select di from DisplayInfo di" +
//                " join fetch di.product p" +
//                " join fetch p.category c" +
//                " where di.id = :displayInfoId")
//                .setParameter("displayInfoId", displayInfoId)
//                .getResultList();
//    }
}
