package me.weekbelt.naverreservation.domain.promotion;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.weekbelt.naverreservation.web.dto.promotion.PromotionDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

@Transactional
@SpringBootTest
class PromotionRepositoryTest {

    @Autowired
    PromotionRepository promotionRepository;

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);
    }

    @DisplayName("PromotionDto 조회")
    @Test
    public void findPromotionDto() throws Exception {
        //given
        List<PromotionDto> result = promotionRepository.findPromotionDto();

        for (PromotionDto promotionDto : result) {
            System.out.println("PromotionDto = " + promotionDto);
        }
    }
}