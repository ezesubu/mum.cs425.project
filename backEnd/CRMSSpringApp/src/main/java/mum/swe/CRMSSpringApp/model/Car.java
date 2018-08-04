package mum.swe.CRMSSpringApp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String brand;
	private String model;
	private String color;
	private Long num_of_seat;
	
	@ManyToOne
	private Category category;
	private String status;

    public Car() {
    }

    public Car(String brand, String model, String color, Long num_of_seat, Category category, String status) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.num_of_seat = num_of_seat;
        this.category = category;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Long getNum_of_seat() {
        return num_of_seat;
    }

    public void setNum_of_seat(Long num_of_seat) {
        this.num_of_seat = num_of_seat;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
