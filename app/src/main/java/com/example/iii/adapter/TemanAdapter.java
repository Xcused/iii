package com.example.iii.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iii.R;
import com.example.iii.database.Friends;

import java.util.ArrayList;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {

    private ArrayList<Friends> ListData;
    private TemanAdapterListener Listener;

    public TemanAdapter(ArrayList<Friends> listData, TemanAdapterListener listener ) {
        ListData = listData;
        Listener = listener;
    }
    @Override
    public TemanAdapter.TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View view = layoutInf.inflate(R.layout.row_data_teman,parent,false);
        return new TemanViewHolder(view);
    }
    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(TemanAdapter.TemanViewHolder holder, int position) {
        String nm,tlp;
        Friends data = ListData.get(position);
        nm = data.getNama();
        tlp = data.getTelpon();
        holder.namaTxt.setTextSize(20);
        holder.namaTxt.setText(nm);
        holder.telponTxt.setText(tlp);
    }

    @Override
    public int getItemCount() {
        return (ListData != null) ? ListData.size() : 0;
    }
    public class TemanViewHolder extends RecyclerView.ViewHolder{
        private CardView cardku;
        private TextView namaTxt , telponTxt;
        public TemanViewHolder(View view){
            super(view);
            cardku = (CardView) view.findViewById(R.id.kartuku);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            telponTxt = (TextView) view.findViewById(R.id.textTelpon);
            cardku.setOnClickListener(v->{
                Friends namaFriends = ListData.get(getAdapterPosition());
                Listener.onTemanSelected(v, namaFriends, getAdapterPosition());
            });
        }
    }

    public interface TemanAdapterListener {
        void onTemanSelected(View v, Friends friends, int pos);
    }
}

