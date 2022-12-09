package com.example.newsx;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsx.Listeners.SelectListener;
import com.example.newsx.Models.DatabaseHelper;
import com.example.newsx.Models.Headline;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterSeeLater extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<Headline> list;

    public AdapterSeeLater(Context context, List<Headline> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headlines_list_container,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.text_title.setText(list.get(position).title);
        if (list.get(position).image!=null){
            Picasso.get().load(list.get(position).image).into(holder.img_headline);
        }
        holder.text_author.setText(list.get(position).publishedat);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("data", (CharSequence) list.get(position));
                v.getContext().startActivity(intent);
            }
        });
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
                databaseHelper.headlineDao().delete(list.get(position));
                Toast.makeText(context, "Berhasil Di Hapus dari Favorit", Toast.LENGTH_SHORT).show();
           }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
