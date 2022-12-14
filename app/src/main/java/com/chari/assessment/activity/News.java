package com.chari.assessment.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.chari.assessment.R;
import com.chari.assessment.adapter.NewsAdapter;
import com.chari.assessment.models.NewsList;
import com.chari.assessment.view_models.AppViewModel;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;

public class News extends BaseActivity implements NewsAdapter.SyncCallbackInterface {
    List<NewsList> branchListTableList;
    NewsAdapter branchListAdapter;
    RecyclerView rvLoanType;

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Inject
    public ViewModelProvider.Factory viewModelFactory;
    public AppViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        rvLoanType=(RecyclerView)findViewById(R.id.rv_product_news);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvLoanType.setLayoutManager(mLayoutManager);
        rvLoanType.setItemAnimator(new DefaultItemAnimator());
        rvLoanType.setNestedScrollingEnabled(false);
        configureDagger();
        configureViewModel();


    }

    private void configureDagger(){
        AndroidInjection.inject(this);
    }

    public void configureViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel.class);
        getBranchListTableDataFromLocalDB();
    }

    public void getBranchListTableDataFromLocalDB() {
        try {

            viewModel.getBranchListTableDataFromLocalDB();
            if (viewModel.getBranchListTableLiveData() != null) {
                Observer getLeadRawDataObserver = new Observer() {
                    @Override
                    public void onChanged(@Nullable Object o) {
                        branchListTableList = (List<NewsList>) o;
                        viewModel.getBranchListTableLiveData().removeObserver(this);
                        if (branchListTableList != null && branchListTableList.size() > 0) {

                            rvLoanType.setVisibility(View.VISIBLE);

                            branchListAdapter = new NewsAdapter(News.this,
                                    branchListTableList, News.this);
                            rvLoanType.setAdapter(branchListAdapter);
                            branchListAdapter.notifyDataSetChanged();
                        }
                        else {

                            rvLoanType.setVisibility(View.GONE);
                        }
                    }
                };
                viewModel.getBranchListTableLiveData().observe(this, getLeadRawDataObserver);
            }
        } catch (Exception ex) {


        }
    }


    @Override
    public void openScreenCallback(int position, NewsList branchListTable) {
        if (!TextUtils.isEmpty(branchListTable.getNewsHeadlines())) {
        }
    }
}