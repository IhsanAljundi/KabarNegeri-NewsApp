package com.example.newsx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsx.Listeners.SelectListener;
import com.example.newsx.Models.DatabaseHelper;
import com.example.newsx.Models.Headline;
import com.example.newsx.Models.NewsHeadline;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<NewsHeadline> headlineList;
    private SelectListener listener;

    public CustomAdapter(Context context, List<NewsHeadline> headlineList, SelectListener listener) {
        this.context = context;
        this.headlineList = headlineList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.headlines_list_container, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.text_title.setText(headlineList.get(position).getTitle());
        if (headlineList.get(position).getUrlToImage()!=null){
            Picasso.get().load(headlineList.get(position).getUrlToImage()).into(holder.img_headline);
        }
        holder.text_author.setText(headlineList.get(position).getSource().getName());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnNewsClicked(headlineList.get(position));
            }
        });
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = DatabaseHelper.getInstance(context);
                Headline headline = new Headline();
                headline.title = headlineList.get(position).getTitle();
                headline.image = headlineList.get(position).getUrlToImage();
                headline.publishedat = headlineList.get(position).getPublishedAt();
                headline.content = headlineList.get(position).getContent();
                databaseHelper.headlineDao().insert(headline);
                Toast.makeText(context, "Berhasil Di Favoritkan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return headlineList.size();
    }
}
