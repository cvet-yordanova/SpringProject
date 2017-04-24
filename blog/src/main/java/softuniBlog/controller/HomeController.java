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

@Controller
public class HomeController {


@Autowired
private EssayRepository essayRepository;

    @GetMapping("/")
    public String index(Model model) {

    List<Essay> essays = this.essayRepository.findAll();

     //   List<Essay> essays = new ArrayList<Essay>();

  //  int size = this.essayRepository.findAll().size();
        Random random = new Random();
        int r = random.nextInt(4);

     //   for(int i =0; i < 2; i++){
      //      int id = random.nextInt(size);
      //      essays.add(this.essayRepository.findOne(id));
      //  }
     //   essays.add(this.essayRepository.findOne(r));

        model.addAttribute("random",essays.get(0).getDate());
      //  model.addAttribute("number",r);
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
