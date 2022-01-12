package online.thrift.shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    //variables
    public static final String TAG = SignupActivity.class.getSimpleName();
    Button login, signupBtn;
    TextInputLayout email, password, confirmPassword;

    @BindView(R.id.signupBtn) Button mSignupButton;
    @BindView(R.id.email) TextInputLayout mEmailEdit;
    @BindView(R.id.password) TextInputLayout mPassword;
    @BindView(R.id.confirmPassword)
    TextInputLayout mConfirmPassword;
    @BindView(R.id.login)
    Button mLogin;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This line will hide the status bar from the screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_signup);

        ButterKnife.bind(this);

        // Initializing Firebase
        mAuth = FirebaseAuth.getInstance();

        createAuthStateListener();


        mLogin.setOnClickListener(this);
        mSignupButton.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {
        if (view == login) {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        if (view == signupBtn) {
            createNewUser();
        }
    }

    private void createNewUser(){
        final String email = mEmailEdit.toString().trim();
        String password = mPassword.toString().trim();
        String confirmPassword = mConfirmPassword.toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validPassword) return;

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "Authentication successful");
                    } else {
                        Toast.makeText(SignupActivity.this, "authentication failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail){
            mEmailEdit.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }

    public boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mPassword.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mPassword.setError("Passwords do not match");
            return false;
        }
        return false;
    }

    public void createAuthStateListener(){
        mAuthListener = firebaseAuth -> {
            final FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        };
    }

    public void onStart() {
        super.onStart();
        //Checking if user is signed in (non-null) and update UI accordingly.
       mAuth.addAuthStateListener(mAuthListener);
    }
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}