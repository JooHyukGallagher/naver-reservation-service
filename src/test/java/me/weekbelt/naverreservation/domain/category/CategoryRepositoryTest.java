package me.weekbelt.naverreservation.domain.category;

import com.querydsl.jpa.impl.JPAQueryFactory;
import me.weekbelt.naverreservation.web.dto.category.CategoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EntityManager em;
    JPAQueryFactory queryFactory;

    @BeforeEach
    public void before() {
        queryFactory = new JPAQueryFactory(em);
    }

    @DisplayName("CategoryDto 조회")
    @Test
    public void findCategoryDto() throws Exception {
        //given
        List<CategoryDto> result = categoryRepository.findCategoryDto();

        for (CategoryDto categoryDto : result) {
            System.out.println("CategoryDto = " + categoryDto);
        }

        assertThat(result).extracting("categoryId")
                .containsExactly(1L, 2L, 3L, 4L, 5L);
        assertThat(result).extracting("name")
                .containsExactly("전시", "뮤지컬", "콘서트", "클래식", "연극");
        assertThat(result).extracting("count")
                .containsExactly(10, 10, 16, 10, 13);
    }
}