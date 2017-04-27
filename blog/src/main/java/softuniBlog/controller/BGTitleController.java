package softuniBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import softuniBlog.entity.BGTitle;
import softuniBlog.entity.Essay;
import softuniBlog.repository.BGTitleRepository;
import softuniBlog.repository.EssayRepository;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BGTitleController {

    @Autowired
    private BGTitleRepository bgTitleRepository;

    @Autowired
    private EssayRepository essayRepository;



    @GetMapping("titles")
    public String listTitles(Model model){
        List<BGTitle> listbgtitle = this.bgTitleRepository.findAll();

        model.addAttribute("listbgtitles", listbgtitle);
        model.addAttribute("view", "bgtitle/list");

        return "base-layout";


    }
    @GetMapping("bgtitle/{id}")
    public String listEssays(@PathVariable Integer id, Model model){
       // BGTitle bgTitle = this.bgTitleRepository.findOne(id);
       // List<Essay> essays = this.essayRepository.findAll()
       //         .stream().filter( a -> a.getBgTitle().equals(bgTitle.getTitle()))
        //
        //        .collect(Collectors.toList());

        List<Essay> n = this.essayRepository.findAll()
                .stream().filter( a -> a.getBgTitle().getId().equals(id)).collect(Collectors.toList());

        model.addAttribute("view", "bgtitle/essays");
        model.addAttribute("essays",n);

        return "base-layout";
    }

}
