package com.fzn.classsign.activitys.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fzn.classsign.R;
import com.fzn.classsign.fragment.student.ClassFragment;
import com.fzn.classsign.fragment.student.MeFragment;
import com.fzn.classsign.fragment.student.SignFragment;

import java.util.ArrayList;
import java.util.List;

public class StudentFragmentActivity extends AppCompatActivity {


    FrameLayout mFl;
    RadioGroup mRg;
    private FragmentManager mFragmentManager;

    private int position = 1;

    private List<Fragment> mFragments;
    private Fragment mCurFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_fragment);

        mFl = (FrameLayout) findViewById(R.id.fl);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mFragments = new ArrayList<>();
        SignFragment signFragment = SignFragment.newInstance("", "");
        ClassFragment classFragment = ClassFragment.newInstance();
        MeFragment meFragment = MeFragment.newInstance(1);
        mFragments.add(signFragment);
        mFragments.add(classFragment);
        mFragments.add(meFragment);
        mCurFragment = mFragments.get(position);
        replaceFragment(mCurFragment);

        ((RadioButton) mRg.getChildAt(position)).setChecked(true);

        initListener();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Intent intent = getIntent();
        int posi = intent.getIntExtra("POSITION", -1);
        if (posi != -1) {
            position = posi;
            ((RadioButton) mRg.getChildAt(position)).setChecked(true);
        }
    }

    private void initListener() {
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);

                if (false == radioButton.isChecked()) {
                    return;
                }

                switch (checkedId) {
                    case R.id.rb_sign:
                        position = 0;
                        break;

                    case R.id.rb_class:
                        position = 1;
                        break;

                    case R.id.rb_me:
                        position = 2;
                        break;
                    default:
                        position = 0;
                        break;

                }
                showFragmentOf(position);
            }

        });
    }


    private void showFragmentOf(int position) {
        Fragment to = mFragments.get(position);
        showFragment(mCurFragment, to);
        mCurFragment = to;
        ((RadioButton) mRg.getChildAt(position)).setChecked(true);
    }

    private void showFragment(Fragment from, Fragment to) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        if (!to.isAdded()) {    // 先判断是否被add过
            transaction.hide(from).add(R.id.fl, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }

    }

    /**
     * 这个方法用老替换fragment
     * xujun
     * 2016/5/3 17:28.
     */

    private void replaceFragment(Fragment fragmeny) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl, fragmeny).commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (position != 1) {
                showFragmentOf(1);
                return true;
            } else {
                Intent home=new Intent(Intent.ACTION_MAIN);
                home.addCategory(Intent.CATEGORY_HOME);
                startActivity(home);
                return true;
            }
        }
        return false;
    }
}