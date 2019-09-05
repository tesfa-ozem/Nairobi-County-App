package com.aw.epayments.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aw.epayments.R;
import com.aw.epayments.models.Response.ParkingAmount;

import java.util.List;

public class ParkingChargeAdapter extends RecyclerView.Adapter<ParkingChargeAdapter.ViewHolder> {
    List<ParkingAmount> _payment;
    Context context;

    public ParkingChargeAdapter(List<ParkingAmount> parkingAmount, Context context) {
        _payment = parkingAmount;
        this.context = context;

    }

    @NonNull
    @Override
    public ParkingChargeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.alert_parking_penalties, parent, false);

        // Return a new holder instance
        ParkingChargeAdapter.ViewHolder viewHolder = new ParkingChargeAdapter.ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ParkingChargeAdapter.ViewHolder viewHolder, int i) {

        ParkingAmount amount = _payment.get(i);
        String amountFormatted = String.valueOf(amount.getAmount());
        TextView textView = viewHolder.parking_amount;
        textView.setText("KSH " + FormatAmount(amountFormatted));
        TextView textViewDescription = viewHolder.parking_description;
        textViewDescription.setText(amount.getDescription());
        viewHolder.rlLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return _payment.size();
    }

    public String FormatAmount(String value) {
        String[] formattedvalue = value.split("-");
        if ((value.contains("-") || value == "0.0")) {
            return formattedvalue[1];
        }
        return value;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView parking_description, parking_amount;
        RelativeLayout rlLayout;

        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            parking_description = itemView.findViewById(R.id.parking_description);
            parking_amount = itemView.findViewById(R.id.parking_amount);
            rlLayout = itemView.findViewById(R.id.rlLayout);
        }
    }
}
