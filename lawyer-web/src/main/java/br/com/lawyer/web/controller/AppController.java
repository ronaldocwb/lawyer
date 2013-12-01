package br.com.lawyer.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {

    @Autowired
    HttpServletRequest request;

    @RequestMapping(value = "/secure", method = RequestMethod.GET)
    public String app(HttpServletRequest request) {
        System.out.println(request);
        return "/secure/index";
    }
}
