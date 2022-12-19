package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentInsuranceRecommend2Binding;

public class InsuranceRecommend2Fragment extends Fragment {

    private FragmentInsuranceRecommend2Binding binding;

    public InsuranceRecommend2Fragment() {
        super(R.layout.fragment_insurance_recommend2);
    }

    public static InsuranceRecommend2Fragment newInstance() {
        return new InsuranceRecommend2Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInsuranceRecommend2Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView insurance_recommend2_back = view.findViewById(R.id.insurance_recommend2_back);

        insurance_recommend2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, InsuranceRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
