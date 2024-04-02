package com.mirea.coffeeshop;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.mirea.coffeeshop.adapters.CartAdapter;
import com.mirea.coffeeshop.data_classes.Product;
import com.mirea.coffeeshop.databinding.ActivityCartBinding;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity
        implements CartAdapter.OnItemCheckListener {

    private final String TAG = "CartActivity";

    private ActivityCartBinding binding;

    CartAdapter adapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCartBinding.inflate(getLayoutInflater());

        ArrayList<Product> checkedProducts = getIntent().getParcelableArrayListExtra("checkedProducts");

        adapter = new CartAdapter(this, checkedProducts, this);
        binding.listViewCart.setAdapter(adapter);

        int totalAmount = 0;

        assert checkedProducts != null;
        for (Product product : checkedProducts) {
            totalAmount += product.getPrice();
        }

        binding.cartTotalAmount.setText("Total Amount: " + totalAmount);

        setContentView(binding.getRoot());
    }

    @Override
    public void onItemCheckChanged() {

    }
}
