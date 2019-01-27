package com.example.deliciousfood;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class FoodAPI {

    public static List<Food> getDemoFood(Context context){
        List<Food> foods = new ArrayList<>();
        foods.add(new Food("提拉米苏",R.drawable.tilamisu,80,Food.DESSERT_FOOD,false,4.5f));
        foods.add(new Food("舒芙蕾",R.drawable.souffle,65,Food.DESSERT_FOOD,false,4f));
        foods.add(new Food("欧培拉",R.drawable.opera,48,Food.DESSERT_FOOD,false,3.5f));
        foods.add(new Food("汉堡包",R.drawable.hamberger,15,Food.FAST_FOOD,false,4.0f));
        foods.add(new Food("三明治",R.drawable.sanmingzhi,8,Food.FAST_FOOD,false,4.5f));
        foods.add(new Food("麻辣鸡块",R.drawable.malajikuai,18,Food.CHINESE_FOOD,true,4.0f));
        foods.add(new Food("宫保鸡丁",R.drawable.gongbaojiding,20,Food.CHINESE_FOOD,true,5.0f));
        foods.add(new Food("鱼香肉丝",R.drawable.yuxiangrousi,24,Food.CHINESE_FOOD,false,4.0f));
        foods.add(new Food("水煮肉片",R.drawable.shuizhurou,32,Food.CHINESE_FOOD,true,4.5f));
        foods.add(new Food("红烧肉",R.drawable.hongshaorou,38,Food.CHINESE_FOOD,false,5.0f));

        return foods;
    }



}
