package fr.istic.mmm_tp3_android_firebase.repository;


import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.List;

import fr.istic.mmm_tp3_android_firebase.User;

public class UserRepository {
    public static final String COLLECTION_NAME = "users";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Get the Collection Reference
    private CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

/*
    // Create User in Firestore
    public void createUser() {
        FirebaseUser user = getCurrentUser();
        if(user != null){
            String urlPicture = (user.getPhotoUrl() != null) ? user.getPhotoUrl().toString() : null;
            String username = user.getDisplayName();
            String uid = user.getUid();

            User userToCreate = new User(uid, username, urlPicture);

            Task<DocumentSnapshot> userData = getUserData();
            // If the user already exist in Firestore, we get his data (isMentor)
            userData.addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.contains(IS_MENTOR_FIELD)){
                    userToCreate.setIsMentor((Boolean) documentSnapshot.get(IS_MENTOR_FIELD));
                }
                this.getUsersCollection().document(uid).set(userToCreate);
            });
        }
    }

    // Get User Data from Firestore
    public Task<DocumentSnapshot> getUserData(){
        String uid = this.getCurrentUserUID();
        if(uid != null){
            return this.getUsersCollection().document(uid).get();
        }else{
            return null;
        }
    }
*/

    public void createUser(User user){
        // creating a collection reference
        // for our Firebase Firetore database
        CollectionReference dbUsers = db.collection("users");
        // adding our data to our users object class.
        User users = new User(user.getFirstName(), user.getLastName(),user.getBirthday(),user.getBirthday());
        // below method is use to add data to Firebase Firestore.
        dbUsers.add(users).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                // after the data addition is successful
                // we are displaying a success toast message.
                Log.i("Tag", "Your User has been added to Firebase Firestore" + user);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // this method is called when the data addition process is failed.
                // displaying a toast message when data addition is failed.
                Log.i("Tag", "Fail to add User \n" + user);

            }
        });

        }

    // Get User Data from Firestore
    public Task<DocumentSnapshot> getUserData(String firstName){
        String uid = this.getUserIdinFirestore(firstName);
        if(uid != null){
            return this.getUsersCollection().document(uid).get();
        }else{
            return null;
        }
    }


    // Update des Users
    public String updateUser(User user) {
       // String uid = this.getCurrentUserUID();
        if(user.getFirstName() != null){
            return this.getUsersCollection().document(user.getFirstName()).set(user).toString();
        }else{
            return "Veuillez Saisir le nom de Famille de l'utilisateur à modifier";
        }
    }


    // Delete des Users
    public String deleteUser(String firstName) {
         String uid = this.getUserIdinFirestore(firstName);
        //CollectionReference users = db.collection(COLLECTION_NAME);
      //  Query query = users.whereEqualTo(firstName,firstName);
      /*  Task<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> document =querySnapshot.getResult().getDocuments().getDocuments();*/
        /*
        if(firstName != null){
            return this.getUsersCollection().document(firstName).delete().toString();
        }else{
            return "Veuillez Saisir le nom de Famille de l'utilisateur à supprimer";
        }*/
        if(uid != null){
           return this.getUsersCollection().document(uid).delete().toString();
        }else{
            return "Veuillez Saisir le nom de Famille de l'utilisateur à supprimer";
        }
    }

    public String getUserIdinFirestore(String firstName){
        CollectionReference users = db.collection(COLLECTION_NAME);
          Query query = users.whereEqualTo(firstName,firstName);
          if(query !=null){
              String userId=query.get().toString();
          return  userId;
        }else{ return "Veuillez saisir un nom de famille";
          }

    }
}