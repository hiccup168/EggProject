package corp.hiccup168.eggproject;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class AreaFragment extends Fragment {

    static String[] area_contents;
    static int floor;
    SimpleAdapter adapter;
    ListView lv;
    int subjectHeight;


    private int id = 0;

    // 動態生成unique id
    private int gen_id(){
        id++;

        return id + floor*100;
    }

    public AreaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("info", "out");

        View view = inflater.inflate(
                R.layout.fragment_area,
                container,
                false
        );


//        CustomListAdapter<String> itemsAdapter =
//                new CustomListAdapter<String>((MainActivity)getActivity(), R.layout.list_area_item, area_contents);


        //建立存放HashMap資訊的ArrayList物件
        ArrayList<HashMap<String, Object>> listItems = new ArrayList<HashMap<String, Object>>();

        //將資料轉換成HashMap型態存進ListView裡
        for(int i = 0; i < area_contents.length; i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemContent", area_contents[i]);// text
            listItems.add(map);
        }

        //利用SimpleAdapter產生動態資訊
        SimpleAdapter listItemAdapter = new SimpleAdapter(getActivity(),listItems, //套入動態資訊
                R.layout.list_area_item,//套用自訂的XML
                new String[] {"ItemContent"}, //動態資訊取出順序
                new int[] {R.id.ItemContent} //將動態資訊對應到元件ID
        );

        adapter = listItemAdapter;

        ListView listview  = (ListView)view.findViewById(R.id.myAreaListView);
        listview.setAdapter(listItemAdapter);
        lv = listview;

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("info", "onItemClick() > " + ((TextView) ((ViewGroup) view).getChildAt(0)).getText().toString());

                FrameLayout fl = (FrameLayout)((LinearLayout)view).getChildAt(1);

                if(fl.getVisibility() == View.GONE){
                    Log.d("info", "fl.getVisibility() == View.GONE");
                    fl.setVisibility(View.VISIBLE);
                    ((ImageView)((RelativeLayout)((LinearLayout)view).getChildAt(0)).getChildAt(0)).setImageResource(R.drawable.arrow_white_down);

                    FragmentManager fm = getFragmentManager();

//                if(fm.getBackStackEntryCount() > 0)
//                    fm.popBackStack();

                    FragmentTransaction ft = fm.beginTransaction();

                    if(fl.getId() == -1){
                        fl.setId(gen_id());
                        Log.d("info", "gen id > " + fl.getId());
                    }

                    Log.d("info", "get id > " + fl.getId());
                    ft.replace(fl.getId(), new SubjectFragment()
                            , "frag" + fl.getId());


                    //ft.addToBackStack("frag" + fl.getId());

                    ft.commit();
                    fm.executePendingTransactions();

                    fl.measure(0, 0);
                    int subjectHeight = fl.getMeasuredHeight();

                    ViewGroup.LayoutParams params = parent.getLayoutParams();
                    parent.getLayoutParams().height += subjectHeight;
                    parent.setLayoutParams(params);
                    parent.requestLayout();
                }
                else{
                    Log.d("info", "fl.getVisibility() == View.VISIBLE");
                    fl.setVisibility(View.GONE);
                    ((ImageView)((RelativeLayout)((LinearLayout)view).getChildAt(0)).getChildAt(0)).setImageResource(R.drawable.arrow_white);

                    fl.measure(0, 0);
                    int subjectHeight = fl.getMeasuredHeight();

                    FragmentManager fm = getFragmentManager();

                    ViewGroup.LayoutParams params = parent.getLayoutParams();
                    parent.getLayoutParams().height -= subjectHeight;
                    parent.setLayoutParams(params);
                    parent.requestLayout();
                }




            }
        });


        // Set list height.
        adjustListView();
//        ViewGroup.LayoutParams params = listview.getLayoutParams();
//        params.height = MeasureHeightOfView(listview, listItemAdapter);
//        listview.setLayoutParams(params);
//        listview.requestLayout(); // 上報重新設置

        // 安裝監聽器
        /*listItemAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                  @Override
                  public boolean setViewValue(View view, Object data, String textRepresentation) {
                      if(view instanceof TextView){
                          Log.d("info", "data > " + data.toString());
//                          FrameLayout fl = (FrameLayout) ((LinearLayout) ((TextView) view).getParent()).getChildAt(1);
//                          fl.setId(gen_id());
//
//                          FragmentManager fm = getFragmentManager();
//                          FragmentTransaction ft = fm.beginTransaction();
//                          ft.add(fl.getId(), new SubjectFragment()
//                                  , "SubjectFragment");
//                          ft.commit();
                          //fm.executePendingTransactions();
                          //adjustListView();


                          view.setOnClickListener(new View.OnClickListener() {
                              @Override
                              public void onClick(View v) {
                                  FrameLayout fl = (FrameLayout) ((LinearLayout) ((TextView) v).getParent()).getChildAt(1);
                                  // fl.setId(gen_id());
                                  // ((TextView) v).setText("hello");

                                  if (fl != null) {
                                      if (fl.getVisibility() == View.GONE) {
//                                          String floor = ((TextView) ((ViewGroup) v.getParent())
//                                                  .findViewById(R.id.ItemTitle))
//                                                  .getText().toString();

                                          FragmentManager fm = getFragmentManager();

                                          if(fm.getBackStackEntryCount() > 0)
                                              fm.popBackStack();

                                          FragmentTransaction ft = fm.beginTransaction();

                                          if(fl.getId() == -1)
                                              fl.setId(gen_id());

                                          ft.add(fl.getId() , new SubjectFragment()
                                                  , "frag" + fl.getId());

                                          //ft.addToBackStack("frag" + fl.getId());

                                          ft.commit();
                                          fm.executePendingTransactions();

                                          fl.setVisibility(View.VISIBLE);
                                          Log.d("info", "fl.getId() > " + fl.getId());
                                          Log.d("info", "fl.getVisiblilty() before adjust1 > " + fl.getVisibility());

                                          fl.measure(0, 0);
                                          Log.d("info", "fl.getMeasuredHeight() > " + fl.getMeasuredHeight());
                                          subjectHeight = fl.getMeasuredHeight();

                                          Log.d("info", "fm.getBackStackEntryCount() > " +fm.getBackStackEntryCount());
                                          //fm.popBackStack();

                                          adjustListView();
                                      } else {
                                          fl.setVisibility(View.GONE);
                                          //getFragmentManager().popBackStack();

                                          Log.d("info", "fl.getVisiblilty() before adjust2 > " + fl.getVisibility());
                                          subjectHeight = 0;
                                          adjustListView();
                                      }
                                  }
                              }
                          });
                      }



                      return false;
                  }
              }
        );*/

        return view;
    }

    private void adjustListView(){
        ViewGroup.LayoutParams params = lv.getLayoutParams();
        params.height = MeasureHeightOfView(lv, adapter);
        lv.setLayoutParams(params);
        lv.requestLayout(); // 上報重新設置
    }

    /*計算客製listView元件的高度*/
    private int MeasureHeightOfView(ViewGroup v, SimpleAdapter adapter){

        int numberOfItems = adapter.getCount();

        // Get total height of all items.
        int totalItemsHeight = 0;

        Log.d("info", "@: numberOfItems > " + numberOfItems);

        // 總和每個entry高度
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            // 是有否種可能性是每個item套用不同的布局？
            // get entry
            View item = adapter.getView(itemPos, null, v);

            if(item instanceof LinearLayout)
                Log.d("info", "item instanceof LinearLayout");
            // assertion: item instanceof TextView
            View item2;
            FrameLayout fl = (FrameLayout)((ViewGroup)item).getChildAt(1);
            Log.d("info", "fl.getId() > " + fl.getId());

//            if(fl.getVisibility() == View.VISIBLE){
//                Log.d("info", "fl.getVisibility() == View.VISIBLE");
//                item2 = (LinearLayout)fl.getChildAt(0);
//                if(item2 != null){
//                    item2.measure(0, 0);
//                    Log.d("info", "item2 not null");
//                }
//            }


//            if(item2 == null)
//                Log.d("null", "item2 == null");
//            else
//                Log.d("null", "item2 != null");


            item.measure(0, 0);


            totalItemsHeight += item.getMeasuredHeight() ; // + (item2 == null?0:item2.getMeasuredHeight());
        }

        Log.d("info", "@: total Height > " + totalItemsHeight);

        // Get total height of all item dividers.
        // => height between each item
        // maybe we can set the value
        int totalDividersHeight = ((ListView)v).getDividerHeight() *
                (numberOfItems - 1);

        return totalItemsHeight + totalDividersHeight + subjectHeight;
    }

    public static AreaFragment getInstance(String[] contents, int floor){
//        area_contents  = Arrays.toString(contents);
        area_contents = contents;
        AreaFragment.floor = floor;
        return new AreaFragment();
    }


}
