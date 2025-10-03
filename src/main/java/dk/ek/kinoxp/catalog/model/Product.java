package dk.ek.kinoxp.catalog.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

@Entity
@Table(name = "product")

public class Product
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer product_id;
    protected String name;
    protected int price;


    public Product(){}

    public Product(Integer product_id, String name, int price)
    {
        this.product_id = product_id;
        this.name = name;
        this.price = price;
    }

    public Integer getProduct_id() {return product_id;}
    public void setProduct_id(int id) {this.product_id = id;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getPrice() {return price;}
    public void setPrice(int price) {this.price = price;}

}
