package com.xtilyna.booksbay.test.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.xtilyna.booksbay.test.R;
import com.xtilyna.booksbay.test.entities.Photo;

import java.util.ArrayList;


public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.imgview_photo);

        }
    }

    private Context context;

    private ArrayList<Photo> data;

    private LayoutInflater inflater;

    private int previousPosition = 0;

    public CustomAdapter(Context context, ArrayList<Photo> data) {

        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int position) {

        View view = inflater.inflate(R.layout.list_item_img, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, final int position) {
        Picasso.get().setLoggingEnabled(true);
        Picasso.get().load(data.get(position).getUrl()).fit().into(myViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}