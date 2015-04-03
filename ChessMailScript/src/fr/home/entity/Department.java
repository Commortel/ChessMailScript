package fr.home.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Thibaut
 */
public class Department {
    
    private int number;
    private String name;
    private final List<Club> clubs;

    public Department(int number, String name) {
        this.number = number;
        this.name = name;
        this.clubs = new ArrayList<>();
    }
    
    public List<Department> getDefaultDepartements() {
        List<Department> res = new ArrayList<>();
        res.add(new Department(67, "Bas-Rhin"));
        res.add(new Department(68, "Haut-Rhin"));
        res.add(new Department(88, "Vosges"));
        res.add(new Department(90, "Territoire de Belfort"));
        res.add(new Department(54, "Meurthe-et-Moselle"));
        res.add(new Department(70, "Haute-Saône"));
        return res;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Club> getClubs() {
        return clubs;
    }
    
}
