package fr.istic.mmm_tp3_android_firebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import fr.istic.mmm_tp3_android_firebase.User;
import fr.istic.mmm_tp3_android_firebase.databinding.FragmentItemBinding;

public class UserAdaptaterRecyclerView extends FirestoreRecyclerAdapter<
        User, UserAdaptaterRecyclerView.ViewHolder>{

    public UserAdaptaterRecyclerView(@NonNull FirestoreRecyclerOptions options) {
        super(options);
    }

    // Function to tell the class about the Card view (here
    // "fragment_item.xml")in
    // which the data will be shown
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position,@NonNull User model) {
        //holder.mItem = mValues.get(position);
       // User currentUser = mValues.get(position);
        holder.firstName.setText(model.getFirstName());
        holder.lastName.setText(model.getLastName());
        holder.birthday.setText(model.getBirthday());
        holder.birthdayPlaceArray.setText(model.getBirthdayPlaceArray());

        //holder.mContentView.setText(mValues.get(position).content);
    }
    @NonNull
    @Override
public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    //Ici, nous allons créer une instance de ViewHolder
    return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater
            .from(parent.getContext()), parent, false));

}



    // Sub Class to create references of the views in Crad
    // view (here "fragment_item.xml")
    public class ViewHolder extends RecyclerView.ViewHolder {

        //public final TextView mContentView;
        private TextView firstName;
        private TextView lastName;
        private TextView birthday;
        private TextView birthdayPlaceArray;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            firstName = binding.firstName;
            lastName = binding.lastName;
            birthday = binding.birthday;
            birthdayPlaceArray = binding.birthdayPlaceArray;
            //mContentView = binding.content;
        }

        @Override
        public String toString() {
            return "ViewHolder{" +
                    "firstName=" + firstName +
                    ", lastName=" + lastName +
                    ", birthday=" + birthday +
                    ", birthdayPlaceArray=" + birthdayPlaceArray +
                    '}';
        }

    }
}