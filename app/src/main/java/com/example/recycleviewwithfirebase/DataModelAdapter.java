package com.example.recycleviewwithfirebase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class DataModelAdapter extends RecyclerView.Adapter<DataModelAdapter.DataModelViewHolder> {

    private ArrayList<DataModel> dataset;

    public DataModelAdapter(ArrayList<DataModel> dataset) {
        this.dataset = dataset;
    }

    public static class DataModelViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView textViewName;
        TextView textViewYears;
        ImageView imageView;

        public DataModelViewHolder(@NonNull View itemView) {
            super(itemView);

            this.cardView = itemView.findViewById(R.id.cardViewCardPage);
            this.textViewName = itemView.findViewById(R.id.textViewNameCard);
            this.textViewYears = itemView.findViewById(R.id.textViewYearsCard);
            this.imageView = itemView.findViewById(R.id.imageViewCard);
        }
    }

    @NonNull
    @Override
    public DataModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);

        return new DataModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataModelViewHolder holder, int position) {
        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewYears;
        ImageView imageView = holder.imageView;
        CardView cardView = holder.cardView;

        textViewName.setText(dataset.get(position).getName());
        textViewVersion.setText(dataset.get(position).getYears());


        DbClient dbClient = DbClient.getInstance();

        dbClient
                .getImage(dataset.get(position).getImageUrl())
                .addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                        imageView.setImageBitmap(bmp);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(MyApplication.getAppContext(), "No Such file or Path found!!", Toast.LENGTH_LONG).show();
                    }
                });

        int p = position;
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("cardName", dataset.get(p));
                Navigation.findNavController(view).navigate(R.id.action_recycleViewFragment_to_detailsPage, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
