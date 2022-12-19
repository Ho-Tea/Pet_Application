package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentFeedRecommend6Binding;

public class FeedRecommend6Fragment extends Fragment {

    private FragmentFeedRecommend6Binding binding;

    public FeedRecommend6Fragment() {
        super(R.layout.fragment_feed_recommend6);
    }

    public static FeedRecommend6Fragment newInstance() {
        return new FeedRecommend6Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFeedRecommend6Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView feed_recommend6_back = view.findViewById(R.id.feed_recommend6_back);

        feed_recommend6_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, FeedRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
