package com.example.recycleviewwithfirebase;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class DbClient {

    private static DbClient instance;
    private FirebaseDatabase database;
    private FirebaseStorage storage;
    private final String USERS_COLLECTION_ID = "figures";

    private DbClient() {
        this.database = FirebaseDatabase.getInstance();
        this.storage = FirebaseStorage.getInstance();
    }

    public static DbClient getInstance() {
        if (instance == null) {
            instance = new DbClient();
        }
        return instance;
    }

    public Query getData() {
        DatabaseReference myRef = database.getReference(USERS_COLLECTION_ID);
        ArrayList<DataModel> dataModels = new ArrayList<>();

        return myRef.orderByChild("_id");

    }

    public com.google.android.gms.tasks.Task<byte[]> getImage(String storageLocation) {
        StorageReference gsReference = storage.getReferenceFromUrl(storageLocation);

        return gsReference.getBytes(1024*1024*5);
    }
}
