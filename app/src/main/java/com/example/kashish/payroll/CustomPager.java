package com.example.kashish.payroll;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class CustomPager extends PagerAdapter {
    Context context;
    Integer[] imagesArray;


    public CustomPager(Context context ,Integer[] imagesArray){

        this.context = context;
        this.imagesArray = imagesArray;

    }
    public Object instantiateItem(ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowLayout = inflater.inflate(R.layout.row_layout,container,false);

        ImageView imageView = (ImageView) rowLayout.findViewById(R.id.imageView);
        imageView.setImageResource(imagesArray[position]);

        imageView.getLayoutParams().height=750;
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);


        ViewPager cp=(ViewPager) container;
        cp.addView(rowLayout,0);

        return rowLayout;
    }

    @Override
    public int getCount() {
        return imagesArray.length;
    }

    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager cp=(ViewPager) container;
        View view = (View) object;
        cp.removeView(view);
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

            return  view == ((View)object);

    }
}
