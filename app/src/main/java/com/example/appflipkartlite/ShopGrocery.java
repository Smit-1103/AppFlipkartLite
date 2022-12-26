package com.example.appflipkartlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShopGrocery extends Activity {
    ListView lvShop;
    TextView tv;
    Button btOrder;
    DatabaseReference dbRef;
    ArrayList<Grocery> gList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_grocery);

        lvShop = findViewById(R.id.lvShop);
        tv = findViewById(R.id.tvGroceryOrderBill);
        btOrder = findViewById(R.id.btGroceryOrderConfirm);

        gList = new ArrayList<Grocery>();

        dbRef = FirebaseDatabase.getInstance().getReference("grocery");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gList.clear(); //clear karvanu pela

                for(DataSnapshot shot : snapshot.getChildren())
                {
                    Grocery g =  shot.getValue(Grocery.class);
                    gList.add(g);
                }

               // ArrayAdapter<Grocery> ad = new ArrayAdapter<Grocery>(getApplicationContext(), android.R.layout.simple_list_item_1,gList); //normal view
                ShopGroceryAdepter sa = new ShopGroceryAdepter(getApplicationContext(), R.layout.grocerycard,gList,tv,btOrder);      //custom view // tv and bt pass karvathi adapter class ma kai b change a a class ma batavs e
                lvShop.setAdapter(sa);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}

//custom view mate 4 file 1.activity je database mathi collect kare data 2.activity ni xml file(listview hase) 3.dream design ni xml file 4.agad ni 3 file ne combined karva mate adapter file