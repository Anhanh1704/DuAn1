package com.example.duan1.ui.CaNhan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1.MainActivity;
import com.example.duan1.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class DangKyActivity extends AppCompatActivity {
    EditText edname, eduse, edpass, edtienconlai, edphone, edemail;
    Button btndangky, btnchonanh;
    UseDAO useDAO;
    ImageView imghinh;
    TextView link_login;
    final int REQUEST_CODE_CAMERA = 123;
    final int REQUEST_CODE_FOLDER = 456;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        edname = findViewById(R.id.edname);
        eduse = findViewById(R.id.eduse);
        edpass = findViewById(R.id.edpass);
        edtienconlai = findViewById(R.id.edtienconlai);
        edphone = findViewById(R.id.edphone);
        edemail = findViewById(R.id.edemail);
        btndangky = findViewById(R.id.btndangky);
        btnchonanh = findViewById(R.id.btnchonanh);
        imghinh = findViewById(R.id.imghinh);
        link_login = findViewById(R.id.link_login);

        useDAO = new UseDAO(DangKyActivity.this);

        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKyActivity.this, LoginActivity.class));
            }
        });

        btnchonanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(DangKyActivity.this);
                dialog.setContentView(R.layout.dialog);
                if (dialog != null && dialog.getWindow() != null){
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }

                final TextView txt_Massage = dialog.findViewById(R.id.txt_Titleconfirm);
                Button btncamera = dialog.findViewById(R.id.btn_yes);
                Button btnfolderl = dialog.findViewById(R.id.btn_no);
                final ProgressBar progressBar = dialog.findViewById(R.id.progress_loadconfirm);
                progressBar.setVisibility(View.INVISIBLE);
                txt_Massage.setText("Thêm ảnh từ:");
                btncamera.setText("CAMERA");
                btnfolderl.setText("BỘ SƯU TẬP");

                btncamera.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(/*Activity, permission, requestCode*/
                                DangKyActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                REQUEST_CODE_CAMERA
                        );
                        dialog.dismiss();
                    }
                });
                btnfolderl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ActivityCompat.requestPermissions(
                                DangKyActivity.this,
                                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                REQUEST_CODE_FOLDER
                        );
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String use = eduse.getText().toString();
                    String name = edname.getText().toString();
                    String pass = edpass.getText().toString();
                    String phone = edphone.getText().toString();
                    String email = edemail.getText().toString();
                    String tienconlai = edtienconlai.getText().toString();

                    /*Viết đọa code để cover Dạng Imageview xang dạng byte để gọi vào code*/
                    BitmapDrawable bitmapDrawable = (BitmapDrawable) imghinh.getDrawable();
                    Bitmap bitmap = bitmapDrawable.getBitmap();
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);/*kiểu hình PNG - chất lượng 100 - dữ liệu truyền vào byteArray*/
                    byte[] hinh = byteArrayOutputStream.toByteArray();/*chuyển về dạng byte*/

                    if (use.equals("")) {
                        Toast.makeText(DangKyActivity.this, "Tên đăng nhập không được để trống", Toast.LENGTH_LONG).show();
                    } else if (name.equals("")) {
                        Toast.makeText(DangKyActivity.this, "Họ và tên không được để trống", Toast.LENGTH_LONG).show();
                    } else if (pass.equals("")) {
                        Toast.makeText(DangKyActivity.this, "Mật khẩu không được để trống", Toast.LENGTH_LONG).show();
                    } else if (phone.equals("")) {
                        Toast.makeText(DangKyActivity.this, "Số điện thoại không được để trống", Toast.LENGTH_LONG).show();
                    } else if (email.equals("")) {
                        Toast.makeText(DangKyActivity.this, "Email không được để trống", Toast.LENGTH_LONG).show();
                    } else if (tienconlai.equals("")) {
                        tienconlai = "0";
                    } else {
                        Use user = new Use(use, hinh, name, pass, phone, email, tienconlai);
                        if (useDAO.addUse(user)) {
                            Toast.makeText(DangKyActivity.this, "Thêm thành công", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(DangKyActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(DangKyActivity.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_LONG).show();
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(DangKyActivity.this, "Lỗi : " + e, Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case REQUEST_CODE_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA);
                }else {
                    Toast.makeText(this, "Vui lòng cấp quyền mở CAMERA!", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_FOLDER:/*Lỗi gạch đỏ thì thêm "final" trước biến toàn cục REQUEST_CODE_FOLDER*/
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                }else {
                    Toast.makeText(this, "Vui lòng cấp quyền mở Thư viện ảnh!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imghinh.setImageBitmap(bitmap);
        }
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imghinh.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}