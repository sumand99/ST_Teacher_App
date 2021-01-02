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

public class AttendanceAdapter extends RecyclerView.Adapter {

    Context context;
    List<Student>studentList;
    private RadioGroup lastCheckedRadioGroup = null;
    public AttendanceAdapter(Context context, List<Student>studentList){

        this.context = context;
        this.studentList = studentList;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_attendance, parent, false);
        AttendanceHolder vh = new AttendanceHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if  (holder instanceof AttendanceHolder) {

            final AttendanceHolder itemHolder = (AttendanceHolder) holder;



            itemHolder.studentName.setText(studentList.get(position).getStudent_name());
            itemHolder.rollNO.setText(studentList.get(position).getRoll_no());
            itemHolder.attendRadio.setOnCheckedChangeListener(

                    new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup group, int checkedId) {

                            if (checkedId == R.id.present) {
                                ((AttdendanceACtivity)context).clickedItem(position, "present");
                            } else if (checkedId == R.id.late) {

                                ((AttdendanceACtivity)context).clickedItem(position, "late");
                            }else if (checkedId == R.id.halfday) {
                                ((AttdendanceACtivity)context).clickedItem(position, "halfday");

                            }else if (checkedId == R.id.absent) {

                                ((AttdendanceACtivity)context).clickedItem(position, "absent");
                            }

                        }
                    });
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

            attendRadio = (RadioGroup) itemView.findViewById(R.id.attendRadio);

            attendRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    //since only one package is allowed to be selected
                    //this logic clears previous selection
                    //it checks state of last radiogroup and
                    // clears it if it meets conditions
                    if (lastCheckedRadioGroup != null
                            && lastCheckedRadioGroup.getCheckedRadioButtonId()
                            != radioGroup.getCheckedRadioButtonId()
                            && lastCheckedRadioGroup.getCheckedRadioButtonId() != -1) {
                        lastCheckedRadioGroup.clearCheck();



                    }
                    lastCheckedRadioGroup = radioGroup;

                }
            });

        }
    }
}
