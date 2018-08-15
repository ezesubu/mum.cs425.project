package mum.swe.CRMSSpringApp.model;

import javax.persistence.*;


@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String imageUrl;
    private String color;
    private Long num_of_seat;

    @ManyToOne
    private Category category;
    private String status; //(Ahmed we have it as string not number) vvalues are 0 , 1 , 2 ( Metting about it 04 08 2018)

    public Car() {
    }

    public Car(String brand, String model, String color, Long num_of_seat, String imageUrl, Category category, String status) {

        this.brand = brand;
        this.model = model;
        this.color = color;
        this.num_of_seat = num_of_seat;
        this.category = category;
        this.status = status;
        this.imageUrl=imageUrl;
        
    }

    public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

    public String getName() {
        return String.format("%s/%s", this.brand, this.model);
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
        return  this.status;
    }
    public String translateStatus() {

        if (this.status==null)
        {
            return null;
        }
        switch (this.status)
        {
                case "0" :
                    return "Available";
                case "1" :
                    return "Reserved";
                case "2" :
                    return "Deleted";


        }
        return "";

    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", num_of_seat=" + num_of_seat +
                ", category=" + category +
                ", status='" + status + '\'' +
                '}';
    }
}
