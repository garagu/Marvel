package com.garagu.marvel.data.datasource.remote;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.garagu.marvel.R;
import com.garagu.marvel.data.datasource.AuthDatasource;
import com.garagu.marvel.data.entity.auth.UserEntity;
import com.garagu.marvel.data.net.exception.EmailAlreadyInUseException;
import com.garagu.marvel.data.net.exception.FirebaseException;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

/**
 * Created by garagu.
 */
public class AuthRemoteDatasource implements AuthDatasource {

    private final Context context;
    private final FirebaseAuth firebaseAuth;

    public AuthRemoteDatasource(@NonNull Context context, @NonNull FirebaseAuth firebaseAuth) {
        this.context = context;
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public Observable<UserEntity> createUser(String name, String email, String password) {
        return Observable.create(subscriber ->
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener(authResult -> {
                            final UserProfileChangeRequest updateRequest = new UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build();
                            updateProfile(authResult.getUser(), updateRequest, subscriber);
                        })
                        .addOnFailureListener(subscriber::onError)
        );
    }

    @Override
    public Observable<UserEntity> getUser() {
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            final UserEntity user = new UserEntity(firebaseUser.getUid(), firebaseUser.getDisplayName(), firebaseUser.getEmail());
            return Observable.just(user);
        } else {
            final String errorMessage = context.getString(R.string.signin_error_unauthenticated);
            final FirebaseException exception = new FirebaseException(errorMessage);
            return Observable.error(exception);
        }
        /*
        return Observable.create(subscriber -> {
            final AuthStateListener authStateListener = new AuthStateListener() {
                @Override
                public void onAuthStateChanged(@NonNull FirebaseAuth auth) {
                    final FirebaseUser firebaseUser = auth.getCurrentUser();
                    if (firebaseUser != null) {
                        final UserEntity user = new UserEntity(firebaseUser.getUid(), firebaseUser.getDisplayName(), firebaseUser.getEmail());
                        subscriber.onNext(user);
                        subscriber.onComplete();
                    } else {
                        final String errorMessage = context.getString(R.string.signin_error_unauthenticated);
                        final FirebaseException exception = new FirebaseException(errorMessage);
                        subscriber.onError(exception);
                    }
                    firebaseAuth.removeAuthStateListener(this);
                }
            };
            firebaseAuth.addAuthStateListener(authStateListener);
        });
        */
    }

    @Override
    public Observable<UserEntity> googleSignIn(String email, String token) {
        return Observable.create(subscriber ->
                firebaseAuth.fetchProvidersForEmail(email)
                        .addOnCompleteListener(task -> {
                            if (emailAlreadyInUse(task.getResult().getProviders(), EmailAuthProvider.PROVIDER_ID)) {
                                final EmailAlreadyInUseException exception = new EmailAlreadyInUseException(context);
                                subscriber.onError(exception);
                            } else {
                                final AuthCredential authCredential = GoogleAuthProvider.getCredential(token, null);
                                firebaseAuth.signInWithCredential(authCredential)
                                        .addOnSuccessListener(getOnSignInListener(subscriber))
                                        .addOnFailureListener(subscriber::onError);
                            }
                        })
                        .addOnFailureListener(subscriber::onError)
        );
    }

    @Override
    public Observable<UserEntity> signIn(String email, String password) {
        return Observable.create(subscriber -> firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(getOnSignInListener(subscriber))
                .addOnFailureListener(subscriber::onError)
        );
    }

    @Override
    public Observable<Boolean> signOut() {
        firebaseAuth.signOut();
        return Observable.just(firebaseAuth.getCurrentUser() == null);
    }

    private void updateProfile(@NonNull FirebaseUser firebaseUser, @NonNull UserProfileChangeRequest updateRequest, @NonNull ObservableEmitter<UserEntity> subscriber) {
        firebaseUser.updateProfile(updateRequest)
                .addOnSuccessListener(voidObject -> {
                    final UserEntity user = new UserEntity(firebaseUser.getUid(), firebaseUser.getDisplayName(), firebaseUser.getEmail());
                    subscriber.onNext(user);
                    subscriber.onComplete();
                })
                .addOnFailureListener(subscriber::onError);
    }

    @NonNull
    private OnSuccessListener<AuthResult> getOnSignInListener(@NonNull ObservableEmitter<UserEntity> subscriber) {
        return authResult -> {
            final FirebaseUser firebaseUser = authResult.getUser();
            final UserEntity user = new UserEntity(firebaseUser.getUid(), firebaseUser.getDisplayName(), firebaseUser.getEmail());
            subscriber.onNext(user);
            subscriber.onComplete();
        };
    }

    private boolean emailAlreadyInUse(@Nullable List<String> providers, @NonNull String providerId) {
        return (providers != null) && (providers.contains(providerId));
    }

}