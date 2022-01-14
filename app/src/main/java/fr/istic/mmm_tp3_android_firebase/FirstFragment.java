package fr.istic.mmm_tp3_android_firebase;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.*;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import fr.istic.mmm_tp3_android_firebase.databinding.FragmentFirstBinding;
import fr.istic.mmm_tp3_android_firebase.manager.UserManager;
import fr.istic.mmm_tp3_android_firebase.repository.UserRepository;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.List;

public class FirstFragment extends Fragment {
    private FragmentFirstBinding binding;
    private Spinner spinner;
    private List<Departement> departementList;
    // creating a variable
    // for firebasefirestore.
    private FirebaseFirestore db;
    //Creating a variable for UserManager
    UserManager userManager= UserManager.getInstance();
    /********************/
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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


    public FirstFragment() {
        // Required empty public constructor
    }


    public static FirstFragment newInstance(String firstName, String lastName, String birthday, String birthdayPlaceArray) {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, firstName);
        args.putString(ARG_PARAM2, lastName);
        args.putString(ARG_PARAM3, birthday );
        args.putString(ARG_PARAM4, birthdayPlaceArray);
        fragment.setArguments(args);
        return fragment;
    }





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFirstName = getArguments().getString(ARG_PARAM1);
            mLastName = getArguments().getString(ARG_PARAM2);
            mBirthday = getArguments().getString(ARG_PARAM3);
            mBirthdayPlaceArray = getArguments().getString(ARG_PARAM4);
        }
    }



    /*****************************/
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        /*
        if (getArguments() != null) {
            mFirstName = getArguments().getString(ARG_PARAM1);
            mLastName = getArguments().getString(ARG_PARAM2);
            // mBirthday = getArguments().getString(ARG_PARAM3);
            mBirthdayPlaceArray = getArguments().getString(ARG_PARAM4);
        }*/

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        /*no below lines in this project*/
        // Inflate the layout for this fragment
        // View v = inflater.inflate(R.layout.fragment_first, container, false);

        return binding.getRoot();
        /*******************/


    }



    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("test","navigate to frag2");
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_ItemFragment);
                //onButtonFirstPressed(null);


            }

        });
        // return v;
/************************/

        // getting our instance
        // from Firebase Firestore.
        db = FirebaseFirestore.getInstance();



        // MC: once the view is created, we can initialize the data (here in First Fragment)
        userData = new ViewModelProvider(requireActivity()).get(SharedInfoVM.class);

        // and set a value to it. De note that as soon as setdata is called, all the interfaces connected to the Viewmodel are notified.
        // So if there's no other fragment, then nothing happens, but if the Fragment2 is displayed at the same time (eg on a tablet), then
        // the second fragment is updated!
        userData.setdata(new User("Bienvenue", "Sotto", null,"Cassablanca"));






        /*******/

        //lastNameArray = view.findViewById (R.id.LastNameArray);
        binding.buttonValidator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                EditText lastName=binding.lastNameArray;
                String recupLastName = lastName.getText().toString ();
                Log.i("Tag", "test lastNameArray" + recupLastName);
                if(TextUtils.isEmpty(recupLastName)) {
                    lastName.setError("Veuillez saisir un nom de famile");
                    return;
                }
                EditText firstName=binding.firstnameArray;
                String  recupFirstName = firstName.getText().toString ();
                if(TextUtils.isEmpty(recupFirstName)) {
                    firstName.setError("Veuillez saisir un nom de prénom");
                    return;
                }
                Log.i("Tag", "test firstnameArray" + recupFirstName);

                String birthdayPlaceArray = binding.birthdayPlaceArray.getText().toString ();
                Log.i("Tag", "test birthdayPlaceArray" + birthdayPlaceArray);

                String recupBirthday = binding.birthdaytext.getText().toString ();
                Log.i("Tag", "test recupBirthday" + recupBirthday);


                // calling method to add data to Firebase Firestore.
                //addDataToFirestore(recupLastName, recupFirstName, birthdayPlaceArray,birthdayPlaceArray);

                userManager.createUser(new User(recupLastName, recupFirstName, recupBirthday,birthdayPlaceArray));

                Toast.makeText(getActivity().getApplicationContext(),
                        recupLastName+ " "+ recupFirstName+ " "+birthdayPlaceArray+ " " +"has been saved successfully!", Toast.LENGTH_SHORT ).show();
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_ItemFragment);

            }

        });




        /*Partie spinner*/

        // Data:
        this.departementList = DepartementDataUtils.getDeparteement();
        this.spinner = binding.spinnerDepartement;




        // Adapter"
        CustomerAdaptater adapter = new CustomerAdaptater(FirstFragment.this,
                R.layout.spinner_item_layout_resource,
                R.id.textViewItemName,
                this.departementList);

        this.spinner.setAdapter(adapter);


    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment1, menu);
        super.onCreateOptionsMenu(menu,inflater);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.clear:
                EditText firstName= binding.firstnameArray;
                EditText clearLarstName =binding.lastNameArray;
                EditText birthdaytext =binding.birthdaytext;
                /*Cherche comment instancier une variable de type menu*/
                Button numberPhoneM= binding.numberPhone;
                firstName.getText().clear();
                clearLarstName.getText().clear();
                birthdaytext.getText().clear();
                return true;
            case R.id.numberPhone:
                binding.numberPhone.setVisibility(View.VISIBLE);
                return true;


            case R.id.wikipedia:
                EditText departement = binding.birthdayPlaceArray;
                Intent intentWikepia = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://fr.wikipedia.org/wiki/" + departement.getText()));
                startActivity(intentWikepia);


        }

        return super.onOptionsItemSelected(item);
    }
    /**********************************/




    public void onButtonFirstPressed(Uri uri) {

    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }


    /**************************/

    /*Méthode pour afficher le bouton de saisie de numéro*/
    public void afficherBoutonNumber(View view){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

            /*Methode to storage the data on Firebase*/
    private void addDataToFirestore(String firstName, String lastName, String birthday, String birthdayPlaceArray) {

        // creating a collection reference
        // for our Firebase Firetore database.
        CollectionReference dbUsers = db.collection("users");

        // adding our data to our users object class.
        User users = new User(firstName, lastName,birthday,birthdayPlaceArray);

        // below method is use to add data to Firebase Firestore.
        dbUsers.add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Toast.makeText(getActivity().getApplicationContext(), "Your User has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Toast.makeText(getActivity().getApplicationContext(), "Fail to add User \n" + e, Toast.LENGTH_SHORT).show();
            }
        });
    }

}