package yb.foodtabesto.screens.details;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import yb.foodtabesto.R;
import yb.foodtabesto.data.Food;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.product_name)
    TextView mName;

    @BindView(R.id.product_price)
    TextView mPrice;

    @BindView(R.id.product_image)
    ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String key = getString(R.string.arg_key_food);
        if (intent != null && intent.getParcelableExtra(key) != null) {
            Food food = intent.getParcelableExtra(key);
            displayDetails(food);
        } else throw new IllegalStateException("Food not Found!");
    }

    private void displayDetails(Food food) {
        mName.setText(food.getName());
        mPrice.setText(food.getPrice());
        Glide.with(this).load(food.getImageThumbnailUrl()).into(mImage);
    }

}
