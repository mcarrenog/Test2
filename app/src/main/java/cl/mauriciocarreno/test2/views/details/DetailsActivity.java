package cl.mauriciocarreno.test2.views.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cl.mauriciocarreno.test2.R;
import cl.mauriciocarreno.test2.models.Food;

public class DetailsActivity extends AppCompatActivity {

    public static final String FOOD_ID = "cl.mauriciocarreno.test2.views.main.KEY.FOOD_ID";
    private Food food;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        long foodID = getIntent().getIntExtra("FOOD_ID", -1);

    }
}
