package me.weekbelt.naverreservation.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

public class ImageFile {
    // 파일명 랜덤 생성 메소드
    public static String makeInherenceFile(String originalName) {
        UUID uuid = UUID.randomUUID();
        String savedName = uuid.toString() + "_" + originalName;

        return savedName;
    }

    public static void saveImageFile(MultipartFile commentImage, String saveAddr) {
        try(FileOutputStream fileOutputStream = new FileOutputStream(saveAddr);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            FileInputStream fileInputStream = (FileInputStream) commentImage.getInputStream();
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {

            int readCount;
            byte[] buffer = new byte[1024];

            while((readCount = bufferedInputStream.read(buffer)) != -1) {
                bufferedOutputStream.write(buffer, 0, readCount);
            }

        } catch (Exception e) {
            throw new RuntimeException("File Save Error");
        }
    }
}
