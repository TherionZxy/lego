package com.zxyono.lego.controller;

import com.zxyono.lego.service.TestService;
import com.zxyono.lego.util.ImageUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "test")
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private ImageUpload imageUpload;

    @RequestMapping(value = "/gets/{name}", method = RequestMethod.GET)
    public String gets(@PathVariable String name) {
        return imageUpload.randomFileName(name);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file) {
        String filepath = imageUpload.saveImage(file);
        return filepath;
    }
}