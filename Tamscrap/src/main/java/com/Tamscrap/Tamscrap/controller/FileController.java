package com.Tamscrap.Tamscrap.controller;

 import com.Tamscrap.Tamscrap.serviceImpl.StorageService;
 
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {

    private final StorageService storageService;

    public FileController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/files/{filenombre:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filenombre){
        Resource file = storageService.loadAsResource(filenombre);
        return ResponseEntity.ok().body(file);
    }
}
