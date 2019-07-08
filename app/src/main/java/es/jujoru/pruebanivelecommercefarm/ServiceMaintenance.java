package es.jujoru.pruebanivelecommercefarm;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


public class ServiceMaintenance extends IntentService {

    public FirebaseDatabase firebaseDatabase;
    public DatabaseReference databaseReference;
    public ValueEventListener valueEventListener;
    private static final String TAG_ERROR_DATABASE="ERROR DATABASE FIREBASE";
    public ServiceMaintenance() {
        super("ServiceMaintenance");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        loadFirebaseDatabase();
    }


    private void loadFirebaseDatabase(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("status");

        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshotStatus: dataSnapshot.getChildren()) {
                    long maintenance = dataSnapshotStatus.getValue(Long.class);

                    if(maintenance==1){
                        Intent i=new Intent(getApplicationContext(), MaintenanceActivity.class );
                        startActivity(i);
                    }
                }


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG_ERROR_DATABASE,"DATABASE ERROR");
            }
        };
        databaseReference.addValueEventListener(valueEventListener);


    }


}
