package com.aw.epayments.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.aw.epayments.R;

import java.util.List;

public class PaymentAdpater extends RecyclerView.Adapter<PaymentAdpater.ViewHolder> {
    private List<Integer> _paymentImage;
    private LayoutInflater _mInflater;
    private PaymentAdpater.ItemClickListener _mClickListener;
    private Context context;

    public PaymentAdpater(Context context, List<Integer> imageData) {
        this._mInflater = LayoutInflater.from(context);
        this._paymentImage = imageData;
        this.context = context;

    }

    @NonNull
    @Override
    public PaymentAdpater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = _mInflater.inflate(R.layout.custom_paymet_buttons, viewGroup, false);
        return new PaymentAdpater.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentAdpater.ViewHolder viewHolder, int i) {
        viewHolder.myView.setImageResource(_paymentImage.get(i));
        //Toast.makeText(context, String.valueOf(i), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return _paymentImage.size();
    }

    public int getItem(int id) {
        return _paymentImage.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(PaymentAdpater.ItemClickListener itemClickListener) {
        this._mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position, ImageView drawable, boolean isActive);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myView;
        boolean isActive = true;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myView = itemView.findViewById(R.id.payment_option);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (_mClickListener != null) {
                _mClickListener.onItemClick(v, getAdapterPosition(), myView, isActive);

            } else {
                isActive = false;
            }
        }
    }
}
