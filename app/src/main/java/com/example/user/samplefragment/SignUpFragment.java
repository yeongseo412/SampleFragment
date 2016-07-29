package com.example.user.samplefragment;


import android.content.SharedPreferences;
import android.os.Bundle;
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

public class SignUpFragment extends Fragment {

    EditText inputId;
    EditText inputpassword;
    EditText reInputpassword;
    Button firstSignupBtn;

    //파일로 저장하기 위해 ~ SP객체와 Editor 선언
    SharedPreferences mPreps;
    SharedPreferences.Editor mEditor;

    public static final String LOGIN_ID = "아이디";
    public static final String LOGIN_PW = "비밀번호";
    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        //Shared Preferences initialize
        mPreps = PreferenceManager.getDefaultSharedPreferences(getContext());
        mEditor = mPreps.edit();

        //view initialize
        inputId = (EditText)view.findViewById(R.id.input2ID);
        inputpassword = (EditText)view.findViewById(R.id.input2PW);
        reInputpassword = (EditText)view.findViewById(R.id.input2PWrpt);
        firstSignupBtn = (Button)view.findViewById(R.id.signupButton2);

        firstSignupBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String id = inputId.getText().toString();
                String password = inputpassword.getText().toString();
                String repassword = reInputpassword.getText().toString();

                if(TextUtils.isEmpty(id)){
                    Toast.makeText(getContext(),"아이디를 입력하세요",Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(password)){
                    Toast.makeText(getContext(),"비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                } else if(!password.equals(repassword)){
                    Toast.makeText(getContext(),"비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                }else{
                    //회원가입! ~ SP 사용
                    mEditor.putString(LOGIN_ID,id);
                    mEditor.putString(LOGIN_PW,password);

                    mEditor.commit();

                    //회원가입 완료하면 로그인 된 상태로 메인으로 돌아가야 하기 때문에 fragment가 소속된 Activity(LoginActivity)를 finish
                    getActivity().finish();
                }
            }
        });
        return view;
    }

}