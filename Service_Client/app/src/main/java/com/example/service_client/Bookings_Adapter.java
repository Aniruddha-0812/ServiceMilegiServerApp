package com.example.service_client;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class Bookings_Adapter extends FirestoreRecyclerAdapter<Orders, Bookings_Adapter.Holder> {

    private  onItemClickListener listener;

    public Bookings_Adapter(@NonNull FirestoreRecyclerOptions<Orders> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, int position, @NonNull Orders model) {
        holder.job.setText(model.getJob());

        holder.status.setText("Pending");

        holder.name.setText(model.getFirst_name() + "\t" + model.getLast_name());

    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tile, parent, false);
        return new Holder(v);
    }
    class Holder extends RecyclerView.ViewHolder{

        TextView job, status ,name;

        public Holder(@NonNull View itemView) {
            super(itemView);

            job = itemView.findViewById(R.id.job);
            status = itemView.findViewById(R.id.status);
            name = itemView.findViewById(R.id.person);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    if(position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(getSnapshots().getSnapshot(position) , position);
                    }

                }
            });

        }

    }

    public interface  onItemClickListener{
        void onItemClick(DocumentSnapshot documentsnapshot , int position);
    }

    public  void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;

    }


}
