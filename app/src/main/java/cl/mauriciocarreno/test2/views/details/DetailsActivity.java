package cl.mauriciocarreno.test2.views.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.sdsmdg.tastytoast.TastyToast;

import cl.mauriciocarreno.test2.R;
import cl.mauriciocarreno.test2.models.Food;
import cl.mauriciocarreno.test2.views.main.FoodsFragment;

public class DetailsActivity extends AppCompatActivity {

    public static final String FOOD_ID = "cl.mauriciocarreno.test2.views.main.KEY.FOOD_ID";
    private Food food;
    TextView foodNameSelectedTv;
    TextView foodCategorySelectedTv;
    TextView foodQuantitySelectedTv;
    EditText foodQuantitySelectedEt;

    private FoodsFragment foodsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        foodsFragment = (FoodsFragment) getSupportFragmentManager().findFragmentById(R.id.foodFragment);

        long foodID = getIntent().getLongExtra(FOOD_ID, 0);

        if(foodID > 0) {

            food = Food.findById(Food.class, foodID);
            getSupportActionBar().setTitle(food.getName());

            foodNameSelectedTv = findViewById(R.id.foodNameDetailTv);
            foodCategorySelectedTv = findViewById(R.id.categoryFoodDetailTv);
            foodQuantitySelectedTv = findViewById(R.id.quatityFoodDetailTv);
            foodQuantitySelectedEt = findViewById(R.id.newQuantityFoodEt);

            foodNameSelectedTv.setText("Nombre: " + food.getName());
            foodCategorySelectedTv.setText("Categoría: " + food.getCategory());
            foodQuantitySelectedTv.setText("Cantidad Actual: " + String.valueOf(food.getQuantity()));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        String quantity = foodQuantitySelectedEt.getText().toString();

        if (quantity.length() > 0) {
            int foodQuantity = Integer.parseInt(quantity);
            food.setQuantity(foodQuantity);
            food.save();

            TastyToast.makeText(DetailsActivity.this, "Se han guardado los cambios", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
        }
    }
}
