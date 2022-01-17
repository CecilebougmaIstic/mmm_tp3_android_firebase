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
import com.google.firestore.v1.WriteResult;


import java.util.List;
import java.util.concurrent.ExecutionException;

import fr.istic.mmm_tp3_android_firebase.User;

public class UserRepository {
    public static final String COLLECTION_NAME = "users";
    private static volatile  UserRepository instance;

    public UserRepository() { }
    public static UserRepository getInstance(){
        UserRepository result = instance;
        if(result!=null){
            return  result;
        }
        synchronized (UserRepository.class){
            if(instance==null){
                instance = new UserRepository();
            }
            return instance;
        }

    }


    FirebaseFirestore db = FirebaseFirestore.getInstance();

    // Get the Collection Reference
    private CollectionReference getUsersCollection(){
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }



    public void createUser(User user){
        // creating a collection reference
        // for our Firebase Firetore database
        CollectionReference dbUsers = db.collection("users");
        // adding our data to our users object class.

        //User users = new User(user.getFirstName(), user.getLastName(),user.getBirthday(),user.getBirthday());
        // below method is use to add data to Firebase Firestore.
        Log.i("Tag", "les informations du User à enregistrer Dans la Base de données Firestore:" + user);
        dbUsers.add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
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
/*

    // Update des Users
    public String updateUser(User user) {
       // String uid = this.getCurrentUserUID();
        if(user.getFirstName() != null){
            return this.getUsersCollection().document(user.getFirstName()).set(user).toString();
        }else{
            return "Veuillez Saisir le nom de Famille de l'utilisateur à modifier";
        }
    }*/
public  Task<Void>  updateUser(User user) {
    // String uid = this.getCurrentUserUID();
    if(user.getFirstName() != null){
        return this.getUsersCollection().document(user.getFirstName()).set(user);
    }else{
        return null;
    }
}


    // Delete des Users
    public String deleteUser(String firstName) {
         String uid = this.getUserIdinFirestore(firstName);
        //CollectionReference users = db.collection(COLLECTION_NAME);
      //  Query query = users.whereEqualTo(firstName,firstName);
      /*  Task<QuerySnapshot> querySnapshot = query.get();
        List<QueryDocumentSnapshot> document =querySnapshot.getResult().getDocuments();*/
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
    public Task<Void> deleteUser1(User user) {
        String uid = this.getUserIdinFirestore(user.getFirstName());
        System.out.println(user.getFirstName());
          if(uid != null){
             return this.getUsersCollection().document(uid).delete();
        }else {
              return null;
          }

    }

    public Query getAllUsersForRecyclerView(){
        return this.getUsersCollection()
                .orderBy("firstName")
                .limit(50);
    }


}
