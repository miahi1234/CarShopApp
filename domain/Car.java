package domain;

import java.io.Serializable;

public class Car extends Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String maker;
    private String model;

    public Car() {
        super(0);
        this.maker = "";
        this.model = "";
    }
    
    public Car(int id, String maker, String model) {
        super(id);
        this.maker = maker;
        this.model = model;
    }

    public String getMaker() {
        return maker;
    }

    public String getModel() {
        return model;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car ID: " + String.valueOf(super.getId()) + ", Maker: " + this.maker + ", Model: " + this.model;
    }
}
