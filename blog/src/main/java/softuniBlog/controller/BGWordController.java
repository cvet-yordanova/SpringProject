package softuniBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import softuniBlog.bindingModel.BGWordTestModel;
import softuniBlog.entity.BGWord;
import softuniBlog.entity.Essay;
import softuniBlog.repository.BGWordRepository;
import softuniBlog.repository.EssayRepository;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class BGWordController {
    @Autowired
    private BGWordRepository bgWordRepository;


    @GetMapping("/test")
    public String listwords(Model model){

        Random random = new Random();

        int size =this.bgWordRepository.findAll().size()+1;

        List<BGWord> bgWords = new ArrayList<BGWord>();

        for(int i = 0 ; i < 5; i++){
            int r = random.nextInt(size);
            if(r ==0){
                i--;
                continue;
            }
            BGWord currentNewWord = this.bgWordRepository.findOne(r);

            if(bgWords.stream().anyMatch(word -> word.getWord().equals(currentNewWord.getWord()))){
                i--;
                continue;
            }

            bgWords.add(currentNewWord);
        }

        model.addAttribute("wordlist", bgWords);
        model.addAttribute("view","bgword/word");


        return "base-layout";
    }

    @PostMapping("/test")
    public String check(BGWordTestModel bgWordTestModel, Model model){

        List<String> result = new ArrayList<String>();

        String everything = bgWordTestModel.getEverythingAsString();
        List<String> list = Arrays.asList(everything.split(","));
        list.stream().forEach(a -> a.trim());

        List<String> givenwords = new ArrayList<>();
        List<String> inputwords = new ArrayList<>();
        List<String> style = new ArrayList<>();

        for (int i = 0; i<10; i+=2){

           String word =list.get(i).toString();
           String input = list.get(i+1).toString();

           givenwords.add(word);
           inputwords.add(input);

            if(isCorrect(this.bgWordRepository.findByWord(word).getCorrect(), input)){
                result.add("Вярно");
                style.add("correct");
            }
            else {
                result.add("Грешно");
                style.add("wrong");
            }

       }
        model.addAttribute("style",style);
        model.addAttribute("givenwords",givenwords);
        model.addAttribute("inputwords",inputwords);
        model.addAttribute("result", result);
        model.addAttribute("view","bgword/wordres");

        return "base-layout";

    }

    private boolean isCorrect(String word, String input){

        return Objects.equals(word,input);
    }
}
