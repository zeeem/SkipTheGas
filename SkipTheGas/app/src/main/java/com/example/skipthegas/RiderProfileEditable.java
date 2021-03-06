package com.example.skipthegas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

/**
 * This is a class that governs the editable view of the rider profile screen
 */
public class RiderProfileEditable extends AppCompatActivity {

    TextView usernameDisplay, emailDisplay;
    EditText phoneEdit, QR_Edit;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String userId;
    String username;
    String phone;
    String email;

    /**
     * onCreate method for RiderProfileEditable class
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rider_profile_edit_layout);

        // UI initiation
        usernameDisplay = findViewById(R.id.editText3);
        emailDisplay = findViewById(R.id.editText4);
        phoneEdit = findViewById(R.id.editText5);
        QR_Edit = findViewById(R.id.editText6);

        // Cloud database initiation
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        // Retrieve user information from cloud store
        assert firebaseUser != null;
        email = firebaseUser.getEmail();
        firebaseFirestore
                .collection("users")
                .document(email)
                .addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                        assert documentSnapshot != null;
                        email = documentSnapshot.getString("email");
                        phone = documentSnapshot.getString("phone");
                        username = documentSnapshot.getString("username");
                        usernameDisplay.setText(username);
                        emailDisplay.setText(email);
                        phoneEdit.setText(phone);
                    }
                });
    }

    /**
     * This cancels edit mode and does not save changes to the profile, returning the user back
     * to the read-only rider profile screen upon a button click
     * @param view
     *      Changes screens from the rider profile (editable) to the rider profile (read-only)
     */
    public void cancel(View view) {
        Intent intent = new Intent(this, RiderProfileActivity.class);
        startActivity(intent);
    }

    /**
     * This saves the edits made to the profile screen and updates the rider profile, returning
     * the user to the read-only rider profile screen with the new edited changes, upon a button
     * click
     * @param view
     * Changes screens from the rider profile (editable) to the rider profile (read-only)
     *
     * Need to finish implementing firebase update of the new edited fields
     */
    public void submitEdit(View view) {

        usernameDisplay = findViewById(R.id.editText3);
        emailDisplay = findViewById(R.id.editText4);
        phoneEdit = findViewById(R.id.editText5);
        QR_Edit = findViewById(R.id.editText6);

        Intent intent = new Intent(this, RiderProfileActivity.class);
        startActivity(intent);

//        // Cloud database initiation
//        firebaseFirestore = FirebaseFirestore.getInstance();
//        firebaseAuth = FirebaseAuth.getInstance();
//        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        assert firebaseUser != null;
//        email = firebaseUser.getEmail();
//
//        // Updates the user contact info on database
//        firebaseFirestore.collection("users").document(email).update("phone", phoneEdit.getText());
//
//        // Passes updated info back to view-only profile
    }
}
