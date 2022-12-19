package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentSnackRecommend5Binding;

public class SnackRecommend5Fragment extends Fragment {

    private FragmentSnackRecommend5Binding binding;

    public SnackRecommend5Fragment() {
        super(R.layout.fragment_snack_recommend5);
    }

    public static SnackRecommend5Fragment newInstance() {
        return new SnackRecommend5Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSnackRecommend5Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView snack_recommend5_back = view.findViewById(R.id.snack_recommend5_back);

        snack_recommend5_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, SnackRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
