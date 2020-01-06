package me.weekbelt.naverreservation.web.dto.comment;

import lombok.Data;
import me.weekbelt.naverreservation.domain.FileInfo;
import me.weekbelt.naverreservation.domain.reservation.ReservationUserCommentImage;

import java.time.LocalDateTime;

@Data
public class CommentImageDto {

    // reservation_user_comment_image 테이블
    private Long imageId;
    private Long reservationInfoId;
    private Long reservationUserCommentId;
    private Long fileId;

    // file_info 테이블
    private String fileName;
    private String saveFileName;
    private String contentType;
    private boolean deleteFlag;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public CommentImageDto(ReservationUserCommentImage reservationUserCommentImage) {
        this.imageId = reservationUserCommentImage.getId();
        this.reservationInfoId = reservationUserCommentImage.getReservationInfo().getId();
        this.reservationUserCommentId = reservationUserCommentImage.getReservationUserComment().getId();

        FileInfo fileInfo = reservationUserCommentImage.getFileInfo();
        if (fileInfo != null) {
            this.fileId = reservationUserCommentImage.getFileInfo().getId();
            this.fileName = fileInfo.getFileName();
            this.saveFileName = fileInfo.getSaveFileName();
            this.contentType = fileInfo.getContentType();
            this.deleteFlag = fileInfo.isDeleteFlag();
            this.createDate = fileInfo.getCreateDate();
            this.modifyDate = fileInfo.getModifyDate();
        }
    }

}
