package com.example.wawin.mynotetaker;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FileActivity extends AppCompatActivity implements android.view.View.OnClickListener{

    Button btnSave ,  btnDelete;
    Button btnClose, btnShare, btnInfo;
    EditText editTextName;
    private int _File_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);

        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btnClose);
        btnShare = (Button) findViewById(R.id.btnShare);
        btnInfo = (Button) findViewById(R.id.btnInfo);

        editTextName = (EditText) findViewById(R.id.editText4);
        btnSave.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnShare.setOnClickListener(this);
        btnInfo.setOnClickListener(this);


        _File_Id =0;
        Intent intent = getIntent();
        _File_Id =intent.getIntExtra("file_Id", 0);
        FileRepo repo = new FileRepo(this);
        File file;
        file = repo.getFIleById(_File_Id);

        editTextName.setText(file.name);
        editTextName.setTypeface(null, Typeface.BOLD_ITALIC);


    }



    public void onClick(View view) {
        if (view == findViewById(R.id.btnSave)){
            FileRepo repo = new FileRepo(this);
            File file = new File();
            file.name=editTextName.getText().toString();
            file.file_ID=_File_Id;

            if (_File_Id==0){
                _File_Id = repo.insert(file);

                Toast.makeText(this, "New File Insert", Toast.LENGTH_SHORT).show();
            }else{

                repo.update(file);
                Toast.makeText(this,"File Record updated",Toast.LENGTH_SHORT).show();
            }
        }else if (view== findViewById(R.id.btnDelete)){
            FileRepo repo = new FileRepo(this);
            repo.delete(_File_Id);
            Toast.makeText(this, "File Record Deleted", Toast.LENGTH_SHORT);
            finish();
        }else if (view== findViewById(R.id.btnClose)){
            finish();
        }
        else if (view == findViewById(R.id.btnShare)){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, editTextName.getText());
            sendIntent.setType("text/plain");
            //sendIntent.setPackage("com.whatsapp");
            startActivity(sendIntent);
        }
        else if (view ==findViewById(R.id.btnInfo)) {
            FileRepo repo = new FileRepo(this);
            File file;
            file = repo.info(_File_Id);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Info of the given text"+ "\n" +file.time.toString());
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            //public void onClick(DialogInterface arg0, int arg1) {
            //Toast.makeText(FileActivity.this,file.time.toString(), Toast.LENGTH_SHORT).show();
          //  }
        }
    }


}
