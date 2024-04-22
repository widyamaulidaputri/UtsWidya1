package com.example.utswidya;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.utswidya.adapters.ListUsersAdapter;
import com.example.utswidya.config.ApiConfig;
import com.example.utswidya.databinding.ActivityMainBinding;
import com.example.utswidya.models.GithubResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ListUsersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        adapter = new ListUsersAdapter();
        String nama = "widya";

        Call<GithubResponse> api = ApiConfig.getApiService().getUsers(nama);
        api.enqueue(new Callback<GithubResponse>() {
            @Override
            public void onResponse(@NonNull Call<GithubResponse> call, @NonNull Response<GithubResponse> response) {
                binding.rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                binding.rv.setAdapter(adapter);
                if (response.isSuccessful()){
                    adapter.submitList(response.body().getItems());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GithubResponse> call, @NonNull Throwable t) {
                Log.e("error", Objects.requireNonNull(t.getMessage()));
            }
        });

    }
}