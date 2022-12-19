package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentInsuranceRecommend1Binding;

public class InsuranceRecommend1Fragment extends Fragment {

    private FragmentInsuranceRecommend1Binding binding;

    public InsuranceRecommend1Fragment() {
        super(R.layout.fragment_insurance_recommend1);
    }

    public static InsuranceRecommend1Fragment newInstance() {
        return new InsuranceRecommend1Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInsuranceRecommend1Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView insurance_recommend1_back = view.findViewById(R.id.insurance_recommend1_back);

        insurance_recommend1_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, InsuranceRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
