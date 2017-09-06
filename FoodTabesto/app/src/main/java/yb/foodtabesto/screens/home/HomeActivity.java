package yb.foodtabesto.screens.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import yb.foodtabesto.R;
import yb.foodtabesto.app.App;
import yb.foodtabesto.data.Food;
import yb.foodtabesto.screens.home.di.DaggerHomeComponent;

/**
 * Home Activity. Display a list of Food products.
 */
public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.rv_food)
    RecyclerView mRvFood;

    @BindView(R.id.home_loader)
    ProgressBar mLoader;

    @Inject
    FoodAdapter mAdapter;

    @Inject
    Observable<List<Food>> mFoodDataObservable;

    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInjection();
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        initRecycler();
        displayFoodData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDisposable.dispose();
    }

    private void initInjection() {
        DaggerHomeComponent.builder()
                .appComponent(App.getAppComponent())
                .build().inject(this);
    }

    private void initRecycler() {
        //Divider
        int orientation = DividerItemDecoration.VERTICAL;
        RecyclerView.ItemDecoration decor = new DividerItemDecoration(this, orientation);
        mRvFood.addItemDecoration(decor);
        //Adapter
        mRvFood.setAdapter(mAdapter);
    }

    private void displayFoodData() {
        showLoader();
        mDisposable = mFoodDataObservable.subscribe(foodData-> {
            mAdapter.updateData(foodData);
            hideLoader();
        });
    }

    private void showDetails(Food food) {
        // TODO: 06/09/2017 open view detail
    }

    private void showLoader() {
        mLoader.setVisibility(View.VISIBLE);
    }

    private void hideLoader() {
        mLoader.setVisibility(View.GONE);
    }
}
