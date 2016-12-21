package ua.rozborsky.classes;

import org.hibernate.validator.constraints.Email;
import org.springframework.stereotype.Component;
import ua.rozborsky.interfaces.Employee;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by roman on 18.12.2016.
 */
@Component
public class EmployeeImpl implements Employee{

    @NotNull
    @Size(min = 2, max = 20, message = "name must be from 2 to 20 symbols")
    private String name;

    @NotNull
    @Size(min = 2, max = 20, message = "second name must be from 2 to 20 symbols")
    private String secondName;

    @NotNull
    @Email(message = "not valid e-mail")
    private String eMail;

    private String remarks;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
