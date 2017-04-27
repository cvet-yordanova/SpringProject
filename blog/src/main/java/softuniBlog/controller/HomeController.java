package softuniBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuniBlog.entity.Essay;
import softuniBlog.repository.EssayRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Controller
public class HomeController {


@Autowired
private EssayRepository essayRepository;

    @GetMapping("/")
    public String index(Model model) {

    List<Essay> essays = this.essayRepository.findAll().stream()
            .sorted((a, b) -> b.getDate().compareTo(a.getDate()))
                .limit(2)
                .collect(Collectors.toList());


        model.addAttribute("view", "home/index");
        model.addAttribute("essays",essays);

        return "base-layout";
    }

    @RequestMapping("/error/403")
    public String accessDenied(Model model){
        model.addAttribute("view", "error/403");

        return "base-layout";
    }
}
