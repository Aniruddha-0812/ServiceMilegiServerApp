package com.example.service_client;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {

    private TextView name , status , time ,job , address , mobile;
    private Button complete;
    private FirebaseFirestore db;
    private  String order_id , user_id , first_name , last_name , addressS ,  jobS  , mob ,stat , time1 ;
    private Timestamp timestamp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        db = FirebaseFirestore.getInstance();
        name =  findViewById(R.id.name);
        status= findViewById(R.id.status);
        time = findViewById(R.id.time);
        job = findViewById(R.id.work);
        address = findViewById(R.id.address);
        mobile = findViewById(R.id.mobile);


        complete = findViewById(R.id.mark_complete);


        first_name = intent.getStringExtra("first_name");
        last_name = intent.getStringExtra("last_name");
        stat = intent.getStringExtra("status");
        time1 = intent.getStringExtra("time");
        jobS = intent.getStringExtra("job");
        addressS = intent.getStringExtra("address");
        mob = intent.getStringExtra("mobile");




        user_id = intent.getStringExtra("user_id");
        order_id = intent.getStringExtra("order_id");
        name.setText(first_name+ "\t"+ last_name);
        status.setText(stat);
        time.setText(time1);
        job.setText(jobS);
        address.setText(addressS);
        mobile.setText(mob);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you sure ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                UpdaterUser();
                            }
                        }).setNegativeButton("No", null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }



    private void UpdaterUser() {
    DocumentReference query =  db.collection("Users").document(user_id).collection("Orders").document(order_id);

        query.update("is_complete" , true)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(DetailsActivity.this, "Marked as Completed Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DetailsActivity.this , MainActivity.class));
                        }
                        else{
                            Toast.makeText(DetailsActivity.this, "aai ghal", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



}

