package jp.co.calace.lonor;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jp.co.calace.lonor.models.Character;
import jp.co.calace.lonor.models.Message;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageButton chatRoomSubmit;
    private EditText chatRoomInput;
    private RecyclerView chatRoomContent;
    private Character self;
    private List<Character> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //FloatingActionButton fab = findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        // Initial characters
        self = initialSelfCharacter();
        friends = initialFriends();

        // Initial chat room list view
        chatRoomContent = findViewById(R.id.chatRoomContent);
        final List<Message> msgList = new ArrayList<Message>();
        final MsgAdapter msgAdapter = new MsgAdapter(msgList);
        chatRoomContent.setAdapter(msgAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        chatRoomContent.setLayoutManager(layoutManager);

        // Initial chat room msg submit
        chatRoomInput = findViewById(R.id.chatRoomInput);
        chatRoomSubmit = findViewById(R.id.chatRoomSubmit);
        chatRoomSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = chatRoomInput.getText().toString().trim();
                if (msg.length() < 1) {
                    return;
                }

                msgList.add(new Message(self, msg, new Date(), Message.TYPE_SEND));

                msgList.add(new Message(friends.get(0), "こんにちは", new Date(), Message.TYPE_RECV));
                msgList.add(new Message(friends.get(1), "話しかけないでください", new Date(), Message.TYPE_RECV));
                msgList.add(new Message(friends.get(2), "喧嘩か、テメェ！ぶっ殺すぞ！", new Date(), Message.TYPE_RECV));
                msgList.add(new Message(friends.get(3), "...", new Date(), Message.TYPE_RECV));

                msgAdapter.notifyItemInserted(msgList.size() - 1);
                chatRoomContent.scrollToPosition(msgList.size() - 1);

                chatRoomInput.setText("");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Character initialSelfCharacter() {
        Character self = new Character(0, "Me", R.drawable.ic_person_black_24dp, new Date(), 0);
        return self;
    }

    public List<Character> initialFriends() {
        List<Character> friends = new ArrayList<>();
        friends.add(new Character(1, "Alice", R.drawable.ic_person_black_24dp, new Date(), 0));
        friends.add(new Character(2, "Bob", R.drawable.ic_person_black_24dp, new Date(), 0));
        friends.add(new Character(3, "Cloud", R.drawable.ic_person_black_24dp, new Date(), 0));
        friends.add(new Character(4, "Darlene", R.drawable.ic_person_black_24dp, new Date(), 0));
        return friends;
    }
}
