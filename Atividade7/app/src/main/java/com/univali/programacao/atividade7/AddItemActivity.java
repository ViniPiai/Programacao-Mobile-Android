package com.univali.programacao.atividade7;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.univali.programacao.atividade7.activity.MainActivity;
import com.univali.programacao.atividade7.helper.DbHelper;


public class AddItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        Button button = findViewById(R.id.btnGoBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etProductName = findViewById(R.id.etProductName);
                EditText etProductType = findViewById(R.id.etProductType);
                EditText etProductQuantity = findViewById(R.id.etProductQuantity);
                String productName = etProductName.getText().toString();
                String productType = etProductType.getText().toString();
                String productQuantity = etProductQuantity.getText().toString();
                DbHelper dbHelper = new DbHelper(getApplicationContext());
                ContentValues contentValues = new ContentValues();
                contentValues.put("product", productName);
                contentValues.put("type", productType);
                contentValues.put("quantity", productQuantity);
                contentValues.put("purchased", 0);
                dbHelper.getWritableDatabase().insert("items", null, contentValues);
                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
