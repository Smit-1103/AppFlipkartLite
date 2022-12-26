package com.example.appflipkartlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class GroceryInvoice extends Activity {
    String totalamount;
    TextView tvGroceryGrandTotal,tvGroceryBillDate,tvGroceryBillTime;
    ListView lvGroceryPuchased;
    Button btGroceryPayment;
    ArrayList<String> gNameList= new ArrayList<String>(); //invoice ma badhu product ni details mokalva mate
    ArrayList<String> gQtyList= new ArrayList<String>();
    ArrayList<String> gPriceList= new ArrayList<String>();
    ArrayList<String> gAmountList= new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery_invoice);

        Intent ii = getIntent();

        totalamount = ii.getStringExtra("totalamount");
        gNameList = ii.getStringArrayListExtra("gnamelist");
        gQtyList = ii.getStringArrayListExtra("gqtylist");
        gPriceList = ii.getStringArrayListExtra("gpricelist");
        gAmountList = ii.getStringArrayListExtra("gamountlist");

        tvGroceryGrandTotal = findViewById(R.id.tvGroceryGrandTotal);
        lvGroceryPuchased = findViewById(R.id.lvGroceryPurchsed);
        btGroceryPayment = findViewById(R.id.btGroceryPayment);
        tvGroceryBillDate = findViewById(R.id.tvGroceryBillDate);
        tvGroceryBillTime = findViewById(R.id.tvGroceryBillTime);

        tvGroceryGrandTotal.setText("Rs. "+totalamount);

        //system mathi date ne time  mate
        Date dd= Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
        String formattedDate = df.format(dd);

        tvGroceryBillDate.setText(formattedDate);

        df = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String formattedTime = df.format(dd);

        tvGroceryBillTime.setText(formattedTime);

        GroceryInvoiceAdapter ga = new GroceryInvoiceAdapter(getApplicationContext(),R.layout.groceryitemsbillview,gNameList,gQtyList,gPriceList,gAmountList);
        lvGroceryPuchased.setAdapter(ga);

        btGroceryPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ii = new Intent(getApplicationContext(),PaymentActivity.class);
                ii.putExtra("total", totalamount);
                startActivity(ii);

            }
        });



    }
}