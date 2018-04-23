package cl.mauriciocarreno.test2.views.main;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.List;

import cl.mauriciocarreno.test2.R;
import cl.mauriciocarreno.test2.models.Food;

public class MainActivity extends AppCompatActivity {

    private FoodsFragment foodsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        foodsFragment = (FoodsFragment) getSupportFragmentManager().findFragmentById(R.id.foodFragment);

        final List<String> category = new ArrayList<>();
        category.add("Seleccione una opción");
        category.add("Carne");
        category.add("Lácteo");
        category.add("Líquido");
        category.add("Verdura");
        category.add("Otro");

        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.spinner_item_custom, category);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_food);

                final Spinner categorySpinner = dialog.findViewById(R.id.categorySpinner);
                categorySpinner.setAdapter(arrayAdapter);

                ImageButton imageButton = dialog.findViewById(R.id.saveBtn);
                imageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText nameFoodEt = dialog.findViewById(R.id.nameFoodEt);
                        EditText quantityFoodEt = dialog.findViewById(R.id.quantityFoodEt);

                        String nameFood = nameFoodEt.getText().toString();
                        String quantityFood = quantityFoodEt.getText().toString();

                        if (nameFood.length() == 0 || quantityFood.length() == 0 || categorySpinner.getSelectedItemPosition() == 0) {
                            TastyToast.makeText(MainActivity.this, "Debes completar todos los campos!", TastyToast.LENGTH_SHORT, TastyToast.WARNING);
                        } else {

                            Food food = new Food();
                            food.setName(nameFood);
                            food.setQuantity(Integer.parseInt(quantityFood));
                            food.setCategory(categorySpinner.getSelectedItem().toString());
                            food.setState(false);
                            food.save();

                            foodsFragment.updateList(food);

                            dialog.dismiss();

                            TastyToast.makeText(MainActivity.this, "Se ha agregado correctamente a la lista", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
                        }

                    }

                });

                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        foodsFragment.refreshList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
