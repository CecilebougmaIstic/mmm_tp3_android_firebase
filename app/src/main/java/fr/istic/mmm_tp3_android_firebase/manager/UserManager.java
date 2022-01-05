package fr.istic.mmm_tp3_android_firebase.manager;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;

import fr.istic.mmm_tp3_android_firebase.User;
import fr.istic.mmm_tp3_android_firebase.repository.UserRepository;

public class UserManager {
    private static volatile  UserManager instance;
    private UserRepository userRepository;

    public UserManager() {
        userRepository = UserRepository.getInstance();
    }

    //Récupération  d'une instance de la classe UserManager
    public static UserManager getInstance(){
        UserManager result = instance;
        if(result!=null){
            return  result;
        }
        synchronized (UserManager.class){
            if(instance==null){
                instance = new UserManager();
            }
            return instance;
        }

    }

    public void createUser(User user){
        userRepository.createUser(user);
    }



    public Task<User> getUserData(String firstName){
        // Get the user from Firestore and cast it to a User model Object
        return userRepository.getUserData(firstName).continueWith(task -> task.getResult().toObject(User.class)) ;
    }

    public Task<Void> deleteUser(User user){
        return userRepository.deleteUser1(user);
    }

    public Task<Void> updateUsername(User user){
        return userRepository.updateUser(user);
    }

    /*Methode for the adaptater of recyclerView*/
    public Query getAllUsersForRecyclerView(){
        return userRepository.getAllUsersForRecyclerView();
    }
}
