package dk.hgigym.controller;

import dk.hgigym.model.Request;
import dk.hgigym.model.User;
import dk.hgigym.repository.RequestRepository;
import dk.hgigym.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Date 04. dec. 2018
 */
@Controller
public class RequestController {
    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/createRequest")
    public String createRequest(Model model) {
        model.addAttribute("request", new Request());
        return "create_request";
    }

    @PostMapping("/createRequest")
    public String createRequest(@ModelAttribute Request request) {
        User currentuser = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        request.setRequester(currentuser);
        requestRepository.save(request);

        return "redirect:/board";
    }

    @GetMapping("/request/edit/{id}")
    public String editRequest(@PathVariable Long id, Model model) {
        Request request = requestRepository.findRequest(id);
        model.addAttribute("request", request);
        model.addAttribute("requester", request.getRequester().getEmail());
        if (request.getAssignee() != null) {
            model.addAttribute("assignee", request.getAssignee().getEmail());
        } else {
            model.addAttribute("assignee", "{{NO ASSIGNEE}}");
        }
        return "edit_request";
    }

    @PostMapping("/request/edit")
    public String editRequest(@ModelAttribute Request request, @RequestParam String requester, @RequestParam String assignee) {
        request.setRequester(userRepository.findByEmail(requester));
        if (!assignee.equals("{{NO ASSIGNEE}}")) {
            request.setAssignee(userRepository.findByEmail(assignee));
        }
        requestRepository.save(request);
        return "redirect:/myPage";

    }

    @GetMapping("/request/{id}")
    public String request(Model model, @PathVariable Long id) {
        model.addAttribute(requestRepository.findRequest(id));
        return "request";
    }

    @PostMapping(value = "/request/edit", params = "delete-request")
    public String deleteRequest(@RequestParam Long id){
        requestRepository.deleteRequest(id);
        return "redirect:/myPage";
    }
}
