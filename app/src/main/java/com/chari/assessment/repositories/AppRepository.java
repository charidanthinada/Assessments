package com.chari.assessment.repositories;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.chari.assessment.models.NewsList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;




@Singleton
public class AppRepository {

    private static final String TAG = AppRepository.class.getCanonicalName();
    private final Executor executor;

    Context context;

    @Inject
    public AppRepository(Executor executor, Context context){

        this.executor = executor;
        this.context = context;
    }

    public LiveData<List<NewsList>> getBranchListTableDataFromLocalDB()    {
        final MutableLiveData<List<NewsList>> data = new MutableLiveData<>();
        executor.execute(() -> {
            List<NewsList> rawdataResponseDTOList= new ArrayList<NewsList>();
            for(int i=1;i<=15;i++){
                NewsList newsList =new NewsList();
                newsList.setNewsId(i);
                newsList.setNewsHeadlines("News Main Head Line -: "+i);
                newsList.setNewsSublines("News Sublines for MainHeading -: "+i);
                rawdataResponseDTOList.add(newsList) ;
            }
            data.postValue(rawdataResponseDTOList);
        });
        return data;
    }

}