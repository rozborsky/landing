package ua.rozborsky.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ua.rozborsky.classes.CvManager;
import ua.rozborsky.classes.DAOPostgress;
import ua.rozborsky.classes.EmployeeImpl;
import ua.rozborsky.classes.SendLetter;

import javax.validation.Valid;
import java.io.File;


/**
 * Created by roman on 18.12.2016.
 */

@Controller
public class MainController {

    @Autowired
    CvManager cvManager;

    @Autowired
    private SendLetter sendLetter;

    @Autowired
    private DAOPostgress dao;

    private String dirPath = getPathToCv();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("employee", new EmployeeImpl());

        return "registration";
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.POST)
    public String confirmation(@RequestParam("cv") MultipartFile file,
                               @Valid @ModelAttribute("employee") EmployeeImpl employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        dao.DAOPostgresss("jdbc:postgresql://localhost:5439/web", "postgres", "postgres");
        dao.addEmployee(employee.getName(), employee.getSecondName(), employee.geteMail(),
                employee.getRemarks(), file.getOriginalFilename());


        cvManager.saveImage(file, dirPath);

        sendLetter.setParameters("rozborsky.test.page@gmail.com", "spongebobsquarepants", "roman.rozborsky@gmail.com",
                employee.getSecondName() + "_" + employee.getName());
        sendLetter.send(employee.getRemarks(), file.getOriginalFilename(), dirPath);

        return "confirmation";
    }

    public String getPathToCv() {
        File file = new File("");

        return file.getAbsolutePath() + "\\cv\\";
    }
}
