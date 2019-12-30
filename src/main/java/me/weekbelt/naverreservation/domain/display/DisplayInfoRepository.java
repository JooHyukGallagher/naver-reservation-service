package me.weekbelt.naverreservation.domain.display;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Long>, DisplayInfoRepositoryCustom {

    @Query("select di from DisplayInfo di" +
            " join fetch di.product p" +
            " join fetch p.category c" +
            " where di.id = :displayInfoId")
    Optional<DisplayInfo> findDisplayInfoByDisplayInfoId(@Param("displayInfoId") Long displayInfoId);
}
