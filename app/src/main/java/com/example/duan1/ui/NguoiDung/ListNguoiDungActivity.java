package com.example.duan1.ui.NguoiDung;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.duan1.R;
import com.example.duan1.ui.CaNhan.Use;
import com.example.duan1.ui.CaNhan.UseDAO;

import java.util.ArrayList;

public class ListNguoiDungActivity extends AppCompatActivity {
    public static ArrayList<Use> dsuse;
    ListView lvUse;
    NguoiDungAdapter adapter;
    UseDAO useDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("DANH SÁCH NGƯỜI DÙNG");
        setContentView(R.layout.activity_list_nguoi_dung);

        lvUse = (ListView) findViewById(R.id.lvUse);
        registerForContextMenu(lvUse);
        dsuse = new ArrayList<>();
        useDAO = new UseDAO(ListNguoiDungActivity.this);

        dsuse = useDAO.getallUse();
        adapter = new NguoiDungAdapter(ListNguoiDungActivity.this, dsuse);
        lvUse.setAdapter(adapter);

        lvUse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog = new Dialog(ListNguoiDungActivity.this);
                dialog.setContentView(R.layout.dialog_nguoi_dung);

                ImageView imghinh = dialog.findViewById(R.id.imghinh);
                EditText edname = dialog.findViewById(R.id.edname);
                EditText eduse = dialog.findViewById(R.id.eduse);
                EditText edpass = dialog.findViewById(R.id.edpass);
                EditText edtienconlai = dialog.findViewById(R.id.edtienconlai);
                EditText edphone = dialog.findViewById(R.id.edphone);
                EditText edemail = dialog.findViewById(R.id.edemail);

                /*Chuyển IMG định dạng byte >> về dạng bipmap*/
                byte[] hinh = dsuse.get(position).getHinh();
                Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
                /*gán hình*/imghinh.setImageBitmap(bitmap);

                edname.setText(dsuse.get(position).getName());
                eduse.setText(dsuse.get(position).getUse());
                edpass.setText(dsuse.get(position).getPass());
                edtienconlai.setText(dsuse.get(position).getTienconlai());
                edphone.setText(dsuse.get(position).getPhone());
                edemail.setText(dsuse.get(position).getEmail());
                dialog.show();
            }
        });
        lvUse.setTextFilterEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        dsuse.clear();
        dsuse = useDAO.getallUse();
        adapter.changeDataset(useDAO.getallUse());
        adapter.notifyDataSetChanged();
    }


}