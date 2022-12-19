package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentSnackRecommend2Binding;

public class SnackRecommend2Fragment extends Fragment {

    private FragmentSnackRecommend2Binding binding;

    public SnackRecommend2Fragment() {
        super(R.layout.fragment_snack_recommend2);
    }

    public static SnackRecommend2Fragment newInstance() {
        return new SnackRecommend2Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSnackRecommend2Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView snack_recommend2_back = view.findViewById(R.id.snack_recommend2_back);

        snack_recommend2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, SnackRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
