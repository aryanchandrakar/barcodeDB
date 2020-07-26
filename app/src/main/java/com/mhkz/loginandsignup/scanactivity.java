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

//scanning manufacture serial number

public class scanactivity extends AppCompatActivity {


    private TextView textView;
    String x;
    EditText sTatus;
    Button updateit;
    DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);
        sTatus = findViewById(R.id.sTatus);
        updateit= findViewById(R.id.updateit);
        textView = findViewById(R.id.textView);
        updateit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickupdateit();
            }
        });
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PackageManager.PERMISSION_GRANTED);
    }



    public void ScanButton(View view){
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        final String r = intentResult.getContents();
        x=r;
        if (intentResult != null){
            if (intentResult.getContents() == null){
                textView.setText("Nothing to update or Connection issue!");
            }else {
                /////////////////////////////////////////////////////////////

                mdatabase.child("barcodedb").orderByChild("ManufSerialNumber").equalTo(r).addListenerForSingleValueEvent(new ValueEventListener()
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
                                textView.setText("Equipment: "+Equipment+"\n");
                                textView.append("Description: "+Description+"\n");
                                textView.append("Manuf. Serial Number: "+ManufSerialNumber+"\n");
                                textView.append("Manufacturer: "+Manufacturer+"\n");
                                textView.append("Category: "+CPU+"\n");
                                textView.append("Location: "+Location+"\n");
                                textView.append("Tech. Iden. No.: "+TechIdentNumber+"\n");
                                textView.append("Model Number: "+ModelNumber+"\n");
                                textView.append("Sort Field: "+SortField+"\n");
                                textView.append("Object Type: "+ObjectType+"\n");
                                textView.append("AMC-Warranty: "+AMCWarranty+"\n");
                                textView.append("AMC-Warranty Vendor: "+AMCWarrantyVendor+"\n");
                                textView.append("Employeee ID: "+EmployeeeID+"\n");
                                textView.append("E-mail ID: "+EmailID+"\n");
                                textView.append("Phy Verif Date: "+PhyVerifDate+"\n");
                                textView.append("Phy Verif Done By: "+PhyVerifDoneBy+"\n");
                                textView.append("User status: "+Userstatus+"\n");
                                textView.append("Remarks: "+Remarks+"\n");
                                textView.append("Status: "+Status+"\n");

                            }}
                        else{
                            String s= r;
                            textView.append(s + "\n Had no match in database!!");}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        textView.append("The read failed: " + databaseError.getCode());

                    }
                });
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected void clickupdateit() {
        //final long y= Long. parseLong(x);
        if (x == null){
            textView.append("Nothing to update or Connection issue!");
        }else {
            textView.setText("");


            mdatabase.child("barcodedb").orderByChild("ManufSerialNumber").equalTo(x).addValueEventListener(new ValueEventListener(){
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    if(dataSnapshot.exists()){
                        String b= sTatus.getText().toString();
                        for (DataSnapshot ps: dataSnapshot.getChildren()) {
                            ps.child("Status").getRef().setValue(b);}
                    }
                    Toast.makeText(scanactivity.this, "update success", Toast.LENGTH_SHORT).show();

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
                            textView.setText("Equipment: "+Equipment+"\n");
                            textView.append("Description: "+Description+"\n");
                            textView.append("Manuf. Serial Number: "+ManufSerialNumber+"\n");
                            textView.append("Manufacturer: "+Manufacturer+"\n");
                            textView.append("Category: "+CPU+"\n");
                            textView.append("Location: "+Location+"\n");
                            textView.append("Tech. Iden. No.: "+TechIdentNumber+"\n");
                            textView.append("Model Number: "+ModelNumber+"\n");
                            textView.append("Sort Field: "+SortField+"\n");
                            textView.append("Object Type: "+ObjectType+"\n");
                            textView.append("AMC-Warranty: "+AMCWarranty+"\n");
                            textView.append("AMC-Warranty Vendor: "+AMCWarrantyVendor+"\n");
                            textView.append("Employeee ID: "+EmployeeeID+"\n");
                            textView.append("E-mail ID: "+EmailID+"\n");
                            textView.append("Phy Verif Date: "+PhyVerifDate+"\n");
                            textView.append("Phy Verif Done By: "+PhyVerifDoneBy+"\n");
                            textView.append("User status: "+Userstatus+"\n");
                            textView.append("Remarks: "+Remarks+"\n");
                            textView.append("Status: "+Status+"\n");
                        }
                    }
                    else{
                        textView.append(x + "\n Had no match in database!!");
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    textView.append("The update failed: " + databaseError.getCode());

                }
            });

        }


    }


}

