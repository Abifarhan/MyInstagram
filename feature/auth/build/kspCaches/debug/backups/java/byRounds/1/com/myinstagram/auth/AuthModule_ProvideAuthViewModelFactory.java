package com.myinstagram.auth;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AuthModule_ProvideAuthViewModelFactory implements Factory<AuthViewModel> {
  @Override
  public AuthViewModel get() {
    return provideAuthViewModel();
  }

  public static AuthModule_ProvideAuthViewModelFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AuthViewModel provideAuthViewModel() {
    return Preconditions.checkNotNullFromProvides(AuthModule.INSTANCE.provideAuthViewModel());
  }

  private static final class InstanceHolder {
    private static final AuthModule_ProvideAuthViewModelFactory INSTANCE = new AuthModule_ProvideAuthViewModelFactory();
  }
}
