package com.hashinology.retrofitlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hashinology.retrofitlist.model.ModelList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterInterface{
    Adapter adapter;
    RecyclerView recyclerView;
    List<ModelList> myLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvList);

        getTest();


    }

    private void getTest() {
        Call<List<ModelList>> myList = RetrofitClient.getInstance().myAPI().getMyPost();
        myList.enqueue(new Callback<List<ModelList>>() {
            @Override
            public void onResponse(Call<List<ModelList>> call, Response<List<ModelList>> response) {
                //ArrayList<ModelList>
                if(response.isSuccessful()) {
                   myLists = response.body();
                    adapter = new Adapter(myLists, getApplication());

                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

                    adapter.getMyInterface(MainActivity.this);
                }else{
                    Toast.makeText(getApplication(), "Error from the List code", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ModelList>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void myInterfaceClick(View view, int position) {
        Toast.makeText(this, "" +position, Toast.LENGTH_SHORT).show();
    }
}