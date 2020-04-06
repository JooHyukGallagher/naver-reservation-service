package me.weekbelt.naverreservation.domain.displayInfoImage;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DisplayInfoImageRepository extends JpaRepository<DisplayInfoImage, Long>, DisplayInfoImageCustom {

}
