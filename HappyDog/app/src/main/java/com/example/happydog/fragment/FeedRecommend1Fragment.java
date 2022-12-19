package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentFeedRecommend1Binding;

public class FeedRecommend1Fragment extends Fragment {

    private FragmentFeedRecommend1Binding binding;

    public FeedRecommend1Fragment() {
        super(R.layout.fragment_feed_recommend1);
    }

    public static FeedRecommend1Fragment newInstance() {
        return new FeedRecommend1Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFeedRecommend1Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView feed_recommend1_back = view.findViewById(R.id.feed_recommend1_back);

        feed_recommend1_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, FeedRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
