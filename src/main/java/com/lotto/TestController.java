package com.lotto;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/test-2")
    public String test2() {
        return "test-2";
    }

    @GetMapping("/test-header")
    public String testHeader() {
        return "header";
    }

    @GetMapping("/test-3")
    public String test3() {
        return "test-3";
    }

    @GetMapping("/test-4")
    public String test4() {
        return "test-4";
    }
}
