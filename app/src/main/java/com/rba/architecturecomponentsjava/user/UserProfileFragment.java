package com.rba.architecturecomponentsjava.user;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rba.architecturecomponentsjava.R;
import com.rba.architecturecomponentsjava.database.entity.User;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class UserProfileFragment extends Fragment {

    public static final String UID_KEY = "uid";
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private UserProfileViewModel viewModel;

    @BindView(R.id.iv_avatar)
    AppCompatImageView ivAvatar;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_web)
    TextView tvWeb;

    public UserProfileFragment() {
        //Do nothing
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        configureDagger();
        configureViewModel();
    }

    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel() {
        if (getArguments() != null) {
            String userLogin = getArguments().getString(UID_KEY);
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserProfileViewModel.class);
            viewModel.init(userLogin);
            viewModel.getUser().observe(this, this::updateUI);
        }
    }

    private void updateUI(@Nullable User user) {
        if (user != null) {
            Glide.with(this).load(user.getAvatarUrl()).into(ivAvatar);
            tvUsername.setText(user.getName());
            tvCompany.setText(user.getCompany());
            tvWeb.setText(user.getBlog());
        }
    }
}
