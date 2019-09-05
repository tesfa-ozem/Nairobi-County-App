package com.aw.epayments.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aw.epayments.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {
    private List<ImageView> _menuImage;
    private List<String> _menuName;
    private LayoutInflater _mInflater;
    private ItemClickListener _mClickListener;
    private  List <Integer> _imageData;
    private boolean IsMain =true;
    public MenuAdapter(Context context, List <Integer> imageData, List<String> menuName){
        this._mInflater = LayoutInflater.from(context);
        _imageData = imageData;
        _menuName = menuName;
    }
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = _mInflater.inflate(R.layout.naipay_menu_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.myView.setImageResource(_imageData.get(position));
        holder.myTextView.setText(_menuName.get(position));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return _menuName.size();
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return _menuName.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this._mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);

    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView myView;
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myView = itemView.findViewById(R.id.menu_image);
            myTextView = itemView.findViewById(R.id.menu_text);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (_mClickListener != null) {
                _mClickListener.onItemClick(view, getAdapterPosition());

            }
        }
    }
}
