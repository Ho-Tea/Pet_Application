package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentFeedRecommend2Binding;

public class FeedRecommend2Fragment extends Fragment {

    private FragmentFeedRecommend2Binding binding;

    public FeedRecommend2Fragment() {
        super(R.layout.fragment_feed_recommend2);
    }

    public static FeedRecommend2Fragment newInstance() {
        return new FeedRecommend2Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFeedRecommend2Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView feed_recommend2_back = view.findViewById(R.id.feed_recommend2_back);

        feed_recommend2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, FeedRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
