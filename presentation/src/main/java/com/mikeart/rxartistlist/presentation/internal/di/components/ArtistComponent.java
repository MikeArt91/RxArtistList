package com.mikeart.rxartistlist.presentation.internal.di.components;

import com.mikeart.rxartistlist.presentation.internal.di.PerActivity;
import com.mikeart.rxartistlist.presentation.internal.di.modules.ActivityModule;
import com.mikeart.rxartistlist.presentation.internal.di.modules.ArtistModule;
import com.mikeart.rxartistlist.presentation.view.fragment.ArtistDetailsFragment;
import com.mikeart.rxartistlist.presentation.view.fragment.ArtistListFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 * Injects artist specific Fragments.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, ArtistModule.class})
public interface ArtistComponent extends ActivityComponent {
  void inject(ArtistListFragment userListFragment);
  void inject(ArtistDetailsFragment userDetailsFragment);
}
