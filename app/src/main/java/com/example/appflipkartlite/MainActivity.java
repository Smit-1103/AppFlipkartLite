package com.example.appflipkartlite;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {
    EditText etUser, etPass;
    Button btLogin, btSign, btShop;
    private long pressedTime; // for back pressed twice to exit


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().hide();

        etUser = findViewById(R.id.etLoginEmail);
        etPass = findViewById(R.id.etLoginPassword);

        btLogin = findViewById(R.id.btLoginLogin);
        btSign = findViewById(R.id.btLoginSignUp);
        btShop = findViewById(R.id.btForwardBuy);

//        //shared preferance
//        SharedPreferences sharedPreferences = getSharedPreferences(AdminHome.PREFS_NAME,0);
//        boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn",false);
//
//        if(hasLoggedIn){
//            Intent intent = new Intent(getApplicationContext(),AdminHome.class);
//            startActivity(intent);
//            finish();
//        }
//        else{
////            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
////            startActivity(intent);
////            finish();
//        }

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = etUser.getText().toString();
                String pass = etPass.getText().toString();

                if (user.equals("ADMIN") && pass.equals("INDIA")) {

//                    //shared preferance
//                    SharedPreferences shad = getSharedPreferences(AdminHome.PREFS_NAME,0);
//                    SharedPreferences.Editor edit = shad.edit();
//                    edit.putBoolean("hasLoggedIn",true);
//                    edit.commit();
//                    startActivity(new Intent(getApplicationContext(),AdminHome.class));
//                    finish();

                    Intent ii = new Intent(getApplicationContext(),AdminHome.class);
                    startActivity(ii);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid Username !!", Toast.LENGTH_SHORT).show();

                }
                etPass.setText("");
                etUser.setText("");

            }
        });

        btShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii = new Intent(getApplicationContext(),ShopGrocery.class);
                startActivity(ii);

            }
        });


    }
    //for back pressed twice to exit
    @Override
    public void onBackPressed() {
        if (pressedTime + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        pressedTime = System.currentTimeMillis();
    }

}