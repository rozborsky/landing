package ua.rozborsky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.rozborsky.classes.EmployeeImpl;
import ua.rozborsky.interfaces.Employee;

import javax.validation.Valid;

/**
 * Created by roman on 18.12.2016.
 */

@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("employee", new EmployeeImpl());
        return "registration";
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
    public String confirmation(@Valid @ModelAttribute("employee") EmployeeImpl employee, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            return "registration";
        }
        return "confirmation";
    }
}
