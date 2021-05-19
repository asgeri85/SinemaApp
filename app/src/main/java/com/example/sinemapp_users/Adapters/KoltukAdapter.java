package com.example.sinemapp_users.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sinemapp_users.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.List;

public class KoltukAdapter extends RecyclerView.Adapter<KoltukAdapter.KoltukTutucu> {
    private List<String>koltujList;
    private Context mContext;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private String ad,bilet;

    public KoltukAdapter(List<String> koltujList, Context mContext,String ad) {
        this.koltujList = koltujList;
        this.mContext = mContext;
        this.ad=ad;
        database= FirebaseDatabase.getInstance();
        reference=database.getReference();
    }

    @NonNull
    @Override
    public KoltukTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.koltuk_card,parent,false);
        return new KoltukTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KoltukTutucu holder, int position) {
        holder.textView.setText(koltujList.get(position));
        reference.child("Koltuklar").child(ad).child(koltujList.get(position)).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
             bilet=snapshot.getValue().toString();

               if (!bilet.equals("")){
                   holder.imageView.setImageResource(R.drawable.dolukoltuk);
               }else{
                   holder.imageView.setImageResource(R.drawable.boskoltuk);
               }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("Koltuklar").child(ad).child(koltujList.get(position)).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        bilet=snapshot.getValue().toString();

                        if (!bilet.equals("")){
                           Toast.makeText(mContext,"Secilen yer dolu",Toast.LENGTH_LONG).show();
                        }else{
                            alertAc(ad,koltujList.get(position));
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return koltujList.size();
    }

    public class KoltukTutucu extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ImageView imageView;
        private TextView textView;
        public KoltukTutucu(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardKoltuk);
            imageView=itemView.findViewById(R.id.imageViewKoltuk);
            textView=itemView.findViewById(R.id.koltukNo);
        }
    }

    public void alertAc(String ad,String koltk){
        View alert=LayoutInflater.from(mContext).inflate(R.layout.alertdialog,null);
        EditText editText=alert.findViewById(R.id.editAlert);

        AlertDialog.Builder dialog=new AlertDialog.Builder(mContext);
        dialog.setView(alert);
        dialog.setCancelable(true);
        dialog.setPositiveButton("Bilet Al", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                reference.child("Koltuklar").child(ad).child(koltk).child("biletAlan").setValue(editText.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(mContext,"Bilet alındı",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

       dialog.create().show();
    }
}
