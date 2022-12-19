package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentFeedRecommendBinding;

import java.util.ArrayList;

public class FeedRecommendFragment extends Fragment {

    private FragmentFeedRecommendBinding binding;

    public FeedRecommendFragment() {
        super(R.layout.fragment_feed_recommend);
    }

    public static FeedRecommendFragment newInstance() {

        return new FeedRecommendFragment();
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState ) {
//        super.onCreate(savedInstanceState);
//
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentFeedRecommendBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView feed_recommend1 = view.findViewById(R.id.feed_recommend1);
        ImageView feed_recommend2 = view.findViewById(R.id.feed_recommend2);
        ImageView feed_recommend3 = view.findViewById(R.id.feed_recommend3);
        ImageView feed_recommend4 = view.findViewById(R.id.feed_recommend4);
        ImageView feed_recommend5 = view.findViewById(R.id.feed_recommend5);
        ImageView feed_recommend6 = view.findViewById(R.id.feed_recommend6);

        return view;
    }
}
