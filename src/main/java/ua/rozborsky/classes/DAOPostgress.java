package ua.rozborsky.classes;

import org.springframework.stereotype.Component;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import ua.rozborsky.exceptions.LandingException;
import ua.rozborsky.interfaces.DAO;

/**
 * Created by roman on 19.12.2016.
 */

@Component
public class DAOPostgress implements DAO{

    private String host;
    private String user;
    private String password;

    public void DAOPostgresss(String host, String user, String password) {
        this.host = host;
        this.user = user;
        this.password = password;
    }

    @Override
    public void addEmployee(String name, String secondName, String eMail, String remarks, String cv) throws LandingException{
        Sql2o sql2o = new Sql2o(host, user, password);
        System.out.println(sql2o.toString());
        String sql =
                "INSERT INTO landingpage(name, secondname, email, remarks, cv) " +
                        "VALUES (:name, :secondname, :email, :remarks, :cv)";

        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                .addParameter("name", name)
                .addParameter("secondname", secondName)
                .addParameter("email", eMail)
                .addParameter("remarks", remarks)
                .addParameter("cv", cv)
                .executeUpdate();
        }catch (Sql2oException e) {
            throw new LandingException();
        }
    }
}
