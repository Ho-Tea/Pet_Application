package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

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

        return view;
    }
}
