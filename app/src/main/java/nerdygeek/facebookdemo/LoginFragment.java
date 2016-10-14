package nerdygeek.facebookdemo;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

/**
 * Project - FacebookDemo
 * Package - nerdygeek.facebookdemo
 * <p>
 * Victor Mwenda
 * +254(0)718034449
 * vmwenda.vm@gmail.com
 * <p>
 * Android App Development Laptop
 * Created by victor on 10/14/2016 at 3:36 PM.
 */

public class LoginFragment extends Fragment {

    interface LoginFragmentCallbacks {
        void onCreateFragment();
    }

    LoginFragmentCallbacks loginFragmentCallbacks;
    MainActivity activity;
    private LoginButton loginButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (MainActivity) getActivity();

        FacebookSdk.sdkInitialize(getActivity());


        getMainActivity().callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(getMainActivity().getCallbackManager(),
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Toast.makeText(getMainActivity(), "Success " + loginResult.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Toast.makeText(getMainActivity(), "Cancelled ", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                        Toast.makeText(getMainActivity(), "Exception " + exception.toString(), Toast.LENGTH_LONG).show();
                    }
                });
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        loginFragmentCallbacks = (LoginFragmentCallbacks) getMainActivity();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        loginButton.registerCallback(getMainActivity().getCallbackManager(), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(getMainActivity(), "Success " + loginResult.toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(getMainActivity(), "Cancelled ", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(getMainActivity(), "Exception " + exception.toString(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public MainActivity getMainActivity() {
        return activity;
    }
}
