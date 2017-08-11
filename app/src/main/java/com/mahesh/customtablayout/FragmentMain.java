package com.mahesh.customtablayout;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentMain extends Fragment {

    @BindView(R.id.tvNodataFound)
    TextView tvNodataFound;
    @BindView(R.id.llMainLayout)
    LinearLayout llMainLayout;
    Unbinder unbinder;
    String date = "";
    private int pos = 0;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd \nMMM");


    public static FragmentMain newInstance(int position, String date, String type) {
        FragmentMain fragmentFirst = new FragmentMain();
        Bundle args = new Bundle();
        args.putInt("pos", position);
        args.putString("date", date);
        args.putString("type", type);
        fragmentFirst.setArguments(args);

        return fragmentFirst;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.delivery_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pos = getArguments().getInt("pos");
        date = getArguments().getString("date");

        tvNodataFound.setText(pos+" "+date);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}