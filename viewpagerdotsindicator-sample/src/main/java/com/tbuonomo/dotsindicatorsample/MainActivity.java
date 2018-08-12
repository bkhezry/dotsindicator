package com.tbuonomo.dotsindicatorsample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
      WindowManager.LayoutParams.FLAG_FULLSCREEN);
    setContentView(R.layout.activity_main);

    final DotsIndicator dotsIndicator = findViewById(R.id.dots_indicator);
    final SpringDotsIndicator springDotsIndicator = findViewById(R.id.spring_dots_indicator);
    final WormDotsIndicator wormDotsIndicator = findViewById(R.id.worm_dots_indicator);

    ViewPager viewPager = findViewById(R.id.view_pager);
    final DotIndicatorPagerAdapter adapter = new DotIndicatorPagerAdapter();
    viewPager.setAdapter(adapter);
    viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

    dotsIndicator.setCount(adapter.getCount());
    springDotsIndicator.setCount(adapter.getCount());
    wormDotsIndicator.setCount(adapter.getCount());

    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int i1) {
        wormDotsIndicator.setCurrentPosition(position, positionOffset);
        dotsIndicator.setCurrentPosition(position, positionOffset);
        springDotsIndicator.setCurrentPosition(position, positionOffset);
        Log.d("offset: ", String.valueOf(positionOffset));
      }

      @Override
      public void onPageSelected(int position) {
      }

      @Override
      public void onPageScrollStateChanged(int i) {

      }
    });
  }
}
