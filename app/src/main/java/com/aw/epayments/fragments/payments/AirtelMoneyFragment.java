package com.aw.epayments.fragments.payments;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aw.epayments.R;

import java.util.List;

public class AirtelMoneyFragment extends Fragment {
    View view;
    TextView instruction;
    List<String> x;
    String[] array = {};
    String ref = "";
    private String paybill = String.format("Paying your Bill with Airtel Money (Airtel)\n" +
            "          1. Select \"Airtel Money\" from the menu.\n" +
            "          2. Click on \"Make Payments\".\n" +
            "          3. Select \"Paybill\" %1$s.\n" +
            "          4. Enter %2$s as Account.\n" +
            "          5. Choose Nairobi County.\n" +
            "          6. Enter your Airtel Money PIN.\n" +
            "          5. Under the reference option,type your full account number\n" +
            "          7.Wait for SMS confirmation of payment.\n" +
            "           \n", "", "");

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_airtel, container, false);
        /*Api.getVolley(getContext(), Api.POST, Api.GetPaymentInstructions, "", new Api.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                PaymentInstructions response = Api.mGson.fromJson(result, PaymentInstructions.class);

                x = response.getResponseData();
                //paybill = x.get(2);


            }
        });*/

        instruction = view.findViewById(R.id.paymentInstractions);
        instruction.setText("Coming Soon");
        return view;
    }
}
