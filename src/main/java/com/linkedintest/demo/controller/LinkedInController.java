package com.linkedintest.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LinkedInController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody String isAlive() {
        return "Application is ready!";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public @ResponseBody String test() {
        return "URL is working fine without Oauth!";
    }

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public @ResponseBody String authenticated() {
        return "Authentication Success!";
    }
}
