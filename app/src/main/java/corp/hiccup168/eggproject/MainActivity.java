package corp.hiccup168.eggproject;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    private int id = 0;

    // 動態生成unique id
    private int gen_id(){
        return ++id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.test);
        setContentView(R.layout.activity_main);

        initView();

        // 安裝監聽器
        // cannot get ImageButton here
        /*
        ImageButton btnImage = (ImageButton)findViewById(R.id.ItemImage);
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup r = (ViewGroup)v.getParent();



//                r.findViewById();
//                FrameLayout container = (FrameLayout)findViewById(R.id.frag_container);
//                if(container.getVisibility() == View.GONE)
//                    container.setVisibility(View.VISIBLE);
//                else
//                    container.setVisibility(View.INVISIBLE);

            }
        });
        */



    }

    private void initView(){
        //動態資訊 陣列
//        int[] img = {R.drawable.bg2f, R.drawable.bg3f, R.drawable.bg4f, R.drawable.bg5f, R.drawable.bg6f};
//        String[] arr = {"2F", "3F", "4F", "5F", "6F"};

        int[] img = {R.drawable.bg6f, R.drawable.bg5f, R.drawable.bg4f, R.drawable.bg3f, R.drawable.bg2f};
        String[] arr = {"6F", "5F", "4F", "3F", "2F"};

        //取得ListView元件
        ListView list = (ListView)findViewById(R.id.MyListView);

        //建立存放HashMap資訊的ArrayList物件
        ArrayList<HashMap<String, Object>> listItems = new ArrayList<HashMap<String, Object>>();

        //將資料轉換成HashMap型態存進ListView裡
        for(int i = 0; i < arr.length; i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", img[i]);//Img
            map.put("ItemTitle", arr[i]);//Title
            listItems.add(map);
        }

        //利用SimpleAdapter產生動態資訊
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItems, //套入動態資訊
                R.layout.list_item,//套用自訂的XML
                new String[] {"ItemImage","ItemTitle"}, //動態資訊取出順序
                new int[] {R.id.ItemImage,R.id.ItemTitle} //將動態資訊對應到元件ID
        );

        // 安裝監聽器
        // bind event Using SimpleAdapter's setViewBinder()
        listItemAdapter.setViewBinder(new SimpleAdapter.ViewBinder(){
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {

                if(view instanceof ImageView){
                    ImageView imgBtn = (ImageView)view;
                    imgBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                            my identifier!!
//                            Log.d("info", "@: check ItemTitle > " + ((TextView) ((ViewGroup) v.getParent()).findViewById(R.id.ItemTitle))
//                                            .getText()
//                            );

//                            Log.d("info", "@: find you~~~");
                            // how to add fragment into the container
                            // FrameLayout fl = (FrameLayout)((ViewGroup) v.getParent().getParent()).findViewById(R.id.frag_container);

                            FrameLayout fl = (FrameLayout)((ViewGroup) v.getParent().getParent()).getChildAt(1);
                            Log.d("info", "@: id > " + fl.getId());
                            fl.setId(gen_id());


                            if(fl != null){
                                if(fl.getVisibility() == View.GONE){

                                    String floor = ((TextView) ((ViewGroup) v.getParent())
                                            .findViewById(R.id.ItemTitle))
                                            .getText().toString();

                                    FragmentManager fm = getFragmentManager();
                                    FragmentTransaction ft = fm.beginTransaction();
                                    ft.replace(fl.getId(), AreaFragment.getInstance(
                                            AreaModel.getAreaContents(floor), floor.charAt(0) - 48
                                    ), "fragment layer " + floor);
                                    ft.commit();
                                    fm.executePendingTransactions();

                                    fl.setVisibility(View.VISIBLE);
                                }

                                else {
                                    fl.setVisibility(View.GONE);
                                }
                            }
                        }
                    });
                }

                return false; // what if it return true
            }
        });


        //執行SimpleAdapter
        list.setAdapter(listItemAdapter);
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

//    public void spreadL2(View v){
//        Log.d("info", "@: MainActivity > spreadL2()");
//    }
}
