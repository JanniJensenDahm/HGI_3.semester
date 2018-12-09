package dk.hgigym.controller;

import dk.hgigym.model.Request;
import dk.hgigym.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private RequestRepository requestRepository;


    @GetMapping("/board")
    public String board(Model model) {
        List<Request> requestList = requestRepository.findAll();
        model.addAttribute("requestList", requestList);
        return "board";
    }
}
