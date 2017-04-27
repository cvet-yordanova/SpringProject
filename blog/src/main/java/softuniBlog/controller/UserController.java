package softuniBlog.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import softuniBlog.bindingModel.EssayBindingModel;
import softuniBlog.bindingModel.UserBindingModel;
import softuniBlog.entity.Role;
import softuniBlog.entity.User;
import softuniBlog.repository.RoleRepository;
import softuniBlog.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class UserController {


    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("view", "user/register");

        return "base-layout";
    }

    @PostMapping("/register")
    public String registerProcess(UserBindingModel userBindingModel){

        if(!userBindingModel.getPassword().equals(userBindingModel.getConfirmPassword())){

            return "redirect:/register";
        }

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String imageDatabasePath = null;

        String[] allowedTypes = {
                "image/png",
                "image/jpg",
                "image/jpeg"
        };
        boolean isTypeAllowed = Arrays.asList(allowedTypes).contains(userBindingModel.getImage().getContentType());

        if(isTypeAllowed){

            String imagesPath = "\\src\\main\\resources\\static\\images\\";
            String imagePathRoot = System.getProperty("user.dir");
            String imageSaveDirectory = imagePathRoot+imagesPath;
            String filename = userBindingModel.getImage().getOriginalFilename();
            String savePath = imageSaveDirectory+filename;


            File imageFile = new File(savePath);
            try {
                userBindingModel.getImage().transferTo(imageFile);
                imageDatabasePath = "/images/" + filename;
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }


        }


        User user = new User(
                userBindingModel.getEmail(),
                userBindingModel.getFullName(),
                bCryptPasswordEncoder.encode(userBindingModel.getPassword()),
                imageDatabasePath
        );

        Role userRole = this.roleRepository.findByName("ROLE_USER");

        user.addRole(userRole);
        this.userRepository.saveAndFlush(user);

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("view", "user/login");

        return "base-layout";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        return "redirect:/login?logout";
    }

    @GetMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String profilePage(Model model){
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User user = this.userRepository.findByEmail(principal.getUsername());

        model.addAttribute("user", user);
        model.addAttribute("view", "user/profile");

        return "base-layout";
    }


}