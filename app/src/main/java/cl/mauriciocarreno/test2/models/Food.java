package cl.mauriciocarreno.test2.models;

import com.orm.SugarRecord;

public class Food extends SugarRecord {

    private String name;
    private String category;
    private int quantity;
    private boolean state;

    public Food() {
    }

    public Food(String name, String category, int quantity, boolean state) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
