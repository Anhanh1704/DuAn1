package com.example.duan1.ui.CaNhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.MainActivity;
import com.example.duan1.R;
import com.example.duan1.ui.NguoiDung.ListNguoiDungActivity;

public class LoginActivity extends AppCompatActivity {
    EditText edUsename, edPassword;
    Button btnLogin;
    TextView tvhuy, tvDangKy;
    CheckBox chkRememberPass;
    UseDAO useDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("ĐĂNG NHẬP");
        edUsename = (EditText) findViewById(R.id.edUsename);
        edPassword = (EditText) findViewById(R.id.edPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvhuy = (TextView) findViewById(R.id.tvhuy);
        tvDangKy = (TextView) findViewById(R.id.tvDangKy);
        chkRememberPass = (CheckBox) findViewById(R.id.chkRememberPass);


        layThongTin();

    }

    public void huy(View view){
        edPassword.setText("");
        edUsename.setText("");
    }
    public void login(View v){
        useDAO = new UseDAO(LoginActivity.this);
        String strUser = edUsename.getText().toString();
        String strPass = edPassword.getText().toString();
        Use use = useDAO.getUsepass(strUser);

        if (strUser.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập không được bỏ trống",
                    Toast.LENGTH_SHORT).show();
        }else if (strPass.isEmpty()){
            Toast.makeText(getApplicationContext(),"Mật khẩu không được bỏ trống",
                    Toast.LENGTH_SHORT).show();
        }
        if (strUser.equalsIgnoreCase("admin")&&strPass.equalsIgnoreCase("admin")){
            startActivity(new Intent(LoginActivity.this, ListNguoiDungActivity.class));
        }else if (use!= null){
            if (use.getPass().equals(strPass)) {
                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                luuThongTin();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            } else {
                Toast.makeText(LoginActivity.this, "Tên tài khoản hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText( LoginActivity.this, "Tài khoản Không Tồn tại!!!", Toast.LENGTH_SHORT).show();
        }


    }
    private void luuThongTin() {
        SharedPreferences sharedPreferences = getSharedPreferences("TKMK", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String use = edUsename.getText().toString();
        String pass = edPassword.getText().toString();
        boolean check = chkRememberPass.isChecked();
        if (!check) {
            editor.clear();
            editor.putString("name", use);
        } else {
            editor.putString("use", use);
            editor.putString("password", pass);
            editor.putBoolean("checkstatus", check);
            editor.putString("name", use);
        }
        editor.commit();
    }

    private void layThongTin() {
        SharedPreferences sharedPreferences = getSharedPreferences("TKMK", MODE_PRIVATE);

        boolean check = sharedPreferences.getBoolean("checkstatus", false);
        if (check) {
            String tenNguoiDung = sharedPreferences.getString("use", "");
            String matKhau = sharedPreferences.getString("password", "");
            edUsename.setText(tenNguoiDung);
            edPassword.setText(matKhau);
        } else {
            edUsename.setText("");
            edPassword.setText("");
        }
        chkRememberPass.setChecked(check);
    }

    public void dangky(View view){
        startActivity(new Intent(LoginActivity.this, DangKyActivity.class));
    }

}