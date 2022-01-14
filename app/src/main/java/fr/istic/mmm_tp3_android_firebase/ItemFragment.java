package fr.istic.mmm_tp3_android_firebase;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

import fr.istic.mmm_tp3_android_firebase.databinding.FragmentItemListBinding;
import fr.istic.mmm_tp3_android_firebase.manager.UserManager;


public class ItemFragment extends Fragment {

    private FragmentItemListBinding binding;
    private RecyclerView recyclerView;
    public UserAdaptaterRecyclerView  adapter; // Create Object of the Adapter class
    FirebaseFirestore dbToRecyc = FirebaseFirestore.getInstance();
    UserManager userManager=UserManager.getInstance();
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
    protected String mBirthday;
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
            mBirthday = getArguments().getString(ARG_PARAM3);
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
        // Set the adapter
        Log.i("fffggkjklhk","navigate to frag2");
        recyclerView = binding.list;
        // To display the Recycler view linearly
        Context context = view.getContext();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirestoreRecyclerOptions<User> options
                = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(userManager.getAllUsersForRecyclerView(), User.class)
                .build();

        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new UserAdaptaterRecyclerView(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);
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
    // Function to tell the app to start getting
    // data from database on starting of the activity
    public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

}
