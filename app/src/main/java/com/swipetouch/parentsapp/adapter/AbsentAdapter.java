package com.swipetouch.parentsapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swipetouch.parentsapp.R;
import com.swipetouch.parentsapp.response.Student;
import com.swipetouch.parentsapp.ui.AttdendanceACtivity;

import java.util.List;

public class AbsentAdapter extends RecyclerView.Adapter {

    Context context;
    List<Student>studentList;
    private RadioGroup lastCheckedRadioGroup = null;
    public AbsentAdapter(Context context, List<Student>studentList){

        this.context = context;
        this.studentList = studentList;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_absent, parent, false);
        AttendanceHolder vh = new AttendanceHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if  (holder instanceof AttendanceHolder) {

            final AttendanceHolder itemHolder = (AttendanceHolder) holder;



            itemHolder.studentName.setText(studentList.get(position).getStudent_name());
            itemHolder.rollNO.setText(studentList.get(position).getRoll_no());

        }

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void notifyStudent(List<Student> attendance) {
        this.studentList = attendance;
        notifyDataSetChanged();
    }



    public class AttendanceHolder extends RecyclerView.ViewHolder {
        TextView studentName, rollNO;
        RelativeLayout parentRL;
        RadioGroup attendRadio;
        public AttendanceHolder(View itemView) {
            super(itemView);
            studentName = (TextView)itemView.findViewById(R.id.studentName);
            rollNO = (TextView)itemView.findViewById(R.id.rollNO);



        }
    }
}
