package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentSnackRecommend1Binding;

public class SnackRecommend1Fragment extends Fragment {

    private FragmentSnackRecommend1Binding binding;

    public SnackRecommend1Fragment() {
        super(R.layout.fragment_snack_recommend1);
    }

    public static SnackRecommend1Fragment newInstance() {
        return new SnackRecommend1Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSnackRecommend1Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView snack_recommend1_back = view.findViewById(R.id.snack_recommend1_back);

        snack_recommend1_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, SnackRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
