package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentInsuranceRecommendBinding;

public class InsuranceRecommendFragment extends Fragment {

    private FragmentInsuranceRecommendBinding binding;

    public InsuranceRecommendFragment() {
        super(R.layout.fragment_insurance_recommend);
    }

    public static InsuranceRecommendFragment newInstance() {
        return new InsuranceRecommendFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentInsuranceRecommendBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView insurance_recommend1 = view.findViewById(R.id.insurance_recommend1);
        ImageView insurance_recommend2 = view.findViewById(R.id.insurance_recommend2);
        ImageView insurance_recommend3 = view.findViewById(R.id.insurance_recommend3);
        ImageView insurance_recommend_back = view.findViewById(R.id.insurance_recommend_back);

        insurance_recommend1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, InsuranceRecommend1Fragment.class, null).commit();
            }
        });

        insurance_recommend2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, InsuranceRecommend2Fragment.class, null).commit();
            }
        });

        insurance_recommend3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, InsuranceRecommend3Fragment.class, null).commit();
            }
        });

        insurance_recommend_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, AccountFragment.class, null).commit();
            }
        });

        return view;
    }
}
