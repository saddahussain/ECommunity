package com.example.ecommunity.Education_Categories;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ecommunity.R;
import com.example.ecommunity.SubmitComplaints_Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class PrivateSchools_Categories extends AppCompatActivity {
    ListView listView;
    ArrayAdapter<String> arrayAdapter;
    String[] listitems = {"Illegal Schools","Teachers Wages","Ill Treatment with Students","Fee / Dues", "General Complaints","Corruption"};
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private String savecuurentdate,savecuurenttime,randomkey;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_schools__categories);
        // Database Connection
        String userID = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("App").child("UserData").child(userID).child("Complaint Details");
        listView = (ListView) findViewById(R.id.lv_PrivateSchools);
        arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listitems);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat currentdate =  new SimpleDateFormat("MMM dd, yyy");
                savecuurentdate = currentdate.format(calendar.getTime());
                SimpleDateFormat currenttime =  new SimpleDateFormat("HH:mm:ss a");
                savecuurenttime = currenttime .format(calendar.getTime());
                randomkey = savecuurentdate +savecuurenttime;

                final DatabaseReference newPost = databaseReference.child(randomkey);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (position == 0) {
                            newPost.child("ComplaintCategory").setValue("Illegal Schools");

                            Intent intent = new Intent(PrivateSchools_Categories.this, SubmitComplaints_Activity.class);
                            startActivity(intent);
                        }
                        if (position == 1) {
                            newPost.child("ComplaintCategory").setValue("Teachers Wages");

                            Intent intent = new Intent(PrivateSchools_Categories.this, SubmitComplaints_Activity.class);
                            startActivity(intent);
                        }
                        if (position == 2) {
                            newPost.child("ComplaintCategory").setValue("Ill Treatment with Students");

                            Intent intent = new Intent(PrivateSchools_Categories.this, SubmitComplaints_Activity.class);
                            startActivity(intent);
                        }
                        if (position == 3) {
                            newPost.child("ComplaintCategory").setValue("Fee / Dues");

                            Intent intent = new Intent(PrivateSchools_Categories.this, SubmitComplaints_Activity.class);
                            startActivity(intent);
                        }
                        if (position == 4) {
                            newPost.child("ComplaintCategory").setValue("General Complaints");

                            Intent intent = new Intent(PrivateSchools_Categories.this, SubmitComplaints_Activity.class);
                            startActivity(intent);
                        }
                        if (position == 5) {
                            newPost.child("ComplaintCategory").setValue("Corruption");

                            Intent intent = new Intent(PrivateSchools_Categories.this, SubmitComplaints_Activity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
    }
}
