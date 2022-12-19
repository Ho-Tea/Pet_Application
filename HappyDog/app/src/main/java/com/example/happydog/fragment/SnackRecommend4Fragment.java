package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentSnackRecommend4Binding;

public class SnackRecommend4Fragment extends Fragment {

    private FragmentSnackRecommend4Binding binding;

    public SnackRecommend4Fragment() {
        super(R.layout.fragment_snack_recommend4);
    }

    public static SnackRecommend4Fragment newInstance() {
        return new SnackRecommend4Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentSnackRecommend4Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView snack_recommend4_back = view.findViewById(R.id.snack_recommend4_back);

        snack_recommend4_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, SnackRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
