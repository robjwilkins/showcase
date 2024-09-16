package com.wilkins.showcase.controller;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping
    @ResponseStatus(CREATED)
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        var fileName = "build/tmp/" + file.getOriginalFilename();
        try (var stream = new BufferedOutputStream(new FileOutputStream(fileName))){
            stream.write(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assert.isTrue(new File(fileName).exists(), "File was uploaded but exists != true");
        return "done";
    }
}
