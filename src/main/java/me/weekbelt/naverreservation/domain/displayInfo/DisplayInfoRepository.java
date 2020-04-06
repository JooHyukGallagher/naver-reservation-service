package me.weekbelt.naverreservation.domain.displayInfo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DisplayInfoRepository extends JpaRepository<DisplayInfo, Long>, DisplayInfoRepositoryCustom {

}
