package com.samapps.onebanc;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.samapps.onebanc.response.OneBancResponse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final OneBancResponse listdata;
    public Adapter(OneBancResponse listdata) {
        this.listdata = listdata;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType ==0){
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.header, parent, false);
            return new HeaderViewHolder(listItem);
        }
        else{
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.list_item, parent, false);
            return new BaseViewHolder(listItem);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0){
            HeaderViewHolder vh1 = (HeaderViewHolder) holder;
            vh1.date.setText(getFormattedDate(listdata.getTransactions().get(position).getStartDate()));
            makeUI(position, vh1.amount,vh1.tid,vh1.status,vh1.relativeLayout,vh1.id_lay,vh1.can_lay,vh1.pay_lay);
        }
        else{
            BaseViewHolder vh1 = (BaseViewHolder) holder;
            makeUI(position, vh1.amount,vh1.tid,vh1.status,vh1.relativeLayout,vh1.id_lay,vh1.can_lay,vh1.pay_lay);
        }
    }

    @Override
    public int getItemViewType(int position) {
        Log.e("TAG","position "+position);
        if (position == 0){
            return 0;
        }
        else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date cuurentDate = null;
            try {
                cuurentDate = sdf.parse(listdata.getTransactions().get(position).getStartDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date prevDate = null;
            try {
                prevDate = sdf.parse(listdata.getTransactions().get((position-1)).getStartDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
            if (sdf1.format(prevDate).equals(sdf1.format(cuurentDate))){
                return 1;
            }
            else return 0;
        }

    }



    String getFormattedDate(String rawdate){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(rawdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");
        return dateFormat.format(date);
    }

    void makeUI(int position,TextView amount,TextView id, TextView status,
                 LinearLayout parent, LinearLayout id_lay, LinearLayout can_lay, LinearLayout pay_lay ){
        amount.setText("₹ "+listdata.getTransactions().get(position).getAmount().toString());
        if (listdata.getTransactions().get(position).getDirection() == 1){
            parent.setGravity(Gravity.END);
            status.setText("You paid");
        }
        if (listdata.getTransactions().get(position).getType() == 1){
            id_lay.setVisibility(View.VISIBLE);
            id.setText(String.valueOf(listdata.getTransactions().get(position).getId()));
            can_lay.setVisibility(View.GONE);
            pay_lay.setVisibility(View.GONE);
            if (listdata.getTransactions().get(position).getDirection()!=1){
                status.setText("You recieved");
            }
        }
        else {
            if (listdata.getTransactions().get(position).getDirection() == 1){
                can_lay.setVisibility(View.VISIBLE);
                pay_lay.setVisibility(View.GONE);
                status.setText("You requested");
            }
            else {
                can_lay.setVisibility(View.GONE);
                pay_lay.setVisibility(View.VISIBLE);
                status.setText("Request recieved");
            }
        }
    }



    @Override
    public int getItemCount() {
        return listdata.getTransactions().size();
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, status, tid, date;
        public LinearLayout relativeLayout, pay_lay, can_lay, id_lay;
        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.amount = (TextView) itemView.findViewById(R.id.amount);
            this.status = (TextView) itemView.findViewById(R.id.status);
            this.tid = (TextView) itemView.findViewById(R.id.tid);
            this.date = (TextView) itemView.findViewById(R.id.date);
            relativeLayout = (LinearLayout)itemView.findViewById(R.id.relativeLayout);
            pay_lay = (LinearLayout)itemView.findViewById(R.id.pay_lay);
            can_lay = (LinearLayout)itemView.findViewById(R.id.can_lay);
            id_lay = (LinearLayout)itemView.findViewById(R.id.id_lay);
        }
    }

    public static class BaseViewHolder extends RecyclerView.ViewHolder {
        public TextView amount, status, tid;
        public LinearLayout relativeLayout, pay_lay, can_lay, id_lay;
        public BaseViewHolder(View itemView) {
            super(itemView);
            this.amount = (TextView) itemView.findViewById(R.id.amount);
            this.status = (TextView) itemView.findViewById(R.id.status);
            this.tid = (TextView) itemView.findViewById(R.id.tid);
            relativeLayout = (LinearLayout)itemView.findViewById(R.id.relativeLayout);
            pay_lay = (LinearLayout)itemView.findViewById(R.id.pay_lay);
            can_lay = (LinearLayout)itemView.findViewById(R.id.can_lay);
            id_lay = (LinearLayout)itemView.findViewById(R.id.id_lay);
        }
    }
}