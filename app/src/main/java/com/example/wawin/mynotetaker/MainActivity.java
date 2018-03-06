package com.example.wawin.mynotetaker;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity implements android.view.View.OnClickListener {

    Button btnAdd, btnGetAll;
    TextView file_Id;

    @Override
    public void onClick(View view) {

        if (view == findViewById(R.id.btnAdd)) {

            Intent intent = new Intent(this, FileActivity.class);
            intent.putExtra("file_Id", 0);
            startActivity(intent);

        } else {

            FileRepo repo = new FileRepo(this);

            ArrayList<HashMap<String, String>> fileList = repo.getFileList();
            if (fileList.size() != 0) {
                ListView lv = getListView();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        file_Id = (TextView) view.findViewById(R.id.file_Id);
                        String fileId = file_Id.getText().toString();
                        Intent objIndent = new Intent(getApplicationContext(), FileActivity.class);
                        objIndent.putExtra("file_Id", Integer.parseInt(fileId));
                        startActivity(objIndent);
                        //file_Id.setTypeface(null, Typeface.BOLD_ITALIC);
                    }
                });
                ListAdapter adapter = new SimpleAdapter(MainActivity.this, fileList, R.layout.view_file_entry, new String[]{"id", "name"}, new int[]{R.id.file_Id, R.id.file_name});
                setListAdapter(adapter);
            } else {
                Toast.makeText(this, "No file!", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);
        onClick(getListView());



    }
}

