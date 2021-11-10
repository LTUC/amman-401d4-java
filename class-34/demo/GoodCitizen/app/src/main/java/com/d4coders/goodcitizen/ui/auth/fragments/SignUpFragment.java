package com.d4coders.goodcitizen.ui.auth.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amplifyframework.api.graphql.model.ModelMutation;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.datastore.generated.model.User;
import com.d4coders.goodcitizen.R;
import com.d4coders.goodcitizen.ui.auth.AuthActivity;
import com.d4coders.goodcitizen.ui.dashboard.MainActivity;
import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "SignUpFragment";
    private static final String USER_ID = "userId";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText yourNameEditText;
    private EditText yourEmailEditText;
    private final View.OnClickListener listener = view -> {
        String name = yourNameEditText.getText().toString();
        String email = yourEmailEditText.getText().toString();
        String[] flNames = name.split(" ");
        createNewUser(flNames[0], flNames[1], "1234567890", email, "password", "962-555-5555");
    };

    private OnSignUpComplete signUpCompleteListener;

    private SharedPreferences sharedPref;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view,
                              @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getActivity() != null) {
            yourNameEditText = getActivity().findViewById(R.id.et_name);
            yourEmailEditText = getActivity().findViewById(R.id.et_email);
            Button registerButton = getActivity().findViewById(R.id.btn_register);
            registerButton.setOnClickListener(listener);

            sharedPref = getActivity().getSharedPreferences("userData", Context.MODE_PRIVATE);
        }
    }

    private void createNewUser(String firstName,
                               String lastName,
                               String nationalId,
                               String email,
                               String password,
                               String number) {
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .nationalId(nationalId)
                .email(email)
                .number(number)
                .build();

        Amplify.API.mutate(
                ModelMutation.create(user),
                userSaved -> {
                    Log.i(TAG, "User saved");
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(USER_ID, userSaved.getData().getId());
                    editor.apply();

//                    signUpCompleteListener.signUpCompleted();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("a", "ABC");
                    bundle.putString("b", "123");
                    bundle.putString("c", "JKL");
                    bundle.putString("d", "XYZ");
                    bundle.putString("userId", userSaved.getData().getId());
                    intent.putExtra("bundle", bundle);
                    startActivity(intent);
                },
                error -> {
                    Log.e(TAG, "User not Saved => " + error);
                }
        );
    }

    public void setSignUpListener(OnSignUpComplete listener) {
        signUpCompleteListener = listener;
    }

    public interface OnSignUpComplete {
        void signUpCompleted();
    }
}