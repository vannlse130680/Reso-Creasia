package com.creasia.ui.outlet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.creasia.R;

import java.util.ArrayList;

public class OutletAdapter extends ArrayAdapter<Outlets> {
    private Context context;
    private ArrayList<Outlets> items;
    private int layout_id;
    public OutletAdapter(Context context, int layout_id, ArrayList<Outlets> items) {
        super(context, layout_id, items);
        this.context = context;
        this.items = items;
        this.layout_id = layout_id;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layout_id, null);

        }
        Outlets item_current = items.get(position);

        ImageView view = convertView.findViewById(R.id.item_icon_locate);
        Glide.with(context)
                .load(item_current.getImage())
                .centerCrop()
                .into(view);
        TextView txtLocationName = convertView.findViewById(R.id.item_txtLocationName);
        TextView txtCode = convertView.findViewById(R.id.item_txtCode);
        TextView txtOutletName = convertView.findViewById(R.id.item_txtOutletName);
        TextView txtTime = convertView.findViewById(R.id.item_txtTime);
        TextView txtLocation = convertView.findViewById(R.id.item_txtLocate);
        TextView txtStatus = convertView.findViewById(R.id.item_txtStatus);

        if(txtLocationName != null && txtCode != null && txtOutletName != null && txtTime != null && txtStatus!=null && txtLocation != null){
            txtLocationName.setText(item_current.getLocationName());
            txtCode.setText(item_current.getCode());
            txtOutletName.setText(item_current.getOutletName());
            txtTime.setText(item_current.getTimeAccess());
            txtLocation.setText(item_current.getLocation());
            txtStatus.setText(item_current.getStatus());
        }

        return convertView;
    }

}
