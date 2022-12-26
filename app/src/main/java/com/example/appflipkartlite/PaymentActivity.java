package com.example.appflipkartlite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONObject;

public class PaymentActivity extends Activity implements PaymentResultListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent ii = getIntent();
        String str = ii.getStringExtra("total");

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_gcSCXENrJeCCv7");

        JSONObject object = new JSONObject();
        try{
            object.put("name","smit patel");
            object.put("description", "enjoy your purchase!");
            object.put("theme.color","#001100");
            object.put("currency","INR");
            object.put("amount",(Integer.parseInt(str)*100));
            object.put("prefill.contact","+919104774568");
            object.put("prefill","my@gmail.com");

            checkout.open(this,object);
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Payment Error : "+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(getApplicationContext(),"Payment successful ",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(getApplicationContext(),"Payment failed",Toast.LENGTH_SHORT).show();
    }
}