package com.example.service_client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentId;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private Bookings_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        Query query = db.collectionGroup("Orders").whereEqualTo("is_complete" ,false).orderBy("messageTime", Query.Direction.DESCENDING);



        FirestoreRecyclerOptions<Orders> options = new FirestoreRecyclerOptions.Builder<Orders>()
                .setQuery(query, Orders.class)
                .build();

        adapter = new Bookings_Adapter(options);

        recyclerView = findViewById(R.id.firestore_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        adapter.setOnItemClickListener(new Bookings_Adapter.onItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentsnapshot, int position) {
                Orders orders = documentsnapshot.toObject(Orders.class);
                String documentId = documentsnapshot.getId();
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                if (orders != null) {

                    Timestamp timestamp = orders.getMessageTime();

                    String com;
                    if(orders.isIs_complete())
                    {
                        com = "Completed";
                    }
                    else{
                        com = "Pending";
                    }

                    intent.putExtra("user_id" , orders.getUser_id());
                    intent.putExtra("order_id", documentId);
                    intent.putExtra("first_name", orders.getFirst_name());
                    intent.putExtra("last_name", orders.getLast_name());
                    intent.putExtra("address", orders.getAddress());
                    intent.putExtra("status", com);
                    intent.putExtra("time",timestamp.toDate().toString());
                    intent.putExtra("job", orders.getJob());
                    intent.putExtra("mobile", orders.getMobile());

                }
                startActivity(intent);
            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (adapter != null) {
            adapter.stopListening();
        }
    }


}