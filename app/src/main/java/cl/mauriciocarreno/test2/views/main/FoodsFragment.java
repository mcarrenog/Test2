package cl.mauriciocarreno.test2.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.mauriciocarreno.test2.R;
import cl.mauriciocarreno.test2.adapters.FoodAdapter;
import cl.mauriciocarreno.test2.adapters.FoodClickListener;
import cl.mauriciocarreno.test2.models.Food;
import cl.mauriciocarreno.test2.views.details.DetailsActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class FoodsFragment extends Fragment implements FoodClickListener {

    private FoodAdapter adapter;

    public static final String FOOD_ID = "cl.mauriciocarreno.test2.views.main.KEY.FOOD_ID";

    public FoodsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.foodRv);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FoodAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    public void updateList(Food food) {
        adapter.update(food);
    }

    public void refreshList()
    {
        adapter.refresh();
    }

    @Override
    public void clickedID(long id) {
        Intent detailsIntent = new Intent(getActivity(), DetailsActivity.class);
        detailsIntent.putExtra(FOOD_ID, id);
        startActivity(detailsIntent);
    }
}
