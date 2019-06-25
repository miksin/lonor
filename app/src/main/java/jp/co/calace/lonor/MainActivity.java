package jp.co.calace.lonor;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
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
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jp.co.calace.lonor.dao.MessageDao;
import jp.co.calace.lonor.db.DBHelper;
import jp.co.calace.lonor.models.BadBoyReplyEngine;
import jp.co.calace.lonor.models.Character;
import jp.co.calace.lonor.models.ConversationSet;
import jp.co.calace.lonor.models.CoolReplyEngine;
import jp.co.calace.lonor.models.Message;
import jp.co.calace.lonor.models.ReplyEngine;
import jp.co.calace.lonor.models.SeriousReplyEngine;
import jp.co.calace.lonor.models.YandereReplyEngine;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageButton chatRoomSubmit;
    private EditText chatRoomInput;
    private RecyclerView chatRoomContent;
    private Character self;
    private List<Character> friends;
    private List<ExecutorService> executors;
    private DBHelper dbHelper;
    private MessageDao messageDao;
    private List<Message> msgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
        executors = new ArrayList<>();
        for (int i = 0; i < friends.size(); i ++) {
            executors.add(Executors.newSingleThreadExecutor());
        }

        // Initial DB
        dbHelper = new DBHelper(this);
        messageDao = new MessageDao(dbHelper, self, friends);


        // Initial chat room list view
        chatRoomContent = findViewById(R.id.chatRoomContent);
        //final List<Message> msgList = new ArrayList<Message>();
        final List<Message> msgList = messageDao.getAll();
        final MsgAdapter msgAdapter = new MsgAdapter(msgList);
        chatRoomContent.setAdapter(msgAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        chatRoomContent.setLayoutManager(layoutManager);
        chatRoomContent.scrollToPosition(msgList.size() - 1);

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

                List<String> refMsgList = new ArrayList<>();
                refMsgList.add(msg);
                insertMsg(msgList, new Message(self, msg, new Date(), Message.TYPE_SEND), msgAdapter);

                for (int i = 0; i < friends.size(); i++) {
                    Character friend = friends.get(i);
                    List<String> replyList = friend.reply(refMsgList);
                    //ExecutorService executor = Executors.newSingleThreadExecutor();
                    for (String reply : replyList) {
                        new ReplyTask(msgList, msgAdapter).executeOnExecutor(
                                executors.get(i), new Message(friend, reply, new Date(), Message.TYPE_RECV));
                    }
                }

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
        Character self = new Character(-1, "Me", R.drawable.ic_person_black_24dp, new Date(), new ReplyEngine());
        return self;
    }

    public List<Character> initialFriends() {
        List<Character> friends = new ArrayList<>();
        friends.add( new Character(0, "アリス", R.drawable.ic_glove, "1997-01-01 18:36:45", new SeriousReplyEngine()));
        friends.add(new Character(1, "ボッブ", R.drawable.ic_guitar, "1998-08-08 13:51:11", new CoolReplyEngine()));
        friends.add(new Character(2, "クラウド", R.drawable.ic_knife, "2000-07-07 06:12:52", new BadBoyReplyEngine()));
        friends.add(new Character(3, "ダーレン", R.drawable.ic_bone, "2003-06-06 09:23:12", new YandereReplyEngine()));
        return friends;
    }

    public void insertMsg(List<Message> msgList, String msg, int senderId, MsgAdapter msgAdapter) {
        if (senderId < 0) {
            messageDao.insertMsg(new Message(self, msg, new Date(), Message.TYPE_SEND));
            msgList.add(new Message(self, msg, new Date(), Message.TYPE_SEND));
            msgAdapter.notifyItemInserted(msgList.size() - 1);
            chatRoomContent.scrollToPosition(msgList.size() - 1);
        } else if (senderId < friends.size()) {
            for (int i = 0; i < friends.size(); i++) {
                if (friends.get(i).getId() == senderId) {
                    messageDao.insertMsg(new Message(friends.get(i), msg, new Date(), Message.TYPE_RECV));
                    msgList.add(new Message(friends.get(i), msg, new Date(), Message.TYPE_RECV));
                    msgAdapter.notifyItemInserted(msgList.size() - 1);
                    chatRoomContent.scrollToPosition(msgList.size() - 1);
                    break;
                }
            }
        }
    }

    public void insertMsg(List<Message> msgList, Message msg, MsgAdapter msgAdapter) {
        messageDao.insertMsg(msg);
        msgList.add(msg);
        msgAdapter.notifyItemInserted(msgList.size() - 1);
        chatRoomContent.scrollToPosition(msgList.size() - 1);
    }

    public class ReplyTask extends AsyncTask<Message, Void, Message> {

        private Random random = new Random();
        private List<Message> msgList;
        private MsgAdapter msgAdapter;

        public ReplyTask(List<Message> msgList, MsgAdapter msgAdapter) {
            this.msgList = msgList;
            this.msgAdapter = msgAdapter;
        }

        @Override
        protected Message doInBackground(Message... msgs) {
            try {
                Thread.sleep(random.nextInt(1000) + msgs[0].getText().length() * 200);
                msgs[0].setTime(new Date());
                return msgs[0];
            } catch (Exception ignore) {};
            return msgs[0];
        }

        @Override
        protected void onPostExecute(Message msg) {
            insertMsg(msgList, msg, msgAdapter);
        }
    }
}
