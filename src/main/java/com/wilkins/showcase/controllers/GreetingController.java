package com.wilkins.showcase.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.*;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.util.StringUtils.isEmpty;

@Slf4j
@RestController
public class GreetingController {

    @GetMapping("/greeting")
    public Greeting getGreeting(@RequestParam(name = "salutation", required = false) String salutationParam,
                                @RequestParam(name = "name", required = false) String nameParam) {

        log.info("A greeting was requested");

        Greeting greeting = Greeting.of("hello", "world");

        if (!isEmpty(salutationParam)) {
            greeting = greeting.withSalutation(salutationParam);
        }
        if (!isEmpty(nameParam)) {
            greeting = greeting.withName(nameParam);
        }

        log.info("Greeting returned: {}", greeting);

        return greeting;
    }

    @GetMapping(value = "/something", produces = "application/zip")
    public ResponseEntity<StreamingResponseBody> download() {
        StreamingResponseBody body = outputStream -> {
            var zip = new ZipOutputStream(outputStream);
            files().forEach(file -> addToZip(file, zip));
            zip.close();
        };
        return ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition
                        .builder("zipfile").filename("something.zip").build().toString())
                .body(body);
    }

    private List<String> files() {
        return List.of("src/main/resources/test-doc-one.pdf",
                "src/main/resources/test-doc-two.pdf");
    }

    private void addToZip(String file, ZipOutputStream zip) {
        var zipEntry = new ZipEntry(name());
        try {
            zip.putNextEntry(zipEntry);
            readFileAndSendTo(zip, file);
        } catch (IOException e) {
            log.error("Exception adding files to zip", e);
        }
    }

    private void readFileAndSendTo(OutputStream outputStream, String fileName) throws IOException {
        InputStream inputStream = new FileInputStream(new File(fileName));
        inputStream.transferTo(outputStream);
    }

    private String name() {
        return UUID.randomUUID() + ".pdf";
    }
}
