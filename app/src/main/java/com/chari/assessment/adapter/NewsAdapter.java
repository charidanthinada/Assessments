package com.chari.assessment.adapter;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.chari.assessment.R;
import com.chari.assessment.models.NewsList;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.LoanTypeViewHolder> {
    private Context context;
    List<NewsList> rawDataTableList;
    List<NewsList> rawDataTableListFiltered;
    SyncCallbackInterface syncCallbackInterface;

    public NewsAdapter(Context context, List<NewsList> rawDataTableList,
                       SyncCallbackInterface syncCallbackInterface) {
        this.context = context;
        this.rawDataTableList = rawDataTableList;
        this.rawDataTableListFiltered = rawDataTableList;
        this.syncCallbackInterface = syncCallbackInterface;

    }

    @NonNull
    @Override
    public LoanTypeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.newslayout, viewGroup, false);
        return new LoanTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanTypeViewHolder loanTypeViewHolder, int i) {
        try {
            if (rawDataTableListFiltered != null && rawDataTableListFiltered.size() > 0) {
                NewsList branchListTable=rawDataTableListFiltered.get(i);
                loanTypeViewHolder.tvNewsHeadLine.setText(branchListTable.getNewsHeadlines());
                loanTypeViewHolder.tvNewsSubLine.setText(branchListTable.getNewsSublines());

            }
        } catch (Exception ex) {

        }
    }

    @Override
    public int getItemCount() {
        if (rawDataTableListFiltered != null) {

            return rawDataTableListFiltered.size();
        } else {
            return 0;
        }
    }


    public class LoanTypeViewHolder extends RecyclerView.ViewHolder {
        TextView tvNewsHeadLine,tvNewsSubLine;
        public LoanTypeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNewsHeadLine = (TextView) itemView.findViewById(R.id.tvNewsHeadLine);
            tvNewsSubLine = (TextView) itemView.findViewById(R.id.tvNewsSubLine);


        }
    }


    public interface SyncCallbackInterface {
        void openScreenCallback(int position, NewsList branchListTable);
    }
}
