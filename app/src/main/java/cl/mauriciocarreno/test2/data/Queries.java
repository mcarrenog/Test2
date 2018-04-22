package cl.mauriciocarreno.test2.data;

import java.util.ArrayList;
import java.util.List;

import cl.mauriciocarreno.test2.models.Food;

public class Queries {

    public List<Food> foods() {
        List<Food> foods = new ArrayList<>();
        List<Food> foodList = Food.find(Food.class, "state = 0");

        if(foodList != null && foodList.size() > 0)
        {
            foods.addAll(foodList);
        }

        return foods;
    }
}
