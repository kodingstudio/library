package com.haris.proscanner.presentationutil.ui.customutil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.haris.proscanner.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentViewHolder;
import androidx.viewpager2.widget.ViewPager2;

public class LockableViewPager2 extends ConstraintLayout {

   private ViewPager2 viewPager2;
   private boolean swipeLocked;

   public LockableViewPager2(@NonNull Context context) {
      super(context);
      initialize(context, null);
   }

   public LockableViewPager2(@NonNull Context context, @Nullable AttributeSet attrs) {
      super(context, attrs);
      initialize(context, attrs);
   }

   public LockableViewPager2(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      initialize(context, attrs);
   }

   private void initialize(Context context, AttributeSet attrs) {
      inflate(context, R.layout.lockable_view_pager_layout, this);
      viewPager2 = findViewById(R.id.view_pager);
      viewPager2.setUserInputEnabled(false);
   }

   public boolean getSwipeLocked() {
      return swipeLocked;
   }

   public void setSwipeLocked(boolean swipeLocked) {
      this.swipeLocked = swipeLocked;
   }

   @Override
   public boolean onInterceptTouchEvent(MotionEvent ev) {
      return !swipeLocked && super.onInterceptTouchEvent(ev);
   }

   @Override
   public boolean onTouchEvent(MotionEvent event) {
      return !swipeLocked && super.onTouchEvent(event);
   }



   public void setAdapter(RecyclerView.Adapter<FragmentViewHolder> adapter) {
      viewPager2.setAdapter(adapter);
   }

   public void setCurrentItem(int item) {
      viewPager2.setCurrentItem(item);
   }

   public void setOffScreenPageLimit(int limit){
      viewPager2.setOffscreenPageLimit(limit);
   }

   public ViewPager2 getViewPager2(){
      return viewPager2;
   }

   public void registerListener(ViewPager2.OnPageChangeCallback onPageChangeCallback){
      viewPager2.registerOnPageChangeCallback(onPageChangeCallback);
   }

   public void unregisterListener(ViewPager2.OnPageChangeCallback onPageChangeCallback){
      viewPager2.unregisterOnPageChangeCallback(onPageChangeCallback);
   }
}
