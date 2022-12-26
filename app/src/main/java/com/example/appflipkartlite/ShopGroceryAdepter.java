package com.example.appflipkartlite;



//for custom view of list
//this is a simple java class
//adapter's name should be on the name of main activity

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class ShopGroceryAdepter extends ArrayAdapter<Grocery>
{

    Context cont;
    ArrayList<Grocery> gList;
    TextView tv;
    Button btOrder;
    int resource;
    int order[],price[],amount[];
    int totalAmount=0;

    //4 empty list  for invoice
    ArrayList<String> gNameList= new ArrayList<String>(); //invoice ma badhu product ni details mokalva mate
    ArrayList<String> gQtyList= new ArrayList<String>();
    ArrayList<String> gPriceList= new ArrayList<String>();
    ArrayList<String> gAmountList= new ArrayList<String>();



    ShopGroceryAdepter(Context cont,int resource,ArrayList<Grocery>gList,TextView tv,Button btOrder){
        super(cont,resource,gList);
        this.cont = cont;
        this.gList = gList;
        this.tv=tv;
        this.btOrder = btOrder;
        this.resource= resource;
        order = new int[gList.size()];
        price = new int[gList.size()];
        amount = new int[gList.size()];

        for (int i=0;i<gList.size();i++)
        {
            gNameList.add("");
            gPriceList.add("");
            gQtyList.add("");
            gAmountList.add("");

        }

        for (int i=0;i<gList.size();i++) {

            price[i] = gList.get(i).price;
            order[i] = 0;
            amount[i]=0;
        }

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = LayoutInflater.from(cont); //non activity ckass ma component map karva mate

        View view = inflater.inflate(resource,null,false);

        ImageView iv = view.findViewById(R.id.cardGroceyOrderImage);
        TextView tvName = view.findViewById(R.id.tvCardGroceryOrderName);
        TextView tvPrice = view.findViewById(R.id.tvCardGroceryOrderPrice);
        Button btPlus = view.findViewById(R.id.btStockPlus);
        Button btMinus = view.findViewById(R.id.btStockMinus);
        TextView tvStock = view.findViewById(R.id.tvStockNumber);

        Grocery g = gList.get(position);

        btPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order[position]++; //jena per click karyu che ani value + thase //quantity
                totalAmount = 0;
                tvStock.setText(""+order[position]);
                int amt = order[position] * price[position];
                amount[position]=amt;

                for(int i=0;i<amount.length;i++){
                    totalAmount = totalAmount + amount[i];

                }
                tv.setText(""+totalAmount);

                gNameList.set(position,gList.get(position).getName()); //name lidhu
                gQtyList.set(position,""+order[position]); // order quantity upar thi j lidhu
                gPriceList.set(position,""+price[position]);
                gAmountList.set(position,""+amount[position]);



            }
        });

        btMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                order[position]--; //jena per click karyu che ani value -- thase
                totalAmount = 0;
                tvStock.setText(""+order[position]);
                int amt = order[position] * price[position];
                amount[position]=amt;

                for(int i=0;i<amount.length;i++){
                    totalAmount = totalAmount + amount[i];

                }
                tv.setText(""+totalAmount);

                gNameList.set(position,gList.get(position).getName()); //name lidhu
                gQtyList.set(position,""+order[position]); // order quantity upar thi j lidhu
                gPriceList.set(position,""+price[position]);
                gAmountList.set(position,""+amount[position]);
            }
        });

        btOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ii = new Intent(cont,GroceryInvoice.class);
                ii.putExtra("totalamount",""+totalAmount);
                ii.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); //this flag has to be set when user tries to call activity from non activity class


                //arraylist pass karva mate
                ii.putStringArrayListExtra("gnamelist",gNameList);
                ii.putStringArrayListExtra("gqtylist",gQtyList);
                ii.putStringArrayListExtra("gpricelist",gPriceList);
                ii.putStringArrayListExtra("gamountlist",gAmountList);

                cont.startActivity(ii); // non activity mathi activity call karva mate flag set karvo pade





            }
        });


        tvName.setText(g.getName());
        tvPrice.setText(""+g.getPrice());
        Glide.with(cont)
                .load(g.getImgUrl())
                .override(800,400)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(iv);


        return view;
    }

}

