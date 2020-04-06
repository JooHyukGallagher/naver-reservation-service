package me.weekbelt.naverreservation.web.dto.display;

import lombok.Data;
import me.weekbelt.naverreservation.domain.FileInfo;
import me.weekbelt.naverreservation.domain.displayInfoImage.DisplayInfoImage;

import java.time.LocalDateTime;

@Data
public class DisplayInfoImageDto {

    // display_info_image테이블
    private Long displayInfoImageId;
    private Long displayInfoId;

    // file_info 테이블
    private Long fileId;
    private String fileName;
    private String saveFileName;
    private String contentType;
    private boolean deleteFlag;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    public DisplayInfoImageDto(DisplayInfoImage displayInfoImage) {
        this.displayInfoImageId = displayInfoImage.getId();
        this.displayInfoId = displayInfoImage.getDisplayInfo().getId();

        FileInfo fileInfo = displayInfoImage.getFileInfo();
        if (fileInfo != null) {
            this.fileId = fileInfo.getId();
            this.fileName = fileInfo.getFileName();
            this.saveFileName = fileInfo.getSaveFileName();
            this.contentType = fileInfo.getContentType();
            this.deleteFlag = fileInfo.isDeleteFlag();
            this.createDate = fileInfo.getCreateDate();
            this.modifyDate = fileInfo.getModifyDate();
        }
    }
}
