package com.zgurski.controller.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "surname", required = false) String surname,
                           Model model) {

        model.addAttribute("message", "Hello, " + name + " " + surname + "!");
        return "first/hello";
    }

    //@GetMapping("/hello")
    //    public String sayHello(HttpServletRequest request) {
    //        String name = request.getParameter("name");
    //        String surname = request.getParameter("surname");
    //
    // public String sayHello(@RequestParam(value = "name", required = false) String name,
    //                           @RequestParam(value = "surname", required = false) String surname) {

    @GetMapping("/goodbye")
    public String sayBye() {
        return "first/goodbye";
    }

}
