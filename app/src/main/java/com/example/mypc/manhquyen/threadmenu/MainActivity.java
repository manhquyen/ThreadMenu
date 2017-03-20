package com.example.mypc.manhquyen.threadmenu;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    TextView tvInfo;
    ListView list;
    boolean isRunning=true;
    boolean isPause;

    String[] dataModel = {"lorem", "ipsum", "dolo", "sit", "amet", "consectetuer"};
    ArrayList<String> data = new ArrayList<String>();
    ArrayAdapter<String> adapter=null;
    private ArrayList<String> words=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo=(TextView)findViewById(R.id.text);
        list=(ListView)findViewById(R.id.list);
        words= new ArrayList<String>();
        for (String s : dataModel){
            words.add(s);
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        //String str= "tyle ";
        //data.add(str);
        //adapter.notifyDataSetChanged();
        //adapter.addAll(dataModel);
        list.setAdapter(adapter);
        //list.setOnItemClickListener(onListClick);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void initData()
    {   //words = new ArrayList<String>();
        data.clear();
        adapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        list.setAdapter(adapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add:
                //add();
                return (true); // xử lý thành công menu trả về true
           case R.id.reset:
                //reset();
                return (true);
            case R.id.pause:
                //pause();
                return (true);
            case R.id.stop:
                //stop();
                return (true);
        }
        return super.onOptionsItemSelected(item);
    }
    public void Add(View v)
    {   MyAsynTaskAdd Asyn =new MyAsynTaskAdd();
        Asyn.execute();
    }

    public class MyAsynTaskAdd extends AsyncTask<Void,Integer,Void>
    {   int progress=0;
        int hold;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            tvInfo.setText("Bắt đầu thêm");
            //isRunning.set(true);
            //list.setAdapter(adapter);

        }

        @Override
        protected Void doInBackground(Void... params) {


            for(int i=0;i<=5;i++)
            {    if (isRunning==false)
                break;
                while (isPause)
                {
                    SystemClock.sleep(5000);
                }
                progress +=1;
                SystemClock.sleep(1000);
                publishProgress(progress);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int progress = values[0].intValue();
            String str;
            //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataModel);
            //ArrayAdapter<String> adapter=(ArrayAdapter<String>)list.getAdapter();
            //adapter.add(dataModel[progress]);
            //tvInfo.setText(str);
            //list.setSelection(progress);
            //adapter.insert((String) str,6);
            //String a= adapter.getItem(progress);
             //adapter.getItem(progress);
            //tvInfo.setText(a);
            switch (progress){
                case 1:
                    str="lorem";
                    data.add(str);
                    break;
                case 2:
                    str="ipsum";
                    data.add(str);
                    break;
                case 3:
                    str="dolo";
                    data.add(str);
                    break;
                case 4:
                    str="sit";
                    data.add(str);
                    break;
                case 5:
                    str="amet";
                    data.add(str);
                    break;
                case 6:
                    str="consectetuer";
                    data.add(str);
                    break;


            }


            adapter.notifyDataSetChanged();//update adapter
            list.setAdapter(adapter);

            //ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,a);
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {


            SystemClock.sleep(1000);
            tvInfo.setText("Kết thúc");
            super.onPostExecute(aVoid);
        }
    }
    public void Reset (View v)
    {
        //list.setAdapter(null);
        initData();
    }
    public void Stop (View v)
    {
        isRunning=false;
    }
    public void Pause(View v)
    {
        isPause=true;
    }
    public void Resume(View v)
    {
        isPause=false;
    }
}
