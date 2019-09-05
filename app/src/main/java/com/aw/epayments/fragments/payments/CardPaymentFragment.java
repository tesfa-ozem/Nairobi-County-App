package com.aw.epayments.fragments.payments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aw.epayments.R;
import com.aw.epayments.activity.HouseRentReciept;

public class CardPaymentFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_card_payment, container, false);
        return view;
    }
}
