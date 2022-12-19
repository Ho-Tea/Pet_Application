package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentFeedRecommend3Binding;

public class FeedRecommend3Fragment extends Fragment {

    private FragmentFeedRecommend3Binding binding;

    public FeedRecommend3Fragment() {
        super(R.layout.fragment_feed_recommend3);
    }

    public static FeedRecommend3Fragment newInstance() {
        return new FeedRecommend3Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentFeedRecommend3Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView feed_recommend3_back = view.findViewById(R.id.feed_recommend3_back);

        feed_recommend3_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, FeedRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
