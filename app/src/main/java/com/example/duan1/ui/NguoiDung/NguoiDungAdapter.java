package com.example.duan1.ui.NguoiDung;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.R;
import com.example.duan1.ui.CaNhan.Use;
import com.example.duan1.ui.CaNhan.UseDAO;

import java.util.ArrayList;

public class NguoiDungAdapter extends BaseAdapter {
    Context context;
    ArrayList<Use> dsuse;
    UseDAO useDAO;

    public NguoiDungAdapter(Context context, ArrayList<Use> dsuse) {
        this.context = context;
        this.dsuse = dsuse;
        useDAO = new UseDAO(context);
    }
    @Override
    public int getCount() {
        return dsuse.size();
    }

    @Override
    public Object getItem(int position) {
        return dsuse.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }


    public class ViewHolder extends AppCompatActivity {
        ImageView imgnd;
        TextView tvName, tvUse;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if(convertView==null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_nguoi_dung, null);
            holder.imgnd = convertView.findViewById(R.id.imgnd);
            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.tvUse = convertView.findViewById(R.id.tvUse);

            convertView.setTag(holder);

        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        final Use entry = (Use) dsuse.get(position);
        /*Chuyển IMG định dạng byte >> về dạng bipmap*/
        byte[] hinh = entry.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
        /*gán hình*/holder.imgnd.setImageBitmap(bitmap);

        holder.tvName.setText(entry.getName());
        holder.tvUse.setText(entry.getUse());

        return convertView;
    }


    public void changeDataset(ArrayList<Use> items){
        this.dsuse = items;
        notifyDataSetChanged();
    }

}