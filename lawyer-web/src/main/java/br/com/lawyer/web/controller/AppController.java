package br.com.lawyer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

    @RequestMapping(value = "/secure", method = RequestMethod.GET)
    public String app() {
        return "/secure/index";
    }
}
