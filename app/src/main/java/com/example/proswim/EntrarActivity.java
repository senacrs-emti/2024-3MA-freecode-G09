package com.example.proswim;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class EntrarActivity extends AppCompatActivity {

    // ID´s
    EditText entrarUsuario, entrarSenha;
    Button entrarBotao;
    TextView cadastrarRedirecionarTexto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        // Deixa a página em tela cheia e tira os botões de navegação android
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        setContentView(R.layout.activity_entrar);

        // Puxa os dados
        entrarUsuario = findViewById(R.id.entrar_usuario);
        entrarSenha = findViewById(R.id.entrar_senha);
        cadastrarRedirecionarTexto = findViewById(R.id.cadastrarRedirecionarTexto);
        entrarBotao = findViewById(R.id.entrar_botao);

        //Botão checka informações e libera acesso
        entrarBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validarUsuario() | !validarSenha()){

                } else {
                    checkUser();
                }
            }
        });

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

        // Redirecionar para a activity "Cadastrar"
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
    public Boolean validarUsuario(){
        String val = entrarUsuario.getText().toString();
        if (val.isEmpty()){
            entrarUsuario.setError("Campo Obrigatório");
            return false;
        } else {
            entrarUsuario.setError(null);
            return true;
        }
    }

    public Boolean validarSenha(){
        String val = entrarSenha.getText().toString();
        if (val.isEmpty()){
            entrarSenha.setError("Campo Obrigatório");
            return false;
        } else {
            entrarSenha.setError(null);
            return true;
        }
    }

    //|
    //|
    //|
    //|
    //|

    //Valida as informações com Database(Firebase)
    public void checkUser(){
        String userUsuario = entrarUsuario.getText().toString().trim();
        String userSenha = entrarSenha.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("usuario").equalTo(userUsuario);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    entrarUsuario.setError(null);
                    String senhaFromDB = snapshot.child(userUsuario).child("senha").getValue(String.class);

                    if (senhaFromDB.equals(userSenha)){
                        entrarUsuario.setError(null);
                        Intent intent = new Intent(EntrarActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        entrarSenha.setError("Credenciais Inválidas");
                        entrarSenha.requestFocus();
                    }
                } else {
                    entrarUsuario.setError("Usuário não existe");
                    entrarUsuario.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}