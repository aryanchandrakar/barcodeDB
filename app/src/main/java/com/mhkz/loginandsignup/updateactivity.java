package com.mhkz.loginandsignup;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

//scanning equipment number

public class updateactivity extends AppCompatActivity {
    Button updater;

    String p;
    EditText status;
    private TextView textview;
    DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updater);
        status = findViewById(R.id.status);
        updater= findViewById(R.id.updater);
        textview = findViewById(R.id.textview);
        updater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickupdate();
            }
        });
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
    }
    public void ScanButton(View view){
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        final String e = intentResult.getContents();
        final long t= Long. parseLong(e);
        p = e;

        if (intentResult != null){
            if (intentResult.getContents() == null){
                textview.setText("Nothing to update or Connection issue!");
            }else {
                /////////////////////////////////////////////////////////////

                mdatabase.child("barcodedb").orderByChild("Equipment").equalTo(t).addListenerForSingleValueEvent(new ValueEventListener()
                {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                                //extracting data from database
                                String Equipment= postSnapshot.child("Equipment").getValue().toString();
                                String Description= postSnapshot.child("Description").getValue().toString();
                                String ManufSerialNumber= postSnapshot.child("ManufSerialNumber").getValue().toString();
                                String Manufacturer= postSnapshot.child("Manufacturer").getValue().toString();
                                String CPU= postSnapshot.child("Category").getValue().toString();
                                String Location= postSnapshot.child("Location").getValue().toString();
                                String TechIdentNumber= postSnapshot.child("TechIdentNumber").getValue().toString();
                                String ModelNumber= postSnapshot.child("ModelNumber").getValue().toString();
                                String SortField= postSnapshot.child("SortField").getValue().toString();
                                String ObjectType= postSnapshot.child("ObjectType").getValue().toString();
                                String AMCWarranty= postSnapshot.child("AMC-Warranty").getValue().toString();
                                String AMCWarrantyVendor= postSnapshot.child("AMCWarrantyVendor").getValue().toString();
                                String EmployeeeID= postSnapshot.child("EmployeeeID").getValue().toString();
                                String EmailID= postSnapshot.child("E-mailID").getValue().toString();
                                String PhyVerifDate= postSnapshot.child("PhyVerifDate").getValue().toString();
                                String PhyVerifDoneBy= postSnapshot.child("PhyVerifDoneBy").getValue().toString();
                                String Userstatus= postSnapshot.child("UserStatus").getValue().toString();
                                String Remarks= postSnapshot.child("Remarks").getValue().toString();
                                String Status= postSnapshot.child("Status").getValue().toString();
                                //view data in the text field
                                textview.setText("Equipment: "+Equipment+"\n");
                                textview.append("Description: "+Description+"\n");
                                textview.append("Manuf. Serial Number: "+ManufSerialNumber+"\n");
                                textview.append("Manufacturer: "+Manufacturer+"\n");
                                textview.append("Category: "+CPU+"\n");
                                textview.append("Location: "+Location+"\n");
                                textview.append("Tech. Iden. No.: "+TechIdentNumber+"\n");
                                textview.append("Model Number: "+ModelNumber+"\n");
                                textview.append("Sort Field: "+SortField+"\n");
                                textview.append("Object Type: "+ObjectType+"\n");
                                textview.append("AMC-Warranty: "+AMCWarranty+"\n");
                                textview.append("AMC-Warranty Vendor: "+AMCWarrantyVendor+"\n");
                                textview.append("Employeee ID: "+EmployeeeID+"\n");
                                textview.append("E-mail ID: "+EmailID+"\n");
                                textview.append("Phy Verif Date: "+PhyVerifDate+"\n");
                                textview.append("Phy Verif Done By: "+PhyVerifDoneBy+"\n");
                                textview.append("User status: "+Userstatus+"\n");
                                textview.append("Remarks: "+Remarks+"\n");
                                textview.append("Status: "+Status+"\n");

                            }}
                        else{
                            String s= e;
                            textview.append(s + "\n Had no match in database!!");}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        textview.append("The read failed: " + databaseError.getCode());
                    }
                });
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void clickupdate() {
        final long z= Long. parseLong(p);
            if (p == null){
                textview.append("Nothing to update or Connection issue!");
            }else {
                textview.setText("");

                mdatabase.child("barcodedb").orderByChild("Equipment").equalTo(z).addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        if(dataSnapshot.exists()){
                            String s= status.getText().toString();
                            for (DataSnapshot ps: dataSnapshot.getChildren()) {
                                ps.child("Status").getRef().setValue(s);}
                            }
                        Toast.makeText(updateactivity.this, "update success", Toast.LENGTH_SHORT).show();

                        if(dataSnapshot.exists()){
                            for (DataSnapshot postSnapshot: dataSnapshot.getChildren()){

                                //extracting data from database
                                String Equipment= postSnapshot.child("Equipment").getValue().toString();
                                String Description= postSnapshot.child("Description").getValue().toString();
                                String ManufSerialNumber= postSnapshot.child("ManufSerialNumber").getValue().toString();
                                String Manufacturer= postSnapshot.child("Manufacturer").getValue().toString();
                                String CPU= postSnapshot.child("Category").getValue().toString();
                                String Location= postSnapshot.child("Location").getValue().toString();
                                String TechIdentNumber= postSnapshot.child("TechIdentNumber").getValue().toString();
                                String ModelNumber= postSnapshot.child("ModelNumber").getValue().toString();
                                String SortField= postSnapshot.child("SortField").getValue().toString();
                                String ObjectType= postSnapshot.child("ObjectType").getValue().toString();
                                String AMCWarranty= postSnapshot.child("AMC-Warranty").getValue().toString();
                                String AMCWarrantyVendor= postSnapshot.child("AMCWarrantyVendor").getValue().toString();
                                String EmployeeeID= postSnapshot.child("EmployeeeID").getValue().toString();
                                String EmailID= postSnapshot.child("E-mailID").getValue().toString();
                                String PhyVerifDate= postSnapshot.child("PhyVerifDate").getValue().toString();
                                String PhyVerifDoneBy= postSnapshot.child("PhyVerifDoneBy").getValue().toString();
                                String Userstatus= postSnapshot.child("UserStatus").getValue().toString();
                                String Remarks= postSnapshot.child("Remarks").getValue().toString();
                                String Status= postSnapshot.child("Status").getValue().toString();
                                //view data in the text field
                                textview.setText("Equipment: "+Equipment+"\n");
                                textview.append("Description: "+Description+"\n");
                                textview.append("Manuf. Serial Number: "+ManufSerialNumber+"\n");
                                textview.append("Manufacturer: "+Manufacturer+"\n");
                                textview.append("Category: "+CPU+"\n");
                                textview.append("Location: "+Location+"\n");
                                textview.append("Tech. Iden. No.: "+TechIdentNumber+"\n");
                                textview.append("Model Number: "+ModelNumber+"\n");
                                textview.append("Sort Field: "+SortField+"\n");
                                textview.append("Object Type: "+ObjectType+"\n");
                                textview.append("AMC-Warranty: "+AMCWarranty+"\n");
                                textview.append("AMC-Warranty Vendor: "+AMCWarrantyVendor+"\n");
                                textview.append("Employeee ID: "+EmployeeeID+"\n");
                                textview.append("E-mail ID: "+EmailID+"\n");
                                textview.append("Phy Verif Date: "+PhyVerifDate+"\n");
                                textview.append("Phy Verif Done By: "+PhyVerifDoneBy+"\n");
                                textview.append("User status: "+Userstatus+"\n");
                                textview.append("Remarks: "+Remarks+"\n");
                                textview.append("Status: "+Status+"\n");
                            }
                        }
                        else{
                            textview.append(p + "\n Had no match in database!!");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        textview.append("The update failed: " + databaseError.getCode());
                    }
                });

            }


    }
}
