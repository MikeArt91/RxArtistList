package com.mikeart.rxartistlist.presentation.internal.di.components;

import android.content.Context;
import com.mikeart.rxartistlist.domain.executor.PostExecutionThread;
import com.mikeart.rxartistlist.domain.executor.ThreadExecutor;
import com.mikeart.rxartistlist.domain.repository.ArtistRepository;
import com.mikeart.rxartistlist.presentation.internal.di.modules.ApplicationModule;
import com.mikeart.rxartistlist.presentation.view.activity.BaseActivity;

import dagger.Component;
import javax.inject.Singleton;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseActivity baseActivity);

  //Exposed to sub-graphs.
  Context context();
  ThreadExecutor threadExecutor();
  PostExecutionThread postExecutionThread();
  ArtistRepository userRepository();
}
