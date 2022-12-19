package com.example.happydog.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentSetAccountBinding;

public class SetAccountFragment extends Fragment {

    private FragmentSetAccountBinding binding;

    public SetAccountFragment() {
        super(R.layout.fragment_set_account);
    }

    public static SetAccountFragment newInstance() {
        return new SetAccountFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSetAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        ImageView confirm_account = view.findViewById(R.id.confirm_account);
        ImageView set_account_back = view.findViewById(R.id.set_account_back);

        confirm_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, AccountFragment.class, null).commit();
            }
        });

        set_account_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, AccountFragment.class, null).commit();
            }
        });

        return view;
    }
}
