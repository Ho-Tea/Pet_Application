package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentFeedRecommend4Binding;

public class FeedRecommend4Fragment extends Fragment {

    private FragmentFeedRecommend4Binding binding;

    public FeedRecommend4Fragment() {
        super(R.layout.fragment_feed_recommend4);
    }

    public static FeedRecommend4Fragment newInstance() {
        return new FeedRecommend4Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFeedRecommend4Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView feed_recommend4_back = view.findViewById(R.id.feed_recommend4_back);

        feed_recommend4_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, FeedRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
