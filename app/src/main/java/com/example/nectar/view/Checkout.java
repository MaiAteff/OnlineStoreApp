package com.example.nectar.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.nectar.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class Checkout extends BottomSheetDialogFragment {
    TextView totalCheckout;
    String total = "";
    Context context;

    public Checkout(String totalCheckout, Context context) {
        this.total = totalCheckout;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.checkout,
                container, false);

        ImageView close = v.findViewById(R.id.close);
        ImageView placeOrder = v.findViewById(R.id.placeOrder);
        totalCheckout = v.findViewById(R.id.total);
        totalCheckout.setText(total);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                dismiss();
            }
        });

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(context, OrderAccepted.class);
                startActivity(intent);
                HomePage.cartData.clear();
                dismiss();
            }
        });

        return v;
    }
}
