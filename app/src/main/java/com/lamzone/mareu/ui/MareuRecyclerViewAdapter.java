package com.lamzone.mareu.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.mareu.DI.DependencyInjector;
import com.lamzone.mareu.R;
import com.lamzone.mareu.model.Meeting;
import com.lamzone.mareu.service.MareuApiService;
import com.lamzone.mareu.utils.MareuUtils;

import java.util.List;

public class MareuRecyclerViewAdapter extends RecyclerView.Adapter<MareuRecyclerViewAdapter.MareuViewHolder> {

    public class MareuViewHolder extends RecyclerView.ViewHolder{

        ImageView avatar;
        TextView title;
        TextView subtitle;
        ImageButton button;


        public MareuViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_meetingRecyclerViewItem_title);
            subtitle = itemView.findViewById(R.id.textView_meetingRecyclerViewItem_subTitle);
            avatar = itemView.findViewById(R.id.imageView_meetingRecyclerViewAvatar);
            button = itemView.findViewById(R.id.imageButton_meetingRecyclerViewItem_delete);
        }
    }

    private final List<Meeting> rvMeetingList; //List of meetings
    private MareuApiService apiService = DependencyInjector.getMareuApiService();

    /**
     * RecyclerView adapter constructor
     * @param meetings meetings that need to be displayed
     */
    public MareuRecyclerViewAdapter(List<Meeting> meetings){
        rvMeetingList = meetings;
    }

    @Override
    public MareuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.meeting_fragment,parent,false);

        return new MareuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MareuViewHolder holder, int position) {
        Meeting meeting = rvMeetingList.get(position);
        holder.title.setText(MareuUtils.getMeetingDisplayName(meeting));
        holder.subtitle.setText(MareuUtils.getParticipantsAddresses(meeting));
        holder.avatar.setColorFilter(meeting.getRoom().getColor());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService.removeMeeting(meeting);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return rvMeetingList.size();
    }
}
