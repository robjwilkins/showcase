package com.wilkins.showcase.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    private static final Logger log = LoggerFactory.getLogger(DocumentController.class);

    @GetMapping(produces = "application/zip")
    public ResponseEntity<StreamingResponseBody> download() {
        return ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        ContentDisposition.builder("zipfile").filename("documents.zip").build().toString())
                .body(DocumentController::asZipFile);
    }

    private static void asZipFile(OutputStream outputStream) throws IOException {
        try (var zip = new ZipOutputStream(outputStream)) {
            files().forEach(file -> addToZip(file, zip));
        }
    }

    private static List<String> files() {
        return List.of("src/main/resources/test-doc-one.pdf",
                "src/main/resources/test-doc-two.pdf");
    }

    private static void addToZip(String file, ZipOutputStream zip) {
        var zipEntry = new ZipEntry(name());
        try {
            zip.putNextEntry(zipEntry);
            readFileAndSendTo(zip, file);
        } catch (IOException e) {
            log.error("Exception adding files to zip", e);
        }
    }

    private static void readFileAndSendTo(OutputStream outputStream, String fileName) throws IOException {
        try (var inputStream = new FileInputStream(fileName)) {
            inputStream.transferTo(outputStream);
        }
    }

    private static String name() {
        return UUID.randomUUID() + ".pdf";
    }
}
