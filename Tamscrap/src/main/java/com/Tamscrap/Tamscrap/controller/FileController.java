package com.Tamscrap.Tamscrap.controller;

 import com.Tamscrap.Tamscrap.serviceImpl.StorageServiceImpl;
 
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileController {

    private final StorageServiceImpl storageServiceImpl;

    public FileController(StorageServiceImpl storageServiceImpl) {
        this.storageServiceImpl = storageServiceImpl;
    }

    @GetMapping("/files/{filenombre:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filenombre){
        Resource file = storageServiceImpl.loadAsResource(filenombre);
        return ResponseEntity.ok().body(file);
    }
}
