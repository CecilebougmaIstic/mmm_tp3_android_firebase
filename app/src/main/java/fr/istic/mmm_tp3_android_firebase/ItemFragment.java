package fr.istic.mmm_tp3_android_firebase;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import android.util.Log;
import fr.istic.mmm_tp3_android_firebase.databinding.FragmentItemListBinding;


public class ItemFragment extends Fragment {

    private FragmentItemListBinding binding;

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;


    private static final String ARG_PARAM1 = "firstName";
    private static final String ARG_PARAM2 = "lastName";
    private static final String ARG_PARAM3 = "birthday";
    private static final String ARG_PARAM4 = "birthdayPlaceArray";

    protected String mFirstName;
    protected String mLastName;
    protected Calendar mBirthday;
    protected String mBirthdayPlaceArray;

    // my Shared data between the fragments
    private SharedInfoVM userData;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mFirstName = getArguments().getString(ARG_PARAM1);
            mLastName = getArguments().getString(ARG_PARAM2);
            // mBirthday = getArguments().getString(ARG_PARAM3);
            mBirthdayPlaceArray = getArguments().getString(ARG_PARAM4);
        }

    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentItemListBinding.inflate(inflater, container, false);
        /*no below lines in this project*/
        // Inflate the layout for this fragment
        // View v = inflater.inflate(R.layout.fragment_first, container, false);

        return binding.getRoot();
        /*******************/


    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        // View view = inflater.inflate(R.layout.fragment_item_list, container, false);


/*
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new MyItemRecyclerViewAdapter(PlaceholderContent.ITEMS));
        }
*/

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("test","navigate to frag2");
                NavHostFragment.findNavController(ItemFragment.this)
                        .navigate(R.id.action_ItemFragment_to_FirstFragment);
                //onButtonFirstPressed(null);


            }

        });

    }


}
