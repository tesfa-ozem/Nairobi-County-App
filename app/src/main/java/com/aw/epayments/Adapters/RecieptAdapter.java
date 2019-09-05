package com.aw.epayments.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aw.epayments.R;
import com.aw.epayments.models.Response.TransactionDetail;

import java.util.List;

public class RecieptAdapter extends RecyclerView.Adapter<RecieptAdapter.ViewHolder> {
    List<TransactionDetail> _transactionDetails;
    private LayoutInflater _mInflater;

    private Context context;

    public RecieptAdapter(Context context, List<TransactionDetail> transactionDetails) {
        this._mInflater = LayoutInflater.from(context);
        this.context = context;
        this._transactionDetails = transactionDetails;
    }

    @NonNull
    @Override
    public RecieptAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = _mInflater.inflate(R.layout.reciept_transactions, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecieptAdapter.ViewHolder holder, int position) {

        TransactionDetail trs = _transactionDetails.get(position);
        TextView textView = holder.tsAmount;
        textView.setText(String.format("KSH %s", String.valueOf(trs.getValue())));
        TextView textView1 = holder.tsDescription;
        textView1.setText(trs.getKey());

    }

    @Override
    public int getItemCount() {
        return _transactionDetails.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tsAmount, tsDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            tsAmount = itemView.findViewById(R.id.receipt_parking);

            tsDescription = itemView.findViewById(R.id.description);

        }


    }
}
