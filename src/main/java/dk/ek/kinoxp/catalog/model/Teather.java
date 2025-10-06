package dk.ek.kinoxp.catalog.model;

import jakarta.persistence.*;

@Entity
@Table
public class Teather
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teather_id;
    private String name;

    public Teather()
    {
    }

    public Teather(Long teather_id, String name){
        this.teather_id = teather_id;
        this.name = name;
    }

    public Long getTeather_id()
    {
        return teather_id;
    }

    public void setTeather_id(Long teather_id)
    {
        this.teather_id = teather_id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
