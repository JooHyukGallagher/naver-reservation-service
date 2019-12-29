package me.weekbelt.naverreservation.domain.display;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Long> {
//
//    @Query("select count(di) from DisplayInfo di" +
//            " join fetch di.product p" +
//            " join fetch p.category c")
//    Integer countDisplayInfoNumber();
//
//    @Query("select count(di) from DisplayInfo di" +
//            " join fetch di.product p" +
//            " join fetch p.category c" +
//            " where c.id = :categoryId")
//    Integer countDisplayInfoNumberByCategoryId(@Param("categoryId") Long categoryId);

    @Query("select di from DisplayInfo di" +
            " join fetch di.product p" +
            " join fetch p.category c" +
            " where di.id = :displayInfoId")
    DisplayInfo findDisplayInfoWithProductAndCategoryByDisplayInfoId(@Param("displayInfoId") Long displayInfoId);
}
