package br.com.lawyer.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class AuthenticationController {

    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String auth() {
        return "auth/login";
    }

}