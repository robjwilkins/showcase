package com.wilkins.showcase.controller;

import org.springframework.http.ContentDisposition;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
import java.io.OutputStream;

import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/download")
public class DownloadController {
    @GetMapping(produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<StreamingResponseBody> download() {
        return ok()
                .header(CONTENT_DISPOSITION, ContentDisposition
                        .builder("pdf")
                        .filename("demo.pdf")
                        .build().toString())
                .body(this::writeFileToStream);
    }

    private void writeFileToStream(OutputStream outputStream) {
        try (var inputStream = DownloadController.class.getClassLoader().getResourceAsStream("Test.pdf")) {
            outputStream.write(inputStream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
