package com.mikeart.rxartistlist.presentation.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mikeart.rxartistlist.presentation.AndroidApplication;

import com.mikeart.rxartistlist.presentation.R;
import com.mikeart.rxartistlist.presentation.internal.di.components.ApplicationComponent;
import com.mikeart.rxartistlist.presentation.internal.di.modules.ActivityModule;
import com.mikeart.rxartistlist.presentation.navigation.Navigator;
import javax.inject.Inject;

/**
 * Base {@link android.app.Activity} class for every Activity in this application.
 */
public abstract class BaseActivity extends AppCompatActivity {

  @Inject Navigator navigator;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.getApplicationComponent().inject(this);
    this.getApplicationContext().setTheme(R.style.AppTheme);
  }

  /**
   * Adds a {@link Fragment} to this activity's layout.
   *
   * @param containerViewId The container view to where add the fragment.
   * @param fragment The fragment to be added.
   */
  protected void addFragment(int containerViewId, Fragment fragment) {
    FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
    fragmentTransaction.add(containerViewId, fragment);
    //fragmentTransaction.replace(containerViewId,fragment);
    fragmentTransaction.commit();
  }

  /**
   * Get the Main Application component for dependency injection.
   *
   * @return {@link ApplicationComponent}
   */
  protected ApplicationComponent getApplicationComponent() {
    return ((AndroidApplication) getApplication()).getApplicationComponent();
  }

  /**
   * Get an Activity module for dependency injection.
   *
   * @return {@link ActivityModule}
   */
  protected ActivityModule getActivityModule() {
    return new ActivityModule(this);
  }
}
