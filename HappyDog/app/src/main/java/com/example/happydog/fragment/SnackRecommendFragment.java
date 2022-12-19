package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentSnackRecommendBinding;


public class SnackRecommendFragment extends Fragment {

    private FragmentSnackRecommendBinding binding;

    public SnackRecommendFragment() {
        super(R.layout.fragment_snack_recommend);
    }

    public static SnackRecommendFragment newInstance() {
        return new SnackRecommendFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSnackRecommendBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        ImageView snack_recommend1 = view.findViewById(R.id.snack_recommend1);
        ImageView snack_recommend2 = view.findViewById(R.id.snack_recommend2);
        ImageView snack_recommend3 = view.findViewById(R.id.snack_recommend3);
        ImageView snack_recommend4 = view.findViewById(R.id.snack_recommend4);
        ImageView snack_recommend5 = view.findViewById(R.id.snack_recommend5);
        ImageView snack_recommend6 = view.findViewById(R.id.snack_recommend6);

        return view;
    }
}
