package com.example.deliciousfood;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView mTvTitle;
    private ImageButton mBtnBack;
    private ImageView mImageFood;

    private TextView mTvName;
    private TextView mTvPrice;
    private RatingBar mRatingBar;

    private Food detailFood;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        detailFood = parseFoodFromBundle(bundle);

        initViews(detailFood);
    }

   public void initViews(Food food){
        mTvTitle = (TextView) findViewById(R.id.tv_title);
        mBtnBack = (ImageButton) findViewById(R.id.imbtn_back);
        mImageFood = (ImageView) findViewById(R.id.imageView);
        mTvName = (TextView) findViewById(R.id.tv_name);
        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mRatingBar = (RatingBar) findViewById(R.id.ratingbar);

        mImageFood.setImageResource(food.getImgResID());
        mTvTitle.setText(food.getName());
        mTvPrice.setText(String.format("价格： %d",food.getPrice()));
        mTvName.setText(String.format("名称： %s",food.getName()));
        mRatingBar.setRating(food.getRating());

    }

    private Food parseFoodFromBundle(Bundle bundle){
        Food food = new Food();
        food.setName(bundle.getString("name"));
        food.setImgResID(bundle.getInt("imgResId"));
        food.setPrice(bundle.getInt("price"));
        food.setRating(bundle.getFloat("rating"));
        food.setSpicy(bundle.getBoolean("isSpicy"));
        return food;
    }


    public void closeActivity(View view) {
        onBackPressed();
    }
}
