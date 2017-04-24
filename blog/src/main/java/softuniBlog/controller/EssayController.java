package softuniBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import softuniBlog.bindingModel.EssayBindingModel;
import softuniBlog.entity.BGTitle;
import softuniBlog.entity.Essay;
import softuniBlog.entity.User;
import softuniBlog.repository.BGTitleRepository;
import softuniBlog.repository.EssayRepository;
import softuniBlog.repository.UserRepository;

@Controller
public class EssayController {

    @Autowired
    private EssayRepository essayRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BGTitleRepository bgtitlerepository;

    @GetMapping("essay/create")
    @PreAuthorize("isAuthenticated()")
    public String create(Model model){
        model.addAttribute("view","essay/create");

        return "base-layout";

    }

    @PostMapping("/essay/create")
    @PreAuthorize("isAuthenticated()")
    public String createProcess(EssayBindingModel essayBindingModel){

        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User userEntity  = this.userRepository.findByEmail(user.getUsername());
        BGTitle bgTitle = this.bgtitlerepository.findByTitle(essayBindingModel.getBgtitle());

        Essay essayEntity = new Essay(
                essayBindingModel.getTitle(),
                bgTitle,
                essayBindingModel.getContent(),
                userEntity
        );

        this.essayRepository.saveAndFlush(essayEntity);

        return "redirect:/";
    }


    @GetMapping("essay/{id}")
    public String details(Model model, @PathVariable Integer id){
        if(!this.essayRepository.exists(id)){
            return "redirect:/";
        }

        Essay essay = this.essayRepository.findOne(id);
        model.addAttribute("essay", essay);
        model.addAttribute("view", "essay/details");

        return "base-layout";

    }

    @GetMapping("/essay/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String edit(@PathVariable Integer id, Model model){
        if(!this.essayRepository.exists(id)){
            return "redirect:/";
        }

        Essay essay = this.essayRepository.findOne(id);

        if(!isUserAuthorOrAdmin(essay)){
            return "redirect:/essay/"+id;
        }

        model.addAttribute("view", "essay/edit");
        model.addAttribute("essay", essay);

        return "base-layout";
    }

    @PostMapping("/essay/edit/{id}")
    @PreAuthorize("isAuthenticated()")
    public String editProcess(@PathVariable Integer id, EssayBindingModel essayBindingModel){
        if(!this.essayRepository.exists(id)){
            return "redirect:/";
        }

        BGTitle newBGtitle = this.bgtitlerepository.findByTitle(essayBindingModel.getBgtitle());
        Essay essay = this.essayRepository.findOne(id);

        if(!isUserAuthorOrAdmin(essay)){
            return "redirect:/essay/"+id;
        }

        essay.setBgTitle(newBGtitle);
        essay.setTitle(essayBindingModel.getTitle());
        essay.setContent(essayBindingModel.getContent());

        this.essayRepository.saveAndFlush(essay);

        return "redirect:/essay/"+essay.getId();
    }

 @GetMapping("essay/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String delete(Model model,@PathVariable Integer id){
     if(!this.essayRepository.exists(id)){
         return "redirect:/";
     }

     Essay essay = this.essayRepository.findOne(id);

     if(!isUserAuthorOrAdmin(essay)){
         return "redirect:/essay/"+id;
     }

     model.addAttribute("essay", essay);
     model.addAttribute("view", "essay/delete");

     return "base-layout";
 }

    @PostMapping("/essay/delete/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deleteProcess(@PathVariable Integer id){
        if(!this.essayRepository.exists(id)){
            return "redirect:/";
        }

        Essay essay = this.essayRepository.findOne(id);

        if(!isUserAuthorOrAdmin(essay)){
            return "redirect:/essay/"+id;
        }
        this.essayRepository.delete(essay);

        return "redirect:/";
    }

    private boolean isUserAuthorOrAdmin(Essay essay){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        User userEntity = this.userRepository.findByEmail(user.getUsername());
        return userEntity.isAdmin() || userEntity.isAuthor(essay);
    }


}
