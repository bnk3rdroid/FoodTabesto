package yb.foodtabesto.screens.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;
import yb.foodtabesto.R;
import yb.foodtabesto.app.App;
import yb.foodtabesto.data.Food;
import yb.foodtabesto.data.Order;
import yb.foodtabesto.screens.details.di.DaggerDetailsComponent;
import yb.foodtabesto.screens.details.di.DetailsModule;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.product_name)
    TextView mName;

    @BindView(R.id.product_price)
    TextView mPrice;

    @BindView(R.id.product_image)
    ImageView mImage;

    @Inject
    Observable<Order> mOrderObservable;

    private Food mFood;

    private Disposable mOrderDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String key = getString(R.string.arg_key_food);
        if (intent != null && intent.getParcelableExtra(key) != null) {
            mFood = intent.getParcelableExtra(key);
            initInjection();
            displayDetails(mFood);
        } else throw new IllegalStateException("Food not Found!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mOrderDisposable != null)
            mOrderDisposable.dispose();
    }

    private void initInjection() {
        DaggerDetailsComponent.builder()
                .appComponent(App.getAppComponent())
                .detailsModule(new DetailsModule(mFood.getId()))
                .build().inject(this);
    }

    private void displayDetails(Food food) {
        mName.setText(food.getName());
        mPrice.setText(food.getPrice());
        Glide.with(this).load(food.getImageThumbnailUrl()).into(mImage);
    }

    @OnClick(R.id.product_order)
    void onButtonOrderClicked() {
        mOrderDisposable = mOrderObservable.onErrorResumeNext(throwable -> {
            //To remove http status 404 not handle exception
            if (throwable instanceof HttpException &&
                    ((HttpException) throwable).code() == 404) {
                Log.e("Http error", "Http status 404 received.", throwable);
                notifyOrderSuccess();
            }
            return Observable.empty();
        }).subscribe(order -> notifyOrderSuccess());
    }

    private void notifyOrderSuccess() {
        Toast.makeText(this, getString(R.string.thanks_order), Toast.LENGTH_SHORT).show();
    }

}
