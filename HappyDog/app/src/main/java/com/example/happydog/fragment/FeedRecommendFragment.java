package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentFeedRecommendBinding;

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
        ImageView feed_recommend_back = view.findViewById(R.id.feed_recommend_back);

        feed_recommend1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, FeedRecommend1Fragment.class, null).commit();
            }
        });

        feed_recommend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, FeedRecommend2Fragment.class, null).commit();
            }
        });

        feed_recommend3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, FeedRecommend3Fragment.class, null).commit();
            }
        });

        feed_recommend4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, FeedRecommend4Fragment.class, null).commit();
            }
        });

        feed_recommend5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, FeedRecommend5Fragment.class, null).commit();
            }
        });

        feed_recommend6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, FeedRecommend6Fragment.class, null).commit();
            }
        });

        feed_recommend_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, AccountFragment.class, null).commit();
            }
        });

        return view;
    }
}
