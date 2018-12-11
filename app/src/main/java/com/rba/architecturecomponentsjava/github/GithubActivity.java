package com.rba.architecturecomponentsjava.github;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rba.architecturecomponentsjava.R;
import com.rba.architecturecomponentsjava.database.entity.User;
import com.rba.architecturecomponentsjava.user.UserProfileViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class GithubActivity extends AppCompatActivity {

    private static final String USER_LOGIN = "JakeWharton";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @BindView(R.id.iv_avatar)
    AppCompatImageView ivAvatar;
    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_company)
    TextView tvCompany;
    @BindView(R.id.tv_web)
    TextView tvWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        init();
    }

    private void init() {
        UserProfileViewModel viewModel =
                ViewModelProviders.of(this, viewModelFactory).get(UserProfileViewModel.class);
        viewModel.init(USER_LOGIN);
        viewModel.getUser().observe(this, this::updateUI);
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
