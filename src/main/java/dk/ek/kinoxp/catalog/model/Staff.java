package dk.ek.kinoxp.catalog.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

@Entity
@Table(name = "staff")

public class Staff
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer staff_id;
    protected String name;
    protected String role;

    public Staff(){}

    public Staff(Integer staff_id, String name, String role)
    {
        this.staff_id = staff_id;
        this.name = name;
        this.role = role;
    }

    public Integer getStaff_id() {return staff_id;}
    public void setStaff_id(Integer staff_id) {}

    public String getName() {return name;}
    public void setName(String name) {}

    public String getRole() {return role;}
    public void setRole(String role) {}


}
