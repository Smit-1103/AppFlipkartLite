package com.example.appflipkartlite;

import androidx.annotation.NonNull;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class AddGrocery extends Activity {
    EditText etAddGName,etAddGPrice,etAddGStock,etAddGUnit;
    Button btAddImage,btAddGrocery;
    ImageView imageView,imgGrocery;
    ImageButton btback;
    Uri imgPath;
    String gName,gUnit;
    int gPrice,gStock;
    DatabaseReference dbRef=null;
    StorageReference storeImg=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grocery);

//        getSupportActionBar().hide();

        etAddGName = findViewById(R.id.etGroceryAdminAddName);
        etAddGPrice = findViewById(R.id.etGroceryAdminAddPrice);
        etAddGStock = findViewById(R.id.etGroceryAdminAddStock);
        etAddGUnit = findViewById(R.id.etGroceryAdminAddUnit);

        btAddImage = findViewById(R.id.btGroceryAdminAddImage);
        btAddGrocery = findViewById(R.id.btGroceryAdminAddItem);
        imgGrocery = findViewById(R.id.imgGrocery);
        btback = findViewById(R.id.btback);


        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AdminHome.class);
                startActivity(intent);
            }
        });
        btAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI); //add imgae par click kare to image add karva mate
                startActivityForResult(ii,121);
            }
        });

        btAddGrocery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gName = etAddGName.getText().toString();
                gPrice = Integer.parseInt(etAddGPrice.getText().toString());
                gStock = Integer.parseInt(etAddGStock.getText().toString());
                gUnit = etAddGUnit.getText().toString();

                dbRef = FirebaseDatabase.getInstance().getReference("grocery");
                storeImg = FirebaseStorage.getInstance().getReferenceFromUrl("gs://appflipkartlite.appspot.com/grocery");

                final StorageReference imgPhoto = storeImg.child(gName); // je name add kariye a j name a img ave thay
                final String gId = dbRef.push().getKey();

                imgPhoto.putFile(imgPath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        //photo ni link leva mate
                        imgPhoto.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                Grocery gr = new Grocery(gId,uri.toString(),gName,gPrice,gStock,gUnit);   //record
                                dbRef.child(gId).setValue(gr);  //je img upload karyu ana link sathe record update thase
                                Toast.makeText(getApplicationContext(), "Record is added!!", Toast.LENGTH_SHORT).show();

                                etAddGName.setText("");
                                etAddGPrice.setText("");
                                etAddGStock.setText("");
                                etAddGUnit.setText("");
                                imgGrocery.setImageURI(null);


                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed!!"+e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });

    }

//    private ActionBar getSupportActionBar() {
//        return null;
//    }

    @Override
    public void onActivityResult(int req,int res,Intent data)
    {
        imgPath = data.getData();
        imgGrocery.setImageURI(imgPath);
    }
}