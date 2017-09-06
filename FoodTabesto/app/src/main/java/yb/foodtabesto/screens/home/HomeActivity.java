package yb.foodtabesto.screens.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import yb.foodtabesto.R;
import yb.foodtabesto.data.Food;
import yb.foodtabesto.screens.home.di.DaggerHomeComponent;
import yb.foodtabesto.screens.home.di.HomeModule;

/**
 * Home Activity. Display a list of Food products.
 */
public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.rv_food)
    RecyclerView mRvFood;

    @Inject
    FoodAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInjection();
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initRecycler();
    }

    private void initInjection() {
        DaggerHomeComponent.builder().build().inject(this);
    }

    private void showFood(Food food) {
        // TODO: 06/09/2017 open view detail
    }

    private void initRecycler() {
        //Divider
        int orientation = DividerItemDecoration.HORIZONTAL;
        RecyclerView.ItemDecoration decor = new DividerItemDecoration(this, orientation);
        mRvFood.addItemDecoration(decor);
        //Adapter
        mRvFood.setAdapter(mAdapter);
    }
}
