package dk.hgigym.controller;

import dk.hgigym.model.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Author Janni Jensen-Dahm
 * Date 04. dec. 2018
 */
@Controller
public class RequestController {

    @GetMapping("/newRequest")
    public String newRequest(Model model){
        model.addAttribute("request", new Request());
        return "newRequest";
    }
}
