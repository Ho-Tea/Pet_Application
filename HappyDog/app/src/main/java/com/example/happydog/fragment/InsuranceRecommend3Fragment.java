package com.example.happydog.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentInsuranceRecommend3Binding;

public class InsuranceRecommend3Fragment extends Fragment {

    private FragmentInsuranceRecommend3Binding binding;

    public InsuranceRecommend3Fragment() {
        super(R.layout.fragment_insurance_recommend3);
    }

    public static InsuranceRecommend3Fragment newInstance() {
        return new InsuranceRecommend3Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInsuranceRecommend3Binding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView insurance_recommend3_back = view.findViewById(R.id.insurance_recommend3_back);

        insurance_recommend3_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, InsuranceRecommendFragment.class, null).commit();
            }
        });

        return view;
    }
}
