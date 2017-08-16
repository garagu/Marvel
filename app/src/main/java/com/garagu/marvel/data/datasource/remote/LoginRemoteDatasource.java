package com.garagu.marvel.data.datasource.remote;

import android.content.Context;
import android.support.annotation.NonNull;

import com.garagu.marvel.R;
import com.garagu.marvel.data.datasource.LoginDatasource;
import com.garagu.marvel.data.entity.login.LoginEntity;
import com.garagu.marvel.data.entity.login.RegisterEntity;
import com.garagu.marvel.data.entity.login.UserEntity;
import com.garagu.marvel.data.net.exception.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

/**
 * Created by garagu.
 */
public class LoginRemoteDatasource implements LoginDatasource {

    private final Context context;
    private final FirebaseAuth firebaseAuth;

    public LoginRemoteDatasource(@NonNull Context context, @NonNull FirebaseAuth firebaseAuth) {
        this.context = context;
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    public Observable<UserEntity> getUser() {
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            final UserEntity user = new UserEntity(firebaseUser.getDisplayName(), firebaseUser.getEmail());
            return Observable.just(user);
        } else {
            final String errorMessage = context.getString(R.string.login_error_unauthenticated);
            final FirebaseException exception = new FirebaseException(errorMessage);
            return Observable.error(exception);
        }
    }

    @Override
    public Observable<Boolean> login(LoginEntity login) {
        return Observable.create(subscriber -> firebaseAuth
                .signInWithEmailAndPassword(login.getEmail(), login.getPassword())
                .addOnSuccessListener(authResult -> {
                    subscriber.onNext(true);
                    subscriber.onComplete();
                })
                .addOnFailureListener(subscriber::onError)
        );
    }

    @Override
    public Observable<Boolean> registerUser(RegisterEntity user) {
        return Observable.create(subscriber -> firebaseAuth
                .createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnSuccessListener(authResult -> {
                    final UserProfileChangeRequest updateRequest = new UserProfileChangeRequest.Builder()
                            .setDisplayName(user.getName())
                            .build();
                    updateProfile(authResult.getUser(), updateRequest, subscriber);
                })
                .addOnFailureListener(subscriber::onError)
        );
    }

    private void updateProfile(@NonNull FirebaseUser user, @NonNull UserProfileChangeRequest updateRequest, @NonNull ObservableEmitter<Boolean> subscriber) {
        user.updateProfile(updateRequest)
                .addOnSuccessListener(voidObject -> {
                    subscriber.onNext(true);
                    subscriber.onComplete();
                })
                .addOnFailureListener(subscriber::onError);
    }

}