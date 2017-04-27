package softuniBlog.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuniBlog.bindingModel.BGWordAddModel;
import softuniBlog.entity.BGWord;
import softuniBlog.repository.BGWordRepository;

import java.util.List;

@Controller
@RequestMapping("/admin/bgwords")
public class BGWordAdminController {

    @Autowired
    private BGWordRepository bgWordRepository;

    @GetMapping("/create")
    public String addWordCouple(Model model) {

        model.addAttribute("view", "admin/bgword/create");

        return "base-layout";

    }

    @PostMapping("/create")
    public String addWordProcess(BGWordAddModel bgWordAddModel) {
        if (StringUtils.isEmpty(bgWordAddModel.getWord()) || StringUtils.isEmpty(bgWordAddModel.getCorrect())) {
            return "redirect:/admin/bgwords/create";

        }

        BGWord newword = new BGWord();

        newword.setWord(bgWordAddModel.getWord());
        newword.setCorrect(bgWordAddModel.getCorrect());

        this.bgWordRepository.saveAndFlush(newword);
        return "redirect:/admin/bgwords/";


    }




    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("view", "admin/bgwords");
        List<BGWord> bgWords = this.bgWordRepository.findAll();

        model.addAttribute("view", "admin/bgword/list");
        model.addAttribute("bgwords", bgWords);

        return "base-layout";
    }

@GetMapping("edit/{id}")
    public String edit(Model model, @PathVariable Integer id){
        if(!this.bgWordRepository.exists(id)){
            return "redirect:/admin/bgwords/";
        }
        BGWord bgWord = this.bgWordRepository.findOne(id);

        model.addAttribute("bgword", bgWord);
        model.addAttribute("view","admin/bgword/edit");
    return "base-layout";
}

@PostMapping("/edit/{id}")
public String editProcess(@PathVariable Integer id, BGWordAddModel bgWordAddModel){
    if(!this.bgWordRepository.exists(id)){
        return "redirect:/admin/bgwords/";
    }
    BGWord bgWord = this.bgWordRepository.findOne(id);

    bgWord.setWord(bgWordAddModel.getWord());
    bgWord.setCorrect(bgWordAddModel.getCorrect());

    this.bgWordRepository.saveAndFlush(bgWord);


    return "redirect:/admin/bgwords/";


}
}
