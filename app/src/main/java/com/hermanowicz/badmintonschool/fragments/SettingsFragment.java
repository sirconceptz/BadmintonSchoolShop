package com.hermanowicz.badmintonschool.fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hermanowicz.badmintonschool.BuildConfig;
import com.hermanowicz.badmintonschool.R;
import com.hermanowicz.badmintonschool.dialogs.AuthorDialog;

public class SettingsFragment extends Fragment {

    private TextView version, author;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        initView(view);
        setListeners();
        setView();

        return view;
    }

    private void showAuthorDialog() {
        AppCompatDialogFragment dialogFragment = AuthorDialog.newInstance(
                R.string.settings_authorLabel);
        assert getFragmentManager() != null;
        dialogFragment.show(getFragmentManager(), "dialog");
    }

    private void initView(View view) {
        version = view.findViewById(R.id.settings_appVersion);
        author = view.findViewById(R.id.appAuthor);
    }

    private void setListeners() {
        version.setOnClickListener(v -> {
            showAuthorDialog();
        });

        author.setOnClickListener(v -> {
            showAuthorDialog();
        });
    }

    private void setView() {
        version.setText(String.format("%s: %s", getResources().getText(R.string.settings_version), BuildConfig.VERSION_NAME));
        author.setText(String.format("%s: %s", getResources().getText(R.string.settings_authorLabel), getResources().getText(R.string.settings_author_name)));
    }
}