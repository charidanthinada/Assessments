package com.chari.assessment.di.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;


import com.chari.assessment.di.key.ViewModelKey;
import com.chari.assessment.view_models.AppViewModel;
import com.chari.assessment.view_models.FactoryViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AppViewModel.class)
    abstract ViewModel bindDynamicUIViewModel(AppViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);
}
