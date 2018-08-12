package com.tbuonomo.dotsindicatorsample;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
    wormDotsIndicator.setCurrentPosition(adapter.getCount() - 1, 0);
    dotsIndicator.setCurrentPosition(adapter.getCount() - 1, 0);
    springDotsIndicator.setCurrentPosition(adapter.getCount() - 1, 0);

    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int i1) {
//        wormDotsIndicator.setCurrentPosition(position, positionOffset);
//        dotsIndicator.setCurrentPosition(position, positionOffset);
//        springDotsIndicator.setCurrentPosition(position, positionOffset);
//        Log.d("offset: ", String.valueOf(positionOffset));
      }

      @Override
      public void onPageSelected(final int position) {
        Handler handler = new Handler();
        for (float i = 0.1f; i < 0.9f; i += 0.3f) {
          final float finalI = i;
          handler.postDelayed(new Runnable() {
            @Override
            public void run() {
              wormDotsIndicator.setCurrentPosition(position, finalI);
            }
          }, 100);
        }
        handler.postDelayed(new Runnable() {
          @Override
          public void run() {
            wormDotsIndicator.setCurrentPosition(position, 0);
          }
        }, 100);
      }

      @Override
      public void onPageScrollStateChanged(int i) {

      }
    });
  }
}
