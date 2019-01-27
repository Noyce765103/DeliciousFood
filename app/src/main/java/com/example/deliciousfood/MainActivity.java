package com.example.deliciousfood;

import android.app.Dialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deliciousfood.Widget.MyDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int STATE_FILTER = 1;
    private static final int STATE_NORMAL = 2;
    private int mState = STATE_NORMAL;

    private ImageButton myImageBtn;
    private CheckBox mCheckBoxChineseFood;
    private CheckBox mCheckBoxFastFood;
    private CheckBox mCheckBoxDessert;

    private RadioGroup mRadioGroup;
    private RadioButton mSpicyYes;
    private RadioButton mSpicyNo;

    private TextView mTvPrice;
    private SeekBar mSeekBarPrice;

    private Button mBtnReset;
    private Button mBtnSearch;

    private Button mBtnPrev;
    private Button mBtnNext;

    private List<Food> mFoodList,filteredFood;
    private int mCurrentPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Food> foods = FoodAPI.getDemoFood(this);

        initViews();
        myImageBtn = (ImageButton) findViewById(R.id.image_btn);
    }

    private void initViews(){

        mCheckBoxChineseFood = (CheckBox) findViewById(R.id.checkBox_chinesefood);
        mCheckBoxFastFood = (CheckBox) findViewById(R.id.checkBox_fastfood);
        mCheckBoxDessert = (CheckBox) findViewById(R.id.checkBox_desert);
        mCheckBoxChineseFood.setChecked(true);
        mCheckBoxFastFood.setChecked(true);
        mCheckBoxDessert.setChecked(true);


        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        mSpicyYes = (RadioButton) findViewById(R.id.radio_yes);
        mSpicyNo = (RadioButton) findViewById(R.id.radio_no);
        mRadioGroup.check(R.id.radio_no);

        mTvPrice = (TextView) findViewById(R.id.tv_price);
        mSeekBarPrice = (SeekBar) findViewById(R.id.seekBar);
        mSeekBarPrice.setProgress(100);
        mTvPrice.setText(""+mSeekBarPrice.getProgress());
        mSeekBarPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mTvPrice.setText(""+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        myImageBtn = (ImageButton) findViewById(R.id.image_btn);
        mFoodList = initData();

        mCurrentPage = 0;
        myImageBtn.setImageResource(mFoodList.get(mCurrentPage).getImgResID());



        mBtnPrev = (Button) findViewById(R.id.btn_prev);
        mBtnNext = (Button) findViewById(R.id .btn_next);

        mBtnPrev.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);


        mBtnReset = (Button) findViewById(R.id.btn_reset);
        mBtnSearch = (Button) findViewById(R.id.btn_search);
        mBtnReset.setOnClickListener(this);
        mBtnSearch.setOnClickListener(this);


    }

    private List<Food> initData(){
        return FoodAPI.getDemoFood(this);
    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_prev:
                showPrevPage();
                break;
            case R.id.btn_next:
                showNextPage();
                break;
            case R.id.btn_reset:
                mState = STATE_NORMAL;
                loadInitialView();
                break;
            case R.id.btn_search:
                mState = STATE_FILTER;
                showFilteredFoods();
                break;
        }
    }

    private void loadInitialView(){
        mCheckBoxDessert.setChecked(true);
        mCheckBoxFastFood.setChecked(true);
        mCheckBoxChineseFood.setChecked(true);
        mRadioGroup.check(R.id.radio_no);
        showPage(0);
    }

    private void showFilteredFoods(){
        filteredFood = filteredFoods();
        boolean isFilteredFoodEmpty = (filteredFood.size() < 1);
        if(isFilteredFoodEmpty){
            Log.d("MainActivity",""+filteredFood.size());
            /*MyDialog myDialog = new MyDialog(MainActivity.this);
            myDialog.setConfirm(new MyDialog.IOnConfirmListener() {
                @Override
                public void onConfirm(MyDialog dialog) {
                    loadInitialView();
                }
            });   自定义dialog跳出未成功，待解决*/

            Toast.makeText(this,"筛选条件有误或未搜到此产品",Toast.LENGTH_LONG).show();
            mCheckBoxDessert.setChecked(true);
            mCheckBoxFastFood.setChecked(true);
            mCheckBoxChineseFood.setChecked(true);
            mRadioGroup.check(R.id.radio_no);
            mSeekBarPrice.setProgress(100);
            mState = STATE_NORMAL;
        }
        else{
            showPage(0);
        }

    }
    private List<Food> filteredFoods(){
        int maxPrice = mSeekBarPrice.getProgress();
        boolean isSpicy = mRadioGroup.getCheckedRadioButtonId() ==R.id.radio_yes;
        List<Integer> selectedFoodTypes = new ArrayList<>();
        if(mCheckBoxChineseFood.isChecked()){
            selectedFoodTypes.add(Food.CHINESE_FOOD);
        }
        if(mCheckBoxFastFood.isChecked()){
            selectedFoodTypes.add(Food.FAST_FOOD);
        }
        if(mCheckBoxDessert.isChecked()){
            selectedFoodTypes.add(Food.DESSERT_FOOD);
        }

        List<Food> results = new ArrayList<>();
        for(Food food:mFoodList){
            if(food.getPrice() < maxPrice &&
                    selectedFoodTypes.contains(food.getType()) &&
                    food.isSpicy() == isSpicy){
                results.add(food);
            }

        }
        return results;
    }

    private List<Food> currentShowingFood(){
        switch (mState){
            case STATE_FILTER:
                return filteredFood;
            case STATE_NORMAL:
                return mFoodList;
            default:
                return mFoodList;
        }
    }

    private void showPrevPage(){
        int prevPage = (mCurrentPage + currentShowingFood().size()- 1) % (currentShowingFood().size());
        showPage(prevPage);

    }

    private void showNextPage(){
        int nextPage = (mCurrentPage + 1) % (currentShowingFood().size());
        showPage(nextPage);

    }


    private void showPage(int index){
        Log.d("MainActivity",""+index);
        Food food = currentShowingFood().get(index);
        myImageBtn.setImageResource(food.getImgResID());
        mCurrentPage = index;
    }

    public void openDetail(View view) {
        Intent intent = new Intent(this,DetailActivity.class);
        Bundle bundle = new Bundle();
        Food food = currentShowingFood().get(mCurrentPage);
        bundle.putString("name",food.getName());
        bundle.putInt("imgResId",food.getImgResID());
        bundle.putInt("price",food.getPrice());
        bundle.putFloat("rating",food.getRating());
        bundle.putBoolean("isSpicy",food.isSpicy());
        intent.putExtras(bundle);
        startActivity(intent);

    }
}

