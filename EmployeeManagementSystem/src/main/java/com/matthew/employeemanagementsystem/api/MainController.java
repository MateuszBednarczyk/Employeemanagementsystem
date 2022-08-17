package com.matthew.employeemanagementsystem.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@Slf4j
public class MainController {
    @GetMapping("")
    ResponseEntity<Void> redirect(HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create("http://" + request.getServerName() + ":8081/"))
                .build();
    }
}
