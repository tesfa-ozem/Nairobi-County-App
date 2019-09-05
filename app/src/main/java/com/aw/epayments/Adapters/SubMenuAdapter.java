package com.aw.epayments.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aw.epayments.R;

import java.util.List;

public class SubMenuAdapter extends RecyclerView.Adapter<SubMenuAdapter.ViewHolder>{
    private List<String> _menuName;
    private LayoutInflater _mInflater;
    private ItemClickListener _mClickListener;

    public SubMenuAdapter(Context context,  List<String> menuName){
        this._mInflater = LayoutInflater.from(context);

        _menuName = menuName;
    }
    @Override
    @NonNull
    public SubMenuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = _mInflater.inflate(R.layout.sub_streams_items, parent, false);
        return new SubMenuAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SubMenuAdapter.ViewHolder holder, int position) {

        holder.myTextView.setText(_menuName.get(position));
    }
    @Override
    public int getItemCount() {
        return _menuName.size();
    }

    public String getItem(int id) {
        return _menuName.get(id);
    }

    public void setClickListener(MenuAdapter.ItemClickListener itemClickListener) {
        this._mClickListener = (ItemClickListener) itemClickListener;
    }


    public interface ItemClickListener {
        void onSubItemClick(View view, int position, Boolean isMain);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);

            //myTextView = itemView.findViewById(R.id.sub_stream_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (_mClickListener != null)
                _mClickListener.onSubItemClick(view, getAdapterPosition(), false);
        }
    }
}
