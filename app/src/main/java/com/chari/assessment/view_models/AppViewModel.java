package com.chari.assessment.view_models;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;


import com.chari.assessment.models.NewsList;
import com.chari.assessment.repositories.AppRepository;

import java.util.List;

import javax.inject.Inject;



public class AppViewModel extends ViewModel {


    private AppRepository appRepository;



    private LiveData<List<NewsList>> branchListTableLiveData;


    @Inject
    public AppViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }


    public void getBranchListTableDataFromLocalDB(){
        try{
            branchListTableLiveData=appRepository.getBranchListTableDataFromLocalDB();
        }catch (Exception ex){

        }
    }

    public LiveData<List<NewsList>> getBranchListTableLiveData() {
        return branchListTableLiveData;
    }

}