package com.tecnm.campusuruapan.pi.tes.helpers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.tecnm.campusuruapan.pi.tes.LoginActivity;
import com.tecnm.campusuruapan.pi.tes.MainActivity;
import com.tecnm.campusuruapan.pi.tes.interfaces.Information;

import java.util.Objects;

public class FirebaseAuthHelper {
    // Initialize Firebase Auth
    public static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private Information information;
    private Context context;
    private final StringHelper stringHelper = new StringHelper();
    private final FirebaseFirestoreHelper firebaseFirestoreHelper = new FirebaseFirestoreHelper();

    public void setContext(Context context) {
        this.context = context;
    }

    public static FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    //Registrarse
    public void createUserEmailAndPassword(final String email, final String password, final ProgressDialog dialog, final String[] args, String tipo) {

        if (stringHelper.isNotEmptyCredentials(email, password)) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                //Creación de usuario
                                firebaseFirestoreHelper.addData(information, dialog, context, user.getUid(), email, password, args, tipo);

                            } else {
                                String error = Objects.requireNonNull(task.getException()).getMessage();
                                if (Objects.equals(error, "The email address is already in use by another account.")) {
                                    information.getMessage("Error en el registro, email registrado");
                                } else if (Objects.equals(error, "The given password is invalid. [ Password should be at least 6 characters ]")) {
                                    information.getMessage("La contraseña debe de tener por lo menos 6 caracteres");
                                } else {
                                    information.getMessage("Error al registrarse");
                                }
                                //createUser(null,"",null);
                                dialog.dismiss();
                            }
                        }
                    });

        } else {
            dialog.dismiss();
            information.getMessage("Error en el email o en el password");
        }
    }

    //Iniciar sesión
    public void signInWithEmailAndPassword(final String email, String password, final ProgressDialog dialog) {
        if (stringHelper.isNotEmptyCredentials(email, password)) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                firebaseFirestoreHelper.getData(Objects.requireNonNull(user).getUid(), dialog, information, context);
                            } else {
                                String error = Objects.requireNonNull(task.getException()).getMessage();
                                switch (Objects.requireNonNull(error)) {
                                    case "There is no user record corresponding to this identifier. The user may have been deleted.":
                                        information.getMessage("Esas credenciales no existen en la base de datos o el email es inválido");
                                        break;
                                    case "The password is invalid or the user does not have a password.":
                                        information.getMessage("La contraseña es incorrecta");
                                        break;
                                    case "The user account has been disabled by an administrator.":
                                        information.getMessage("Cuenta inhabilidada, contacta al administrador");
                                        break;
                                    default:
                                        information.getMessage("Verifica tu conexión a Internet");
                                        break;
                                }
                                Log.e("error", error);
                                dialog.dismiss();
                            }
                        }
                    });
        } else {
            information.getMessage("Error en el email o en el password");
            dialog.dismiss();
        }
    }

    ///Cerrar sesión
    public void signout(final ProgressDialog dialog) {
        mAuth.signOut();
        FirebaseFirestoreHelper.user = null;
        Log.e("er", mAuth.getCurrentUser() + "");
        dialog.dismiss();
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        ((Activity) context).finish();
    }


    public void setOnInformationListener(Information information) {
        this.information = information;
    }

}
