package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentFeedRecommend5Binding;

public class FeedRecommend5Fragment extends Fragment {

    private FragmentFeedRecommend5Binding binding;

    public FeedRecommend5Fragment() {
        super(R.layout.fragment_feed_recommend5);
    }

    public static FeedRecommend5Fragment newInstance() {
        return new FeedRecommend5Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFeedRecommend5Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView feed_recommend5_back = view.findViewById(R.id.feed_recommend5_back);

        feed_recommend5_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, FeedRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
