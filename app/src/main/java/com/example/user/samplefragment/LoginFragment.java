package com.example.user.samplefragment;


import android.content.SharedPreferences;
import android.os.Bundle;
// support... 는 fragment 가 나오기 전 버전을 지원하기 위함
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */

public class LoginFragment extends Fragment {

    EditText inputID;
    EditText inputPW;
    Button loginButton;
    Button signupButton;

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;


    public LoginFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);

        mPrefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        mEditor = mPrefs.edit();

        // View initialize
        inputID = (EditText) view.findViewById(R.id.inputID);
        inputPW = (EditText) view.findViewById(R.id.inputPW);
        loginButton = (Button) view.findViewById(R.id.loginButton);
        signupButton = (Button) view.findViewById(R.id.singupButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = inputID.getText().toString();
                String pw = inputPW.getText().toString();

                if(TextUtils.isEmpty(id)) {
                    // fragment는 component가 아니므로 getContext를 첫번째 인자로 변경
                    Toast.makeText(getContext(), "아이디를 입력하세요", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(pw)) {
                    Toast.makeText(getContext(), "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                }
                else {
                    // LOGIN !
                    String memberId = mPrefs.getString(SignUpFragment.LOGIN_ID, "");
                    String memberPw = mPrefs.getString(SignUpFragment.LOGIN_PW, "");

                    if(id.equals(memberId) && pw.equals(memberPw)) {
                        Toast.makeText(getContext(), "로그인 성공!", Toast.LENGTH_SHORT).show();

                        getActivity().finish();
                    }
                    else if (TextUtils.isEmpty(memberId) && TextUtils.isEmpty(memberPw)) {
                        Toast.makeText(getContext(), "가입된 아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "아이디와 비밀번호를 확인하세요.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 자신을 포함하는 activity를 불러오는 함수
                ((LoginActivity)getActivity()).pushSignupFragment();
            }
        });



        return view;
    }

}
