package corp.hiccup168.eggproject;


import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends Fragment {


    public SubjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(
                R.layout.fragment_subject,
                container,
                false
        );

        // xml中已設定參數
        LinearLayout root = (LinearLayout)view.findViewById(R.id.objects_holder);

        LinearLayout.LayoutParams containerParams
                = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0.0F);

        LinearLayout.LayoutParams widgetParams
                = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.FILL_PARENT,
                1.0F);

        // add first section for object1
        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setBackgroundColor(Color.LTGRAY);
        ll.setLayoutParams(containerParams);
        root.addView(ll);

//        android:adjustViewBounds="true"
//        android:layout_centerVertical="true"
//        android:maxWidth="10dp"
//        android:maxHeight="10dp"

        ImageView iv = new ImageView(getActivity());
        iv.setImageResource(R.drawable.green_point);
        iv.setMaxHeight(100);
        iv.setMaxWidth(100);
        iv.setAdjustViewBounds(true);
        ll.addView(iv);

        TextView tv = new EditText(getActivity());
        tv.setFocusable(false);
        tv.setClickable(false);
        tv.setText("object1");
        tv.setLayoutParams(widgetParams);
        ll.addView(tv);



        // add second section for object1
        ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.HORIZONTAL);
        ll.setBackgroundColor(Color.LTGRAY);
        ll.setLayoutParams(containerParams);
        root.addView(ll);

//        android:adjustViewBounds="true"
//        android:layout_centerVertical="true"
//        android:maxWidth="10dp"
//        android:maxHeight="10dp"

        iv = new ImageView(getActivity());
        iv.setImageResource(R.drawable.gray_point);
        iv.setMaxHeight(100);
        iv.setMaxWidth(100);
        iv.setAdjustViewBounds(true);
        ll.addView(iv);

        tv = new EditText(getActivity());
        tv.setFocusable(false);
        tv.setClickable(false);
        tv.setText("object2");
        tv.setLayoutParams(widgetParams);
        ll.addView(tv);



        return view;
    }


}
