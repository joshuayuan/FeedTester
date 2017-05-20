package co.joshuayuan.feedtester;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mainFeedListView;
    private ArrayList<Entry> entry_data;
    private EntryAdapter mAdapter;
    private static final int entryRequestcode = 642;
    private static final int resultOK = 585;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab!=null){
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, EntryEditorActivity.class);
                    startActivityForResult(intent, entryRequestcode);

                }
            });
        }

        entry_data = new ArrayList<>();
        entry_data.add(new Entry(BitmapFactory.decodeResource(getResources(), R.drawable.sample_0), "Rex", "15", "Rex is a beautiful dog worth 15"));
        entry_data.add(new Entry(BitmapFactory.decodeResource(getResources(), R.drawable.sample_2), "Pups", "20", "Pups is not a cheap one. He should be worth more still."));
        entry_data.add(new Entry(BitmapFactory.decodeResource(getResources(), R.drawable.sample_2), "Winston", "12", "I brought up Winston myself."));
        entry_data.add(new Entry(BitmapFactory.decodeResource(getResources(), R.drawable.sample_2), "Barker", "6", "No one likes Barker's barks, except me!"));
        entry_data.add(new Entry(BitmapFactory.decodeResource(getResources(), R.drawable.sample_2), "Biscuits", "12", "Everyday, Biscuits eats at least 12 biscuits.\n3 for breakfast, 3 for noon, 3 for dinner, and 3 at midnight!" ));

        mAdapter = new EntryAdapter(this,
                R.layout.feed_entry_layout, entry_data);


        mainFeedListView = (ListView)findViewById(R.id.mainFeedListView);

        if (mainFeedListView!=null){

            mainFeedListView.setAdapter(mAdapter);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==entryRequestcode){
            if(resultCode==resultOK){
                Bundle b = data.getExtras();
                if (b!=null){
                    Entry entryToAdd = b.getParcelable("newEntry");
                    mAdapter.add(entryToAdd);
                }
                mAdapter.notifyDataSetChanged();
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
