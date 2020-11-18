package com.hermanowicz.badmintonschool.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.hermanowicz.badmintonschool.R;

public class SalesFragment extends Fragment {

    private WebView webview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sales, container, false);

        initView(view);
        setView();

        return view;
    }

    private void initView(View view) {
        webview = view.findViewById(R.id.webview_sales);
    }

    private void setView() {
        webview.getSettings().setBuiltInZoomControls(true);
        webview.loadUrl("http://lukaszkomar.vxm.pl/mobile_app/?page_id=14");
    }
}