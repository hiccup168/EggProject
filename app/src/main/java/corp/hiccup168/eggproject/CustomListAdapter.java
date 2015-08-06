package corp.hiccup168.eggproject;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by hiccup168 on 2015/8/4.
 */
public class CustomListAdapter<T> extends ArrayAdapter<T> {
//    private Context mContext;
//    private int id;
//    private List<String> items ;

    public CustomListAdapter(Context context, int textViewResourceId, T[] objects )
    {
        super(context, textViewResourceId, objects);
//        mContext = context;
//        id = textViewResourceId;
//        items = list ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        // Here all your customization on the View


        return view;
    }




//    @Override
//    public View getView(int position, View v, ViewGroup parent)
//    {
//        View mView = v ;
//        if(mView == null){
//            LayoutInflater vi = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            mView = vi.inflate(id, null);
//        }
//
//        TextView text = (TextView) mView.findViewById(R.id.textView);
//
//        if(items.get(position) != null )
//        {
//            text.setTextColor(Color.WHITE);
//            text.setText(items.get(position));
//            text.setBackgroundColor(Color.RED);
//            int color = Color.argb( 200, 255, 64, 64 );
//            text.setBackgroundColor( color );
//
//        }
//
//        return mView;
//    }


}
