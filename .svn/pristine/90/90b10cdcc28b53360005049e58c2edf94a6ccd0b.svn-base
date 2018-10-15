package kr.co.genexon.factconnector;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import kr.co.genexon.factconnector.implementation.ExtendWNInterface;
import m.client.android.library.core.view.AbstractActivity;

public class LockPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    TextView toolbarTitle;
    TextView tvTitle;
    TextView tvDesc;
    ImageView ivPwPoint1;
    ImageView ivPwPoint2;
    ImageView ivPwPoint3;
    ImageView ivPwPoint4;
    ImageView ivPwPoint5;
    ImageView ivPwPoint6;
    Button btnDataReset;
    Button btnDataDel;
    Button btnInput1;
    Button btnInput2;
    Button btnInput3;
    Button btnInput4;
    Button btnInput5;
    Button btnInput6;
    Button btnInput7;
    Button btnInput8;
    Button btnInput9;
    Button btnInput0;
    StringBuffer tempPassword;
    Intent getData;
    String optionStr;

    private AbstractActivity parentActivity;
    protected static final String TAG = "LockPasswordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock_password);

        getData = getIntent();
        tempPassword = new StringBuffer();

        toolbarTitle = findViewById(R.id.toolbarTitle);
        tvTitle = findViewById(R.id.tvPwTitle);
        tvDesc = findViewById(R.id.tvPwDesc);
        ivPwPoint1 = findViewById(R.id.ivPwPoint1);
        ivPwPoint2 = findViewById(R.id.ivPwPoint2);
        ivPwPoint3 = findViewById(R.id.ivPwPoint3);
        ivPwPoint4 = findViewById(R.id.ivPwPoint4);
        ivPwPoint5 = findViewById(R.id.ivPwPoint5);
        ivPwPoint6 = findViewById(R.id.ivPwPoint6);
        btnDataReset = findViewById(R.id.btnDataReset);
        btnDataDel = findViewById(R.id.btnNumberDel);
        btnInput0 = findViewById(R.id.btnInputZero);
        btnInput1 = findViewById(R.id.btnInputOne);
        btnInput2 = findViewById(R.id.btnInputTwo);
        btnInput3 = findViewById(R.id.btnInputThree);
        btnInput4 = findViewById(R.id.btnInputFour);
        btnInput5 = findViewById(R.id.btnInputFive);
        btnInput6 = findViewById(R.id.btnInputSix);
        btnInput7 = findViewById(R.id.btnInputSeven);
        btnInput8 = findViewById(R.id.btnInputEight);
        btnInput9 = findViewById(R.id.btnInputNine);

        tvTitle.setText(getData.getStringExtra("title"));
        tvDesc.setText(getData.getStringExtra("desc"));
        optionStr = getData.getStringExtra("option");
        Log.d(TAG, "option 값 = " + optionStr);
        if (optionStr.equals("0")) {
            btnDataReset.setClickable(false);
            btnDataReset.setAlpha(0.5f);
            btnDataReset.setFocusable(false);
            btnDataReset.setEnabled(false);
        } else {
            btnDataReset.setClickable(true);
            btnDataReset.setAlpha(1.f);
            btnDataReset.setFocusable(true);
            btnDataReset.setEnabled(true);
        }

        toolbarTitle.setText("비밀번호입력");
        btnDataReset.setOnClickListener(this);
        btnDataDel.setOnClickListener(this);
        btnInput0.setOnClickListener(this);
        btnInput1.setOnClickListener(this);
        btnInput2.setOnClickListener(this);
        btnInput3.setOnClickListener(this);
        btnInput4.setOnClickListener(this);
        btnInput5.setOnClickListener(this);
        btnInput6.setOnClickListener(this);
        btnInput7.setOnClickListener(this);
        btnInput8.setOnClickListener(this);
        btnInput9.setOnClickListener(this);
    }

    public void setPwTitleStr(String title, String desc, String option) {
        Log.d(TAG, "전달된 값 = " + title + ", " + desc + ", " + option);
        tvTitle.setText(title);
        tvDesc.setText(desc);
        final int optionInt = Integer.parseInt(option);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (optionInt == 0) {

                } else {

                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnDataReset) {
            Log.d(TAG, "데이터 초기화");
            deleteInfo();
        }
        if (v.getId() == R.id.btnNumberDel) {
            Log.d(TAG, "입력 데이터 지우기");
            if (tempPassword.length() == 0) {
                Toast.makeText(this, "입력된 값이 없습니다", Toast.LENGTH_SHORT).show();
            } else {
                tempPassword.deleteCharAt(tempPassword.length() - 1);
                Log.d(TAG, "남아있는 값 = " + tempPassword.toString());
                if(tempPassword.length() == 5) {
                    ivPwPoint6.setImageResource(R.drawable.num_withoutstar);
                } else if (tempPassword.length() == 4) {
                    ivPwPoint5.setImageResource(R.drawable.num_withoutstar);
                } else if (tempPassword.length() == 3) {
                    ivPwPoint4.setImageResource(R.drawable.num_withoutstar);
                } else if (tempPassword.length() == 2) {
                    ivPwPoint3.setImageResource(R.drawable.num_withoutstar);
                } else if (tempPassword.length() == 1) {
                    ivPwPoint2.setImageResource(R.drawable.num_withoutstar);
                } else if (tempPassword.length() == 0) {
                    ivPwPoint1.setImageResource(R.drawable.num_withoutstar);
                }
            }
        } else {
            if (tempPassword.length() == 6) {
                Toast.makeText(this, "6자리 모두 입력하셨습니다", Toast.LENGTH_SHORT).show();
            } else {
                switch (v.getId()) {
                    case R.id.btnInputZero:
                        Log.d(TAG, "0 입력");
                        tempPassword.append("0");
                        break;
                    case R.id.btnInputOne:
                        Log.d(TAG, "1 입력");
                        tempPassword.append("1");
                        break;
                    case R.id.btnInputTwo:
                        Log.d(TAG, "2 입력");
                        tempPassword.append("2");
                        break;
                    case R.id.btnInputThree:
                        Log.d(TAG, "3 입력");
                        tempPassword.append("3");
                        break;
                    case R.id.btnInputFour:
                        Log.d(TAG, "4 입력");
                        tempPassword.append("4");
                        break;
                    case R.id.btnInputFive:
                        Log.d(TAG, "5 입력");
                        tempPassword.append("5");
                        break;
                    case R.id.btnInputSix:
                        Log.d(TAG, "6 입력");
                        tempPassword.append("6");
                        break;
                    case R.id.btnInputSeven:
                        Log.d(TAG, "7 입력");
                        tempPassword.append("7");
                        break;
                    case R.id.btnInputEight:
                        Log.d(TAG, "8 입력");
                        tempPassword.append("8");
                        break;
                    case R.id.btnInputNine:
                        Log.d(TAG, "9 입력");
                        tempPassword.append("9");
                        break;
                }
            }
            Log.d(TAG, "입력된 값 = " + tempPassword.toString());
            if(tempPassword.length() == 1) {
                ivPwPoint1.setImageResource(R.drawable.num_star);
            } else if (tempPassword.length() == 2) {
                ivPwPoint2.setImageResource(R.drawable.num_star);
            } else if (tempPassword.length() == 3) {
                ivPwPoint3.setImageResource(R.drawable.num_star);
            } else if (tempPassword.length() == 4) {
                ivPwPoint4.setImageResource(R.drawable.num_star);
            } else if (tempPassword.length() == 5) {
                ivPwPoint5.setImageResource(R.drawable.num_star);
            } else if (tempPassword.length() == 6) {
                ivPwPoint6.setImageResource(R.drawable.num_star);
                Intent intent = new Intent();
                intent.putExtra("pwResult", tempPassword.toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    private void deleteInfo() {
        new AlertDialog.Builder(LockPasswordActivity.this)
                .setTitle("")
                .setMessage("모든 데이터를 초기화하시겠습니까?")
                .setPositiveButton("확인", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        setResult(ExtendWNInterface.LOCKSCREEN_DELETE, intent);
                        dialog.cancel();
                        finish();
                    }
                })
                .setNegativeButton("취소", new AlertDialog.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
}
