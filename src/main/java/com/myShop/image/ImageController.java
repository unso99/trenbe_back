package com.myShop.image;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/image")
public class ImageController {
    @Value("${file.location}")
    private String fileLocation;

    @PostMapping(
            value = "/get-images",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_GIF_VALUE}
    )
    public byte[] image(@RequestBody ImageDto dto) throws IOException {
        String absolutePath = fileLocation+ File.separator+dto.getImageUrl();

        InputStream is = new FileInputStream(absolutePath);

        return IOUtils.toByteArray(is);

    }
}
