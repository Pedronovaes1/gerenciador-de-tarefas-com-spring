package com.web.JavaHTML.controller;

import org.springframework.ui.Model;
import com.web.JavaHTML.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {
    private List<Task> tasks = new ArrayList<>();
    
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("tasks",tasks);
        return "index";
    }

    @PostMapping("/addTask")
    public String addTask(Task task){
        tasks.add(task);
        return "redirect:/";
    }

    @PostMapping("/completeTask")
    public String completeTask(@RequestParam Long id){
        tasks.stream().filter(task -> task.getId().equals(id)).findFirst().ifPresent(task -> task.setCompleted(true));
        return "redirect:/";
    }
    @PostMapping("/deleteTask")
    public String deleteTask(@RequestParam Long id){
        tasks.removeIf(task -> task.getId().equals(id));
        return "redirect:/";
    }
}
