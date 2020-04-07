package me.weekbelt.naverreservation.domain.reservationUserComment;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReservationUserCommentRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    ReservationUserCommentRepository reservationUserCommentRepository;

    JPAQueryFactory queryFactory;

    @BeforeEach
    public void init() {
        queryFactory = new JPAQueryFactory(em);
    }

    @Test
    public void averageScore() throws Exception {
        //given
        Double averageScore = reservationUserCommentRepository.findAverageScoreByProductId(3L);
        //when
        //then
        System.out.println(averageScore);
    }
}