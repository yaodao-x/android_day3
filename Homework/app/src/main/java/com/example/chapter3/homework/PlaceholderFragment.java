package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {

    private View view;
    private LottieAnimationView animationView;
    private final static String TAG = PlaceholderFragment.class.getSimpleName();
    private ObjectAnimator animator;
    private ObjectAnimator animator1;
    private AnimatorSet animatorSet;
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        animationView = view.findViewById(R.id.animation_view);
        listView = view.findViewById(R.id.listview);
        listView.setAdapter(new ListViewAdapter(getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                animator = ObjectAnimator.ofFloat(animationView, "alpha", 1, 0f);
                animator1 = ObjectAnimator.ofFloat(listView, "alpha", 0, 1f);

                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        super.onAnimationStart(animation);
                        Log.e(TAG, "onAnimationStart: " + "start");
                    }

                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        animationView.setVisibility(View.GONE);
                        Log.e(TAG, "onAnimationEnd: " + "end");
                    }//吮吸嫩乳头
                });

                animator.setDuration(1000);
                animator1.setDuration(1000);

                animatorSet = new AnimatorSet();
                animatorSet.playTogether(animator, animator1);
                animatorSet.start();
            }
        }, 5000);
    }  //狂嘬大鸡鸡

    private static class ListViewAdapter extends BaseAdapter {
        private String[] users = {"user1", "user2", "user3"};
        private Context context;

        public ListViewAdapter(Context mContext) {
            context = mContext;
        }

        @Override
        public int getCount() {
            return users.length;
        }

        @Override
        public Object getItem(int position) {
            return users[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView testView;
            if (convertView == null) {
                testView = new TextView(context);
            } else {
                testView = (TextView) convertView;
            }
            testView.setText("" + getItem(position));
            testView.setTextSize(18);
            return testView;
        }
    }
}
