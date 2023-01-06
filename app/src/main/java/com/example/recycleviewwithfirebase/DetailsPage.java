package com.example.recycleviewwithfirebase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsPage#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsPage extends Fragment {

    private static final String ARG_PARAM1 = "cardName";

    private DataModel dataModel;

    public DetailsPage() {
    }

    public static DetailsPage newInstance(DataModel param1) {
        DetailsPage fragment = new DetailsPage();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            dataModel = (DataModel) getArguments().get(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_page, container, false);

        TextView textViewDescription = view.findViewById(R.id.TextViewDescription);
        TextView textViewName = view.findViewById(R.id.TextViewName);
        TextView textViewYears = view.findViewById(R.id.TextViewYears);
        ImageView imageView = view.findViewById(R.id.ImageViewDetailsPage);

        textViewName.setText(dataModel.getName());
        textViewYears.setText(dataModel.getYears());
        textViewDescription.setText(dataModel.getDescription());

        DbClient dbClient = DbClient.getInstance();

        dbClient
                .getImage(dataModel.getImageUrl())
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

        return view;
    }
}