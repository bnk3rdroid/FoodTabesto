package yb.foodtabesto.screens.details;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

    @BindView(R.id.details_loader)
    ProgressBar mLoader;

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
            displayDetails();
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

    /**
     * Set texts for the name and the price.
     * Download an image for the header.
     */
    private void displayDetails() {
        mName.setText(mFood.getName());
        mPrice.setText(mFood.getPrice());
        Glide.with(this).load(mFood.getImageThumbnailUrl()).into(mImage);
    }

    /**
     * Notify the user about the order using a toast.
     */
    private void notifyAboutOrder(boolean success) {
        //For demonstration purpose the result will always be true.
        String msg = getString(success ? R.string.thanks_order : R.string.order_failed);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void showLoader() {
        mLoader.setVisibility(View.VISIBLE);
    }

    private void hideLoader() {
        mLoader.setVisibility(View.GONE);
    }

    /**
     * Click on the Order button.
     * # Send POST request on FoodApi /order/{id_product} (fake endpoint).
     * # Catch 404 Http status and send a default Order object in the pipe.
     * # Notify the user upon result with a Toast.
     */
    @OnClick(R.id.product_order)
    void onButtonOrderClicked() {
        showLoader();
        mOrderDisposable = mOrderObservable
                //Handling http 404 exception because our endpoint is fake.
                .onErrorResumeNext(throwable -> {
                    if (throwable instanceof HttpException &&
                            ((HttpException) throwable).code() == 404) {
                        String log = DetailsActivity.this.getString(R.string.http_404);
                        Log.e("Http error", log, throwable);
                    }
                    return Observable.just(new Order());
                })
                .subscribe(order -> {
                    notifyAboutOrder(order.isSuccessful());
                    hideLoader();
                });
    }
}
