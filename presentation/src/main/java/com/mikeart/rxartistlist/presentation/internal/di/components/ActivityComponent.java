package com.mikeart.rxartistlist.presentation.internal.di.components;

import android.support.v7.app.AppCompatActivity;

import com.mikeart.rxartistlist.presentation.internal.di.PerActivity;
import com.mikeart.rxartistlist.presentation.internal.di.modules.ActivityModule;

import dagger.Component;

/**
 * A base component upon which fragment's components may depend.
 * Activity-level components should extend this component.
 *
 * Subtypes of ActivityComponent should be decorated with annotation:
 * {@link PerActivity}
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
  //Exposed to sub-graphs.
  AppCompatActivity activity();
}
