package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentSnackRecommendBinding;

public class SnackRecommendFragment extends Fragment {

    private FragmentSnackRecommendBinding binding;

    public SnackRecommendFragment() {
        super(R.layout.fragment_snack_recommend);
    }

    public static SnackRecommendFragment newInstance() {
        return new SnackRecommendFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSnackRecommendBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView snack_recommend1 = view.findViewById(R.id.snack_recommend1);
        ImageView snack_recommend2 = view.findViewById(R.id.snack_recommend2);
        ImageView snack_recommend3 = view.findViewById(R.id.snack_recommend3);
        ImageView snack_recommend4 = view.findViewById(R.id.snack_recommend4);
        ImageView snack_recommend5 = view.findViewById(R.id.snack_recommend5);
        ImageView snack_recommend6 = view.findViewById(R.id.snack_recommend6);
        ImageView snack_recommend_back = view.findViewById(R.id.snack_recommend_back);

        snack_recommend1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, SnackRecommend1Fragment.class, null).commit();
            }
        });

        snack_recommend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, SnackRecommend2Fragment.class, null).commit();
            }
        });

        snack_recommend3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, SnackRecommend3Fragment.class, null).commit();
            }
        });

        snack_recommend4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, SnackRecommend4Fragment.class, null).commit();
            }
        });

        snack_recommend5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, SnackRecommend5Fragment.class, null).commit();
            }
        });

        snack_recommend6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, SnackRecommend6Fragment.class, null).commit();
            }
        });

        snack_recommend_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, AccountFragment.class, null).commit();
            }
        });

        return view;
    }
}
