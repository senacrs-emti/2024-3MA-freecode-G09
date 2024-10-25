package com.example.proswim;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proswim.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

        ActivityMainBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            replaceFragment(new HomeFragment());
            binding.bottomNav.setBackground(null);

            binding.bottomNav.setOnItemSelectedListener(item -> {

                int id = item.getItemId();

                if (id == R.id.home) {
                    replaceFragment(new HomeFragment());
                }
                else if (id == R.id.preparacao) {
                    replaceFragment(new PreparacaoFragment());
                }
                else if (id == R.id.mobilidade) {
                    replaceFragment(new MobilidadeFragment());
                }
                else if (id == R.id.alimentacao) {
                    replaceFragment(new AlimentacaoFragment());
                }

                return true;
            });
        }

        private void replaceFragment (Fragment fragment) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, fragment);
            fragmentTransaction.commit();

    }
}