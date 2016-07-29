package com.example.user.samplefragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // savedInstanceState 는 convertView 와 같은 역할
        // null 이면 한 번도 받은 적 없음
        // fragment stack 이 또 쌓이는 것을 방지 (최초 구동시 기본 화면)
        if(savedInstanceState == null) {

            // Activity 에서 FragmentManager 로 Fragments 를 관리
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, new LoginFragment())
                    .commit();
        }
    }

    public void pushSignupFragment() {

        // manager 에서 연산
        // replace : 기존에 add 되어있던 fragment 를 signup 으로 대체
        //          (add 도 가능하지만 웬만하면 하나의 fragment 만 두는것 권장)
        // addToBackStack : stack 에 fragment 추가 **연산**에 대한 스택의 요소를 찾기 위한 이름이 매개변수
        //          back키를 눌렀을 때 자동으로 pop, 그래서 이전 fragment 로 돌아감

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new SignUpFragment())
                .addToBackStack(null)
                .commit();
    }

    // 보통 사용하지 않는다. (back 버튼 누르면 돌아가기 때문)
    public void popFragment() {
        getSupportFragmentManager().popBackStack();
    }

}
