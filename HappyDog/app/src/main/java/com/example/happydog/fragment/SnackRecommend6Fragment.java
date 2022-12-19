package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentSnackRecommend6Binding;

public class SnackRecommend6Fragment extends Fragment {

    private FragmentSnackRecommend6Binding binding;

    public SnackRecommend6Fragment() {
        super(R.layout.fragment_snack_recommend6);
    }

    public static SnackRecommend6Fragment newInstance() {
        return new SnackRecommend6Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSnackRecommend6Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView snack_recommend6_back = view.findViewById(R.id.snack_recommend6_back);

        snack_recommend6_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, SnackRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
