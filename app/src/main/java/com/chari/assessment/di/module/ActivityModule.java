package com.chari.assessment.di.module;


import com.chari.assessment.activity.News;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract News contributeNews();

}
