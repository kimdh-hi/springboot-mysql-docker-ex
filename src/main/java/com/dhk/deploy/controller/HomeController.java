package com.dhk.deploy.controller;

import com.dhk.deploy.domain.Contents;
import com.dhk.deploy.repository.ContentsRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ContentsRepository contentsRepository;

    @GetMapping("/")
    public String home() {
        return "form";
    }

    @PostMapping("/")
    public String form(@ModelAttribute ContentRequest content) {
        contentsRepository.save(new Contents(content.getContent()));
        return "redirect:/";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Contents> contents = new ArrayList<>();
        Iterable<Contents> contentsIterable = contentsRepository.findAll();
        contentsIterable.forEach(contents::add);

        model.addAttribute("contents", contents);
        return "list";
    }

    @Data
    @AllArgsConstructor
    static class ContentRequest{
        private String content;
    }
}
