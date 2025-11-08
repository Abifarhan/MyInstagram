package com.myinstagram.auth;

import com.google.firebase.auth.FirebaseAuth;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class AuthViewModel_Factory implements Factory<AuthViewModel> {
  private final Provider<FirebaseAuth> firebaseAuthProvider;

  public AuthViewModel_Factory(Provider<FirebaseAuth> firebaseAuthProvider) {
    this.firebaseAuthProvider = firebaseAuthProvider;
  }

  @Override
  public AuthViewModel get() {
    return newInstance(firebaseAuthProvider.get());
  }

  public static AuthViewModel_Factory create(Provider<FirebaseAuth> firebaseAuthProvider) {
    return new AuthViewModel_Factory(firebaseAuthProvider);
  }

  public static AuthViewModel newInstance(FirebaseAuth firebaseAuth) {
    return new AuthViewModel(firebaseAuth);
  }
}
