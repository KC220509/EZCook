package com.example.ezcook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ezcook.R;
import com.example.ezcook.model.h_Notification_Model;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class h_NotificationAdapter extends RecyclerView.Adapter<h_NotificationAdapter.NotificationViewHolder> {
    private List<h_Notification_Model> notificationList;

    public void setData(List<h_Notification_Model> notificationList) {
        this.notificationList = notificationList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.h_item_notification, parent, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        h_Notification_Model notification = notificationList.get(position);
        // Hiển thị thông tin của thông báo trong ViewHolder

        Picasso.get().load(notification.getImage()).into(holder.image);

        holder.title.setText(notification.getTitle());
        holder.content.setText(notification.getContent());
        long time = notification.getTime();
        String timestamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).format(new Date(time));
        holder.tvtime.setText(timestamp);

    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        TextView title, content, tvtime;
        ImageView image;

        // Khai báo các view trong mỗi mục của danh sách thông báo
        public NotificationViewHolder(View itemView) {
            super(itemView);

            // Ánh xạ và gán giá trị cho các view ở đây
            image = itemView.findViewById(R.id.ImageNotification);
            title = itemView.findViewById(R.id.TitleNotification);
            content = itemView.findViewById(R.id.ContentNotification);
            tvtime = itemView.findViewById(R.id.TimeNotification);
        }
    }
}
