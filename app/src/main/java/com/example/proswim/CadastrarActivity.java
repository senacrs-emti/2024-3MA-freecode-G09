package com.example.proswim;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastrarActivity extends AppCompatActivity {

    // ID´s
    EditText cadastrarNome, cadastrarEmail, cadastrarUsuario, cadastrarSenha;
    TextView entrarRedirecionarTexto;
    Button cadastrarBotao;

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        mAuth = FirebaseAuth.getInstance();

        // Puxa os dados
        cadastrarEmail = findViewById(R.id.cadastrar_email);
        cadastrarSenha = findViewById(R.id.cadastrar_senha);
        cadastrarBotao = findViewById(R.id.cadastrar_botao);
        entrarRedirecionarTexto = findViewById(R.id.entrarRedirecionarTexto);

        //|
        //|
        //|
        //|
        //|

        //Botão checka informações e libera acesso
        cadastrarBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
                finish();
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

        // Sublinha uma palavra do textview
        TextView textView = findViewById(R.id.entrarRedirecionarTexto);

        String textoCompleto = "Já tem uma conta? Entrar.";
        SpannableString spannableString = new SpannableString(textoCompleto);
        String palavraParaSublinhar = "Entrar";

        int startIndex = textoCompleto.indexOf(palavraParaSublinhar);
        int endIndex = startIndex + palavraParaSublinhar.length();

        spannableString.setSpan(new UnderlineSpan(), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);

        //|
        //|
        //|
        //|
        //|

        // Redireciona para a activity "Entrar"
        entrarRedirecionarTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastrarActivity.this, EntrarActivity.class);
                startActivity(intent);
            }
        });
    }

    //|
    //|
    //|
    //|
    //|

    // Alerta se estiver com o campo vazio
    private void registerUser() {
        String email = cadastrarEmail.getText().toString().trim();
        String senha = cadastrarSenha.getText().toString().trim();

        if (email.isEmpty()) {
            cadastrarEmail.setError("Campo Obrigatório!");
            cadastrarEmail.requestFocus();
            return;
        }

        if (senha.isEmpty()) {
            cadastrarSenha.setError("Campo Obrigatório!");
            cadastrarSenha.requestFocus();
            return;
        }

        // Valida as informações com Authentication(Firebase)
        mAuth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            user.sendEmailVerification().addOnCompleteListener(task1 -> {
                                if (task1.isSuccessful()) {
                                    Toast.makeText(CadastrarActivity.this, "Cadastro realizado. Verifique seu email.", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(CadastrarActivity.this, EntrarActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(CadastrarActivity.this, "Falha no envio do email de verificação.", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    } else {
                        Toast.makeText(CadastrarActivity.this, "Já existe um usuário com esse Email", Toast.LENGTH_LONG).show();
                    }
                });
    }
}