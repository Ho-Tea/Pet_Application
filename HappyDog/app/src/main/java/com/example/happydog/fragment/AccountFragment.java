package com.example.happydog.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.happydog.R;
import com.example.happydog.databinding.FragmentAccountBinding;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private PieChart pieChart;
    private int[] colorArray = new int[] {Color.LTGRAY, Color.DKGRAY, Color.GRAY};
    private FragmentAccountBinding binding;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */

    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        pieChart = view.findViewById(R.id.piechart);
        PieDataSet pieDataSet = new PieDataSet(data1(),"");
        pieDataSet.setColors(colorArray);
        PieData pieData = new PieData(pieDataSet);
        pieChart.setDrawEntryLabels(true);
        pieChart.setUsePercentValues(true);
        pieData.setValueTextSize(20);
        pieChart.setHoleRadius(0);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.invalidate();

        ImageView feedRecommendButton = view.findViewById(R.id.feed_recommend_button);
        ImageView snackRecommendButton = view.findViewById(R.id.snack_recommend_button);
        ImageView insuranceRecommendButton = view.findViewById(R.id.insurance_recommend_button);
        ImageView setAccountButton = view.findViewById(R.id.btn_fix);

        feedRecommendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, FeedRecommendFragment.class, null).commit();
            }
        });

        snackRecommendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, SnackRecommendFragment.class, null).commit();
            }
        });

        insuranceRecommendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, InsuranceRecommendFragment.class, null).commit();
            }
        });

        setAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentManager fragmentManager = getParentFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragments_frame, SetAccountFragment.class, null).commit();
            }
        });


        return view;
    }

    private ArrayList<PieEntry> data1(){
        ArrayList<PieEntry> datavalue = new ArrayList<>();

        datavalue.add(new PieEntry(30,"간식"));
        datavalue.add(new PieEntry(50,"사료"));
        datavalue.add(new PieEntry(20,"보험"));
        return datavalue;
    }

}