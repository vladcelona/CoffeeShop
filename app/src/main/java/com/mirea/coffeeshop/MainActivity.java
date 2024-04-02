package com.mirea.coffeeshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.mirea.coffeeshop.adapters.CartAdapter;
import com.mirea.coffeeshop.data_classes.Product;
import com.mirea.coffeeshop.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements CartAdapter.OnItemCheckListener {

    private final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private View footerView;

    CartAdapter adapter;
    ArrayList<Product> productsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        footerView = getLayoutInflater().inflate(R.layout.footer, null);
        binding.listViewMain.addFooterView(footerView);

        initializeItemsList();
        adapter = new CartAdapter(this, productsList, this);
        binding.listViewMain.setAdapter(adapter);

        ((Button) footerView.findViewById(R.id.show_cart_button)).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CartActivity.class);
            intent.putParcelableArrayListExtra("checkedProducts", adapter.getCheckedItems());
            startActivity(intent);
        });

        setContentView(binding.getRoot());
    }

    @Override
    public void onItemCheckChanged() {
        int checksCount = 0;
        for (Product product : productsList) {
            if (product.isChecked()) { checksCount++; }
        }
        ((TextView) footerView.findViewById(R.id.footer_checked_items))
                .setText("Checked items: " + checksCount);
    }

    private void initializeItemsList() {
        productsList.add(new Product(1, "Эспрессо", 150, false, R.drawable.espresso));
        productsList.add(new Product(2, "Капучино", 180, false, R.drawable.cappuccino));
        productsList.add(new Product(3, "Латте", 200, false, R.drawable.latte));
        productsList.add(new Product(4, "Американо", 160, false, R.drawable.americano));
        productsList.add(new Product(5, "Макиато", 190, false, R.drawable.macchiato));
        productsList.add(new Product(6, "Флэт Уайт", 210, false, R.drawable.flat_white));
        productsList.add(new Product(7, "Мокка", 220, false, R.drawable.mocha));
        productsList.add(new Product(8, "Айриш Кофе", 250, false, R.drawable.irish_coffee));
        productsList.add(new Product(9, "Фраппучино", 230, false, R.drawable.frappuccino));
        productsList.add(new Product(10, "Глясе", 240, false, R.drawable.glace));
        // Добавьте другие фрукты аналогично
    }
}
