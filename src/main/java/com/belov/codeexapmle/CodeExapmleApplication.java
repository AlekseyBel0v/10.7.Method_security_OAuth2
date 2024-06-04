package com.belov.codeexapmle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/")
@SpringBootApplication
public class CodeExapmleApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeExapmleApplication.class, args);
    }
//чтобы получить доступ к этому эндпоинту необходимо переопределить
    @GetMapping("hi")
    public String hu() {
        return "hi, authenticated user!";
    }

    @RolesAllowed({"ROLE_READ"})
    @GetMapping ("read")
    public String read() {
        return "read";
    }

    @Secured({"ROLE_WRITE"})
    @GetMapping("write")
    public String whrite(){
        return "write";
    }

    /**
     * Возможности SpEL:
     * <a href="https://docs.spring.io/spring-security/reference/5.8/servlet/authorization/expression-based.html">...</a>
     * */

    @PreAuthorize("hasRole('WRITE') or hasAnyAuthority('WRITE')")
    @GetMapping("hello")
    public String hello() {
        return "hello";
    }
}