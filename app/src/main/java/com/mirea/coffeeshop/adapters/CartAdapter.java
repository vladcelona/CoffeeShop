package com.mirea.coffeeshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.mirea.coffeeshop.R;
import com.mirea.coffeeshop.data_classes.Product;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> itemsList;
    private LayoutInflater inflater;


    private OnItemCheckListener onItemCheckListener;

    // Интерфейс для обратной связи
    public interface OnItemCheckListener {
        void onItemCheckChanged();
    }

    public CartAdapter(
            Context context, ArrayList<Product> itemsList,
            OnItemCheckListener onItemCheckListener
    ) {
        this.context = context;
        this.itemsList = itemsList;
        this.onItemCheckListener = onItemCheckListener;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return itemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itemsList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.product_item, null);
        TextView name = convertView.findViewById(R.id.product_name);
        TextView price = convertView.findViewById(R.id.product_price);
        CheckBox checkBox = convertView.findViewById(R.id.product_checkbox);
        ImageView imageView = convertView.findViewById(R.id.product_image);

        Product item = itemsList.get(position);
        name.setText(item.getName());
        price.setText(String.valueOf(item.getPrice()));
        checkBox.setChecked(item.isChecked());
        imageView.setImageResource(item.getImageResId());

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            item.setChecked(isChecked);
            if (onItemCheckListener != null) {
                onItemCheckListener.onItemCheckChanged();
            }
        });

        return convertView;
    }

    public ArrayList<Product> getCheckedItems() {
        ArrayList<Product> checkedItems = new ArrayList<>();
        for (Product item : itemsList) {
            if (item.isChecked()) {
                checkedItems.add(item);
            }
        }
        return checkedItems;
    }
}
