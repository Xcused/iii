package com.example.iii;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.iii.adapter.TemanAdapter;
import com.example.iii.database.Controller;
import com.example.iii.database.Friends;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener, TemanAdapter.TemanAdapterListener {

    private RecyclerView recyclerView;
    private TemanAdapter adapter;

    private ArrayList<Friends> friendsArrayList;
    Controller controller = new Controller(this);
    String id,nm,tlp;
    private FloatingActionButton fab;
    private Button b;

    Bundle bundle = new Bundle();
    Intent intent;
    private Friends selectedFriends;
    private int SelectedIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingBtn);

        friendsArrayList = controller.getAllTeman();
        adapter = new TemanAdapter(friendsArrayList, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddFriend.class);
                startActivity(intent);
            }
        });
        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {


                Intent intent = new Intent(MainActivity.this, TampilData.class);
                startActivity(intent);

                return false;
            }
        });

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        Toast.makeText(this, "Selected Item: " +menuItem.getTitle(), Toast.LENGTH_SHORT).show();
        switch (menuItem.getItemId()){
            case R.id.mnedit:
                intent = new Intent(getApplicationContext(), Data_Edit.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.mndelete:
                try {
                    new AlertDialog.Builder(this).setMessage("Apakah anda yakin ingin menghapus semuanya?")
                            .setPositiveButton("Hapus",(y, x)-> {
                                controller.deleteTeman(this.selectedFriends.getId());

                                this.adapter.notifyItemRemoved(this.SelectedIndex);
                            } )
                            .setNegativeButton("Batal",(y, x)-> {

                            }).show();
                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Error saat menghapus",
                            Toast.LENGTH_LONG).show();
                    return false;
                }

                Toast.makeText(getApplicationContext(),"Berhasil Terhapus",
                        Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }

    @Override
    public void onTemanSelected(View v, Friends friends, int pos) {
        showPopup(v);
        this.selectedFriends = friends;
        this.SelectedIndex = pos;
    }
    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(MainActivity.this);
        popup.show();
    }
}