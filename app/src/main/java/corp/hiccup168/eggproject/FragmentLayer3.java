package corp.hiccup168.eggproject;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLayer3 extends Fragment {


    public FragmentLayer3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_fragment_layer3, container, false);

        View view = inflater.inflate(
                R.layout.fragment_fragment_layer3,
                container,
                false
        );



        return view;
    }


}
