package com.redrumming.thecreaturehub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ME on 8/9/2015.
 */
public class RecyclerOnItemClickListener implements RecyclerView.OnItemTouchListener{

    private OnItemClickListener listener;
    private GestureDetector gestureDetector;

    public interface OnItemClickListener{

        void onItemClick(View view, int position);
    }

    public RecyclerOnItemClickListener(Context context, OnItemClickListener listener){
        this.listener = listener;
        gestureDetector = new GestureDetector(context, gestureListener);
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

        View childView = rv.findChildViewUnder(e.getX(), e.getY());

        if(childView != null && listener != null && gestureDetector.onTouchEvent(e)){

            listener.onItemClick(childView, rv.getChildLayoutPosition(childView));
            return true;
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    private GestureDetector.SimpleOnGestureListener gestureListener = new GestureDetector.SimpleOnGestureListener(){

        @Override
        public boolean onSingleTapUp(MotionEvent e) {

            return true;
        }
    };
}
