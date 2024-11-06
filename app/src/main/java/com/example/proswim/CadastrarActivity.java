package com.example.proswim;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CadastrarActivity extends AppCompatActivity {

    // ID´s
    EditText cadastrarNome, cadastrarEmail, cadastrarUsuario, cadastrarSenha;
    TextView entrarRedirecionarTexto;
    Button cadastrarBotao;
    FirebaseDatabase database;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        // Deixa a página em tela cheia e tira os botões de navegação android
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_cadastrar);

        // Puxa os dados
        cadastrarNome = findViewById(R.id.cadastrar_nome);
        cadastrarEmail = findViewById(R.id.cadastrar_email);
        cadastrarUsuario = findViewById(R.id.cadastrar_usuario);
        cadastrarSenha = findViewById(R.id.cadastrar_senha);
        cadastrarBotao = findViewById(R.id.cadastrar_botao);
        entrarRedirecionarTexto = findViewById(R.id.entrarRedirecionarTexto);


        //Botão checka informações e libera acesso
        cadastrarBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarNome() & validarEmail() & validarUsuario() & validarSenha()) {
                    database = FirebaseDatabase.getInstance();
                    reference = database.getReference("users");

                    String nome = cadastrarNome.getText().toString();
                    String email = cadastrarEmail.getText().toString();
                    String usuario = cadastrarUsuario.getText().toString();
                    String senha = cadastrarSenha.getText().toString();

                    HelperClass helperClass = new HelperClass(nome, email, usuario, senha);
                    reference.child(usuario).setValue(helperClass);

                    Toast.makeText(CadastrarActivity.this, "Você se cadastrou com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CadastrarActivity.this, EntrarActivity.class);
                    startActivity(intent);
                }
            }
        });

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


    // Alerta se estiver com o campo vazio
    public Boolean validarNome() {
        String val = cadastrarNome.getText().toString();
        if (val.isEmpty()) {
            cadastrarNome.setError("Campo Obrigatório!");
            return false;
        } else {
            cadastrarNome.setError(null);
            return true;
        }
    }

    public Boolean validarEmail() {
        String val = cadastrarEmail.getText().toString();
        if (val.isEmpty()) {
            cadastrarEmail.setError("Campo Obrigatório!");
            return false;
        } else {
            cadastrarEmail.setError(null);
            return true;
        }
    }

    public Boolean validarUsuario() {
        String val = cadastrarUsuario.getText().toString();
        if (val.isEmpty()) {
            cadastrarUsuario.setError("Campo Obrigatório!");
            return false;
        } else {
            cadastrarUsuario.setError(null);
            return true;
        }
    }

    public Boolean validarSenha() {
        String val = cadastrarSenha.getText().toString();
        if (val.isEmpty()) {
            cadastrarSenha.setError("Campo Obrigatório!");
            return false;
        } else {
            cadastrarSenha.setError(null);
            return true;
        }
    }
}