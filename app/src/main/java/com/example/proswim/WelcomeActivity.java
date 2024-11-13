package com.example.proswim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // Verifica se o usuário já está logado com Firebase Authentication
        if (auth.getCurrentUser() != null || preferences.getBoolean("isLoggedIn", false)) {
            // Usuário logado, vá diretamente para MainActivity
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            // Usuário não logado, mostra a tela de boas-vindas
            setContentView(R.layout.activity_welcome);
        }


        TextView button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Deixa a página em tela cheia e tira os botões de navegação android
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                setContentView(R.layout.activity_welcome);

                //|
                //|
                //|
                //|
                //|

                // Atualiza o SharedPreferences para indicar que o Welcome foi visualizado
                preferences.edit().putBoolean("isFirstTime", false).apply();

                // Vai para a tela de cadastro ou login
                startActivity(new Intent(WelcomeActivity.this, CadastrarActivity.class));
                finish();

            }
        });
    }
}