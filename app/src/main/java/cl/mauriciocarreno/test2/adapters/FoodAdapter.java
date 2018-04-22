package cl.mauriciocarreno.test2.adapters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import cl.mauriciocarreno.test2.R;
import cl.mauriciocarreno.test2.data.Queries;
import cl.mauriciocarreno.test2.models.Food;


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private List<Food> foods = new Queries().foods();
    private FoodClickListener listener;

    public FoodAdapter(FoodClickListener listener) {
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Food food = foods.get(position);


        Log.d("CTM", food.getName() + " " + food.getCategory() + " " + food.getQuantity());

        holder.nameFoodTv.setText(food.getName());
        holder.categoryFoodTv.setText(food.getCategory());
        holder.quantityFoodTv.setText(String.valueOf(food.getQuantity()));
        holder.foodCb.setChecked(food.isState());

        holder.foodCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int auxPosition = holder.getAdapterPosition();
                            Food auxFood = foods.get(auxPosition);
                            auxFood.setState(true);
                            auxFood.save();

                            foods.remove(auxFood);
                            notifyItemRemoved(auxPosition);
                        }
                    }, 400);
                }
            }
        });

        holder.nameFoodTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Food auxFood = foods.get(holder.getAdapterPosition());
                listener.clickedID(auxFood.getId());
            }
        });

    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    public void update(Food food) {
        foods.add(food);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox foodCb;
        private TextView nameFoodTv;
        private TextView categoryFoodTv;
        private TextView quantityFoodTv;

        public ViewHolder(View itemView) {
            super(itemView);

            foodCb = itemView.findViewById(R.id.foodListCb);
            nameFoodTv = itemView.findViewById(R.id.foodNameListTv);
            categoryFoodTv = itemView.findViewById(R.id.foodCategoryListTv);
            quantityFoodTv = itemView.findViewById(R.id.foodQuantityTv);
        }
    }
}
