package me.weekbelt.naverreservation.web.controller;

import lombok.RequiredArgsConstructor;
import me.weekbelt.naverreservation.domain.ImageType;
import me.weekbelt.naverreservation.service.ImageService;
import me.weekbelt.naverreservation.web.dto.display.DisplayInfoImageDto;
import me.weekbelt.naverreservation.web.dto.product.ProductImageDto;
import me.weekbelt.naverreservation.web.dto.reservation.ReservationCommentImageDto;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.io.File;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ImageApiController {

    final static String CLASS_PATH = "c:/tmp/";

    private final ImageService imageService;

    @GetMapping("/productImages/{productId}")
    public ResponseEntity<Resource> getProductImage(@PathVariable Long productId, @RequestParam ImageType type) {
        List<ProductImageDto> productImageDtoList = imageService.getProductImageDto(productId, type);
        ProductImageDto productImageDto = productImageDtoList.get(0);

        File fileImage = new File(CLASS_PATH + productImageDto.getSaveFileName());
        long fileLength = fileImage.length();
        String saveFileName = CLASS_PATH + productImageDto.getSaveFileName();
        String contentType = productImageDto.getContentType();

        return setResponseImage(fileLength, saveFileName, contentType);
    }

    @GetMapping("/reviewCommentImage/{commentId}")
    public ResponseEntity<Resource> getReviewCommentImage(@PathVariable Long commentId) {
        ReservationCommentImageDto commentImage = imageService.getReservationCommentImageDto(commentId);

        File fileImage = new File(CLASS_PATH + commentImage.getSaveFileName());
        long fileLength = fileImage.length();
        String saveFileName = CLASS_PATH + commentImage.getSaveFileName();
        String contentType = commentImage.getContentType();

        return setResponseImage(fileLength, saveFileName, contentType);
    }

    @GetMapping("/displayInfoImage/{displayInfoId}")
    public ResponseEntity<Resource> getDisplayInfoImage(@PathVariable Long displayInfoId) {
        DisplayInfoImageDto displayInfoImageDto = imageService.getDisplayInfoImageDto(displayInfoId);

        File fileImage = new File(CLASS_PATH + displayInfoImageDto.getSaveFileName());
        long fileLength = fileImage.length();
        String saveFileName = CLASS_PATH + displayInfoImageDto.getSaveFileName();
        String contentType = displayInfoImageDto.getContentType();

        return setResponseImage(fileLength, saveFileName, contentType);
    }

    private ResponseEntity<Resource> setResponseImage(long fileLength, String saveFileName, String contentType) {
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_LENGTH, String.valueOf(fileLength))
                .header(HttpHeaders.CONTENT_TYPE, contentType)
                .body(new FileSystemResource(saveFileName));
    }
}
