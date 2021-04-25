package com.lamzone.mareu.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lamzone.mareu.R;
import com.lamzone.mareu.model.Meeting;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MareuRecyclerViewAdapter extends RecyclerView.Adapter<MareuRecyclerViewAdapter.mareuViewHolder> {

    public class mareuViewHolder extends RecyclerView.ViewHolder{

        ImageView avatar;
        TextView title;
        TextView subtitle;


        public mareuViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_meetingRecyclerViewItem_title);
            subtitle = itemView.findViewById(R.id.textView_meetingRecyclerViewItem_subTitle);
            avatar = itemView.findViewById(R.id.imageView_meetingRecyclerViewAvatar);
        }
    }

    private final List<Meeting> rvMeetingList; //List of meetings

    /**
     * RecyclerView adapter constructor
     * @param meetings meetings that need to be displayed
     */
    public MareuRecyclerViewAdapter(List<Meeting> meetings){
        rvMeetingList = meetings;
    }

    /**
     * inflate recyclerview fragments
     */
    @Override
    public mareuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.meeting_fragment,parent,false);

        return new mareuViewHolder(view);
    }

    
    @Override
    public void onBindViewHolder(@NonNull mareuViewHolder holder, int position) {
        Meeting meeting = rvMeetingList.get(position);
        holder.title.setText(meeting.getMeetingDisplayName());
        holder.subtitle.setText(meeting.getParticipantsAddresses());
        holder.avatar.setColorFilter(meeting.getRoom().getColor());

        //TODO clic bouton supprimer

    }

    @Override
    public int getItemCount() {
        return rvMeetingList.size();

    }


}
