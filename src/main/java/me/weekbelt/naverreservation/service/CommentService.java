package me.weekbelt.naverreservation.service;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.fileInfo.FileInfo;
import me.weekbelt.naverreservation.domain.fileInfo.FileInfoRepository;
import me.weekbelt.naverreservation.domain.product.Product;
import me.weekbelt.naverreservation.domain.product.ProductRepository;
import me.weekbelt.naverreservation.domain.reservationInfo.ReservationInfo;
import me.weekbelt.naverreservation.domain.reservationInfo.ReservationInfoRepository;
import me.weekbelt.naverreservation.domain.reservationUserComment.ReservationUserComment;
import me.weekbelt.naverreservation.domain.reservationUserComment.ReservationUserCommentRepository;
import me.weekbelt.naverreservation.domain.reservationUserCommentImage.ReservationUserCommentImage;
import me.weekbelt.naverreservation.domain.reservationUserCommentImage.ReservationUserCommentImageRepository;
import me.weekbelt.naverreservation.util.ImageFile;
import me.weekbelt.naverreservation.web.dto.comment.CommentDto;
import me.weekbelt.naverreservation.web.dto.comment.CommentResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CommentService {

    @Value("${property.image.url}")
    private String PATH;
    private static final String COMMENT_IMAGE = "review_img/";

    private final ReservationUserCommentRepository reservationUserCommentRepository;
    private final ReservationUserCommentImageRepository reservationUserCommentImageRepository;
    private final ProductRepository productRepository;
    private final ReservationInfoRepository reservationInfoRepository;
    private final FileInfoRepository fileInfoRepository;


    public List<CommentDto> findCommentDto(Long productId) {
        List<ReservationUserComment> reservationUserComments = reservationUserCommentRepository.findReservationUserCommentsByProductId(productId);

        return reservationUserComments.stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }

    public Double findAverageScore(Long productId) {
        Double averageScore = reservationUserCommentRepository.findAverageScoreByProductId(productId);
        if (averageScore == null){
            return 0.0;
        }
        return averageScore;
    }

    @Transactional
    public Long saveComment(MultipartFile commentImage, String comment,
                            Long productId, Long reservationInfoId, int score) {
        // 엔티티 호출
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다. productId=" + productId));
        ReservationInfo reservationInfo = reservationInfoRepository.findById(reservationInfoId)
                .orElseThrow(() -> new IllegalArgumentException("해당 예약정보가 없습니다. reservationInfoId=" + reservationInfoId));

        // ReservationUserComment 엔티티 생성
        ReservationUserComment reservationUserComment = ReservationUserComment.builder()
                .product(product)
                .reservationInfo(reservationInfo)
                .score(score)
                .comment(comment)
                .build();

        if (commentImage == null) {
            return reservationUserCommentRepository.save(reservationUserComment).getId();
        } else {

            ReservationUserComment savedReservationUserComment = reservationUserCommentRepository.save(reservationUserComment);

            String fileName = ImageFile.makeInherenceFile(commentImage.getOriginalFilename());
            String saveFileName = COMMENT_IMAGE + fileName;

            // 이미지 로컬에 저장
            ImageFile.saveImageFile(commentImage, PATH + saveFileName);

            // FileInfo 엔티티 생성
            FileInfo fileInfo = FileInfo.builder()
                    .fileName(fileName)
                    .saveFileName(saveFileName)
                    .contentType(commentImage.getContentType())
                    .build();

            FileInfo savedFileInfo = fileInfoRepository.save(fileInfo);

            // ReservationUserCommentImage 엔티티 생성
            ReservationUserCommentImage reservationUserCommentImage = ReservationUserCommentImage.builder()
                    .reservationInfo(reservationInfo)
                    .reservationUserComment(savedReservationUserComment)
                    .fileInfo(savedFileInfo)
                    .build();

            return reservationUserCommentImageRepository.save(reservationUserCommentImage).getReservationUserComment().getId();
        }
    }

    public CommentResponse findCommentResponse(Long commentId) {
        ReservationUserComment reservationUserComment = reservationUserCommentRepository.findReservationUserCommentByCommentId(commentId)
                .orElseThrow(() -> new IllegalArgumentException("해당 한줄평 정보가 없습니다. commentId=" + commentId));

        return new CommentResponse(reservationUserComment);
    }
}
