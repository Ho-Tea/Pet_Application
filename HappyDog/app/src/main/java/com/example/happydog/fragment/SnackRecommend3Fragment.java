package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentSnackRecommend3Binding;

public class SnackRecommend3Fragment extends Fragment {

    private FragmentSnackRecommend3Binding binding;

    public SnackRecommend3Fragment() {
        super(R.layout.fragment_snack_recommend3);
    }

    public static SnackRecommend3Fragment newInstance() {
        return new SnackRecommend3Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSnackRecommend3Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView snack_recommend3_back = view.findViewById(R.id.snack_recommend3_back);

        snack_recommend3_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, SnackRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
