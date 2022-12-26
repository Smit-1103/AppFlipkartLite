package com.example.appflipkartlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class GroceryInvoiceAdapter extends ArrayAdapter<String>
{
    Context context;
    int resource;
    ArrayList<String> gNameList= new ArrayList<String>(); //invoice ma badhu product ni details mokalva mate
    ArrayList<String> gQtyList= new ArrayList<String>();
    ArrayList<String> gPriceList= new ArrayList<String>();
    ArrayList<String> gAmountList= new ArrayList<String>();


    GroceryInvoiceAdapter(Context context, int resource,
                          ArrayList<String> gNameList,
                          ArrayList<String> gQtyList,
                          ArrayList<String> gPriceList,
                          ArrayList<String> gAmountList)
    {
        super(context,resource,gNameList);
        this.context = context;
        this.resource = resource;
        this.gNameList = gNameList;
        this.gQtyList = gQtyList;
        this.gPriceList = gPriceList;
        this.gAmountList = gAmountList;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        //non activity class ma xml component pass karva mate ( niche ni 2 line )
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource,null,false);

        TextView tvGName  = view.findViewById(R.id.tvGroceryProductName);
        TextView tvGQty = view.findViewById(R.id.tvGroceryProductQty);
        TextView tvGPrice  = view.findViewById(R.id.tvGroceryProductRate);
        TextView tvGAmount = view.findViewById(R.id.tvGroceryProductAmount);

        tvGName.setText(""+gNameList.get(position));
        tvGQty.setText(""+gQtyList.get(position));
        tvGPrice.setText(""+gPriceList.get(position));
        tvGAmount.setText(""+gAmountList.get(position));


        return view;
    }
}
