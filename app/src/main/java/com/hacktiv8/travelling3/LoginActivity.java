package com.hacktiv8.travelling3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    GoogleSignInClient googleSignInClient;
    FirebaseAuth auth;

    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        MaterialButton loginBtnLogin = findViewById(R.id.loginBtnLogin);

        // Konfigurasi Google Sign-In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        loginBtnLogin.setOnClickListener(v -> signInWithGoogle());
    }

    private void signInWithGoogle() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
//        Intent signInIntent = googleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            try {
                // Mendapatkan objek GoogleSignInAccount dari intent
                GoogleSignInAccount account = GoogleSignIn.getSignedInAccountFromIntent(data).getResult(ApiException.class);

                // Mendapatkan token ID dari objek GoogleSignInAccount
                String idToken = account.getIdToken();

                // Membuat credential untuk Firebase Authentication menggunakan token ID Google
                AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

                // ...
                auth.signInWithCredential(credential).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        // Jika autentikasi berhasil, cek apakah pengguna baru atau sudah terdaftar
                        boolean isNewUser = Objects.requireNonNull(task.getResult().getAdditionalUserInfo()).isNewUser();
                        String uid = Objects.requireNonNull(auth.getCurrentUser()).getUid();
                        String url = "https://hacktiv8-finalproject-default-rtdb.asia-southeast1.firebasedatabase.app/";

                        DatabaseReference userRef = FirebaseDatabase.getInstance(url).getReference().child("users").child(uid);
                        if (isNewUser) {
                            // Jika pengguna baru, tambahkan data baru ke Realtime Database
                            userRef.child("uid").setValue(uid);

                            // Lanjut ke Register1Activity
                            Intent intent = new Intent(LoginActivity.this, Register1Activity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Jika pengguna sudah terdaftar, periksa nomor telepon
                            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    if (snapshot.child("nohp").exists()) {
                                        // Jika nomor telepon sudah ada, lanjut ke MainActivity
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        // Jika nomor telepon tidak ada, lanjut ke Register1Activity
                                        Intent intent = new Intent(LoginActivity.this, Register1Activity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    // Handle error ketika membaca dari Realtime Database
                                    // ...
                                }
                            });
                        }
                    } else {
                        // Jika autentikasi gagal, handle error
                        // Misalnya, tampilkan pesan error kepada pengguna
                        // ...
                    }
                });

            } catch (ApiException e) {
                // Handle error saat Google Sign-In
                // Misalnya, tampilkan pesan error kepada pengguna
                // ...
            }
        }
    }
}