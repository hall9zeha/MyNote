package com.barryzea.mynote.ui;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.barryzea.mynote.R;
import com.barryzea.mynote.databinding.ActivityMiNoteMenuBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MiNoteMenuActivity extends AppCompatActivity {

    private ActivityMiNoteMenuBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMiNoteMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigationHome, R.id.navigationFavorites, R.id.navigationMyAccount)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.contentMain);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}