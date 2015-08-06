package corp.hiccup168.eggproject;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLayer2 extends Fragment {
    static String[] area_contents;

    public FragmentLayer2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_fragment_layer2, container, false);
        View view = inflater.inflate(
                R.layout.fragment_fragment_layer2,
                container,
                false
        );

//        ((TextView)view.findViewById(R.id.myTextView))
//                .setText(area_contents);

//        String[] values = new String[] { "Android List View",
//                "Adapter implementation",
//                "Simple List View In Android",
//                "Create List View Android",
//                "Android Example",
//                "List View Source Code",
//                "List View Array Adapter",
//                "Android Example List View"
//        };

        // Using system-defined ArrayAdpater
//        ArrayAdapter<String> itemsAdapter =
//                new ArrayAdapter<String>((MainActivity)getActivity(), android.R.layout.simple_list_item_1, area_contents);

        // Using custom ArrayAdpater
        // ListView的設計圖: entry的布局 與 資料(圖、文)
        CustomListAdapter<String> itemsAdapter =
                new CustomListAdapter<String>((MainActivity)getActivity(), R.layout.custom_list, area_contents);

        ListView listview  = (ListView)view.findViewById(R.id.myAreaListView);
        listview.setScrollContainer(false);
        listview.setAdapter(itemsAdapter);

        // Set list height.
        ViewGroup.LayoutParams params = listview.getLayoutParams();
        params.height = MeasureHeightOfView(listview, itemsAdapter);
        listview.setLayoutParams(params);
        listview.requestLayout(); // 上報重新設置

        /*接上fragment layer 3: 主題*/
        listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // Log.d("info", "@: parent > " + ((ListView)view instanceof  ListView));
                        Log.d("info", "@: parent > " + parent.getClass().getName());
                        Log.d("info", "@: view > " + (view instanceof TextView));
                        Log.d("info", "@: TextView text > " + ((TextView)view).getText());
                        Log.d("info", "@: clicked postion and id > " + position + ", " + id);
                        // assuming string and if you want to get the value on click of list item
                        // do what you intend to do on click of listview row

                        ((ViewGroup)parent.getParent()).getChildAt(1).setVisibility(View.VISIBLE);



                    }
                }
        );

        return view;
    }

    /*計算客製listView元件的高度*/
    private int MeasureHeightOfView(ViewGroup v, ArrayAdapter adapter){

        int numberOfItems = adapter.getCount();

        // Get total height of all items.
        int totalItemsHeight = 0;

        Log.d("info", "@: numberOfItems > " + numberOfItems);

        // 總和每個entry高度
        for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
            // 是有否種可能性是每個item套用不同的布局？
            // get entry
            View item = adapter.getView(itemPos, null, v);
            // assertion: item instanceof TextView
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }

        Log.d("info", "@: total Height > " + totalItemsHeight);

        // Get total height of all item dividers.
        // => height between each item
        // maybe we can set the value
        int totalDividersHeight = ((ListView)v).getDividerHeight() *
                (numberOfItems - 1);

        return totalItemsHeight + totalDividersHeight;
    }

    public static FragmentLayer2 getInstance(String[] contents){
//        area_contents  = Arrays.toString(contents);
        area_contents = contents;
        return new FragmentLayer2();
    }

}
