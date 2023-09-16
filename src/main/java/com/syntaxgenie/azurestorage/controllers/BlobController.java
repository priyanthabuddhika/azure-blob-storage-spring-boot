package com.syntaxgenie.azurestorage.controllers;

import com.syntaxgenie.azurestorage.external.storage.Storage;
import com.syntaxgenie.azurestorage.external.storage.StoragePath;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("blob")
public class BlobController {
    private final Storage storage;

    public BlobController(Storage storage) {
        this.storage = storage;
    }

    @GetMapping("/upload")
    public String writeBlobFile() {
        String dataSample = "samples";
        return storage.upload(
                new ByteArrayInputStream(dataSample.getBytes()), StoragePath.PROFILE_IMAGES, "test.txt");
    }
}
