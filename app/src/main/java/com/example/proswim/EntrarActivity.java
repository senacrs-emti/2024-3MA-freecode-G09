package com.example.proswim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class EntrarActivity extends AppCompatActivity {

    // ID´s
    EditText entrarEmail, entrarSenha;
    Button entrarBotao;
    TextView cadastrarRedirecionarTexto;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        mAuth = FirebaseAuth.getInstance();

        // Puxa os dados
        entrarEmail = findViewById(R.id.entrar_email);
        entrarSenha = findViewById(R.id.entrar_senha);
        cadastrarRedirecionarTexto = findViewById(R.id.cadastrarRedirecionarTexto);
        entrarBotao = findViewById(R.id.entrar_botao);

        //|
        //|
        //|
        //|
        //|

        //Botão checka informações e libera acesso
        entrarBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        //|
        //|
        //|
        //|
        //|

        // Deixa a página em tela cheia e tira os botões de navegação android
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //|
        //|
        //|
        //|
        //|

        // Sublinhar uma palavra do textview
        TextView textView = findViewById(R.id.cadastrarRedirecionarTexto);

        String textoCompleto = "Não tem uma conta? Cadastrar";
        SpannableString spannableString = new SpannableString(textoCompleto);
        String palavraParaSublinhar = "Cadastrar";

        int startIndex = textoCompleto.indexOf(palavraParaSublinhar);
        int endIndex = startIndex + palavraParaSublinhar.length();

        spannableString.setSpan(new UnderlineSpan(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);

        //|
        //|
        //|
        //|
        //|

        // Volta para a activity "Cadastrar"
        cadastrarRedirecionarTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //|
    //|
    //|
    //|
    //|

    // Alerta se estiver com o campo vazio
    private void loginUser() {
        String email = entrarEmail.getText().toString().trim();
        String senha = entrarSenha.getText().toString().trim();

        if (email.isEmpty()) {
            entrarEmail.setError("Campo Obrigatório");
            entrarEmail.requestFocus();
            return;
        }

        if (senha.isEmpty()) {
            entrarSenha.setError("Campo Obrigatório");
            entrarSenha.requestFocus();
            return;
        }

        //|
        //|
        //|
        //|
        //|

        // Valida as informações com Authentication(Firebase)
        mAuth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {

                        // Checar se o e-mail foi verificado
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null && user.isEmailVerified()) {

                            // E-mail verificado, pode ir para a tela principal
                            Intent intent = new Intent(EntrarActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();  // Finaliza a tela de login

                            // Salva a preferência de login
                            SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putBoolean("isLoggedIn", true);
                            editor.apply();
                        } else {

                            // E-mail não verificado, exibe mensagem
                            Toast.makeText(EntrarActivity.this, "Verifique seu e-mail para continuar.", Toast.LENGTH_SHORT).show();
                            mAuth.signOut();  // Desconecta o usuário, pois o e-mail não foi verificado
                        }
                    } else {

                        // Trate o erro de login
                        Toast.makeText(EntrarActivity.this, "Senha ou Email incorreto", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}