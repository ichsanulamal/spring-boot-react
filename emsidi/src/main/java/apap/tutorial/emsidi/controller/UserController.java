package apap.tutorial.emsidi.controller;

import apap.tutorial.emsidi.model.RoleModel;
import apap.tutorial.emsidi.model.UserModel;
import apap.tutorial.emsidi.service.RoleService;
import apap.tutorial.emsidi.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/add")
    private String addUserFormPage(Model model) {
        UserModel user = new UserModel();
        List<RoleModel> listRole = roleService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("listRole", listRole);

        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)
                SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        System.out.println(authorities);
        return "form-add-user";
    }

//    @PostMapping(value = "/add")
//    private String addUserSubmit(@ModelAttribute UserModel user, Model model) {
//        userService.addUser(user);
//        model.addAttribute("user", user);
//        return "redirect:/";
//    }

    @PostMapping(value = "/add")
    private String addUserSubmit(@ModelAttribute UserModel userModel,
                                 RedirectAttributes attributes) {


        String a = userService.addUser(userModel);
        attributes.addFlashAttribute("notif", a);
        return "redirect:/";
    }

    @GetMapping(value = "/update-password")
    private String updatePasswordForm(
            Model model){
        return "update-password";
    }

    @PostMapping(value = "/update-password")
    private String changePasswordSubmit(
            @RequestParam(value = "passwordOld") String passwordOld,
            @RequestParam(value = "passwordNew") String passwordNew,
            @RequestParam(value = "passwordNew2") String passwordNew2,
            HttpServletRequest request,
            RedirectAttributes attributes,
            Model model
    ) {
        Principal principal = request.getUserPrincipal();
        UserModel user = userService.findUserByNama(principal.getName());
        if(!passwordNew.equals(passwordNew2)){
            model.addAttribute("notif", "Password baru tidak match, mohon input ulang");
            return "update-password";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(passwordOld, user.getPassword())){
            String a = userService.changePassword(user, passwordNew);
            attributes.addFlashAttribute("notif", a);
            return "redirect:/user/update-password";
        }
        else{
            model.addAttribute("notif", "Password lama salah, mohon input ulang");
            return "update-password";
        }
    }

    @GetMapping("/viewall")
    public String viewAllUser(
            Model model
    ){
        return "viewall-user";
    }





}
