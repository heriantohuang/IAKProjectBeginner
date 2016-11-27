package com.example.android.belajarlayout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class CoffeeActivity extends AppCompatActivity {
    EditText mTextNama;
    TextView mTextHarga, mTextQty;
    Button mButtonOrder, mButtonPlus, mButtonMin;
    RadioButton mRadioItem1, mRadioItem2;
    RadioGroup mRadioGroup;
    Spinner mSpinnerMenu;

    Context mContext;
    int qty = 0;
    int harga = 0;
    List<String> mListMenu = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mContext = getApplicationContext();

        mTextNama = (EditText) findViewById(R.id.mTextNama);
        mTextHarga = (TextView) findViewById(R.id.mTextHarga);
        mTextQty = (TextView) findViewById(R.id.mTextQty);
        mButtonOrder = (Button) findViewById(R.id.mButtonOrder);
        mButtonPlus = (Button) findViewById(R.id.mButtonPlus);
        mButtonMin  = (Button) findViewById(R.id.mButtonMin);
        mSpinnerMenu = (Spinner) findViewById(R.id.mSpinnerMenu);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mRadioItem1 = (RadioButton) findViewById(R.id.mRadioItem1);
        mRadioItem2 = (RadioButton) findViewById(R.id.mRadioItem2);

        mListMenu.add("--------------------");
        mListMenu.add("Martabak");
        mListMenu.add("Piscok Bakar");
        mListMenu.add("Ice Cream Sandwich");
        mListMenu.add("Lumpia Basah");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mListMenu);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinnerMenu.setAdapter(dataAdapter);

        mRadioItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Item 1 diklik", Toast.LENGTH_SHORT).show();
            }
        });
        mRadioItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Item 2 diklik", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickOrder(View v)
    {
        String subject = mTextNama.getText().toString();

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "arieridwansyah@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Saya pesan "
                + mSpinnerMenu.getSelectedItem()
                + " sebanyak "
                + mTextQty.getText()
                + " seharga "
                + mTextHarga.getText());
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
        } catch (android.content.ActivityNotFoundException ex)
        {
            Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }

    }

    public void onClickMin(View v)
    {
        harga = harga - 5;
        qty = qty - 1;

        if(harga < 0 )
        {
            harga = 0;
            qty = 0;
        }

        mTextHarga.setText("$" + harga);
        mTextQty.setText(String.valueOf(qty));
    }

    public  void onClickPlus(View v) {
        harga = harga + 5;
        qty = qty + 1;
        mTextHarga.setText("$" + harga);
        mTextQty.setText(String.valueOf(qty));
    }

    public void onButtonReset(View v)
    {
        harga =0;
        qty = 0;
        mTextNama.setText("");
        mTextHarga.setText("$" + harga);
        mTextQty.setText(String.valueOf(qty));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
