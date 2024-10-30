package com.example.proswim;

import android.content.Intent;
import android.os.Bundle;
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

    EditText entrarUsuario, entrarSenha;
    Button entrarBotao;
    TextView cadastrarRedirecionarTexto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrar);

        entrarUsuario = findViewById(R.id.entrar_usuario);
        entrarSenha = findViewById(R.id.entrar_senha);
        cadastrarRedirecionarTexto = findViewById(R.id.cadastrarRedirecionarTexto);
        entrarBotao = findViewById(R.id.entrar_botao);

        entrarBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validarUsuario() | !validarSenha()){

                } else {
                    checkUser();
                }
            }
        });

        cadastrarRedirecionarTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EntrarActivity.this, CadastrarActivity.class);
                startActivity(intent);
            }
        });
    }

    public Boolean validarUsuario(){
        String val = entrarUsuario.getText().toString();
        if (val.isEmpty()){
            entrarUsuario.setError("Usuário não pode ficar vazio");
            return false;
        } else {
            entrarUsuario.setError(null);
            return true;
        }
    }

    public Boolean validarSenha(){
        String val = entrarSenha.getText().toString();
        if (val.isEmpty()){
            entrarSenha.setError("Senha não pode ficar vazia");
            return false;
        } else {
            entrarSenha.setError(null);
            return true;
        }
    }

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