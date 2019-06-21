package jp.co.calace.lonor;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import jp.co.calace.lonor.models.Message;


public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {

    private List<Message> msgList;
    final private String datePattern = "HH:mm";
    private DateFormat dateFormat;

    public MsgAdapter(List<Message> msgList) {
        this.msgList = msgList;
        dateFormat = new SimpleDateFormat(datePattern);
    }

    @Override
    public int getItemViewType(int i) {
        return msgList.get(i).getType();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int type) {
        View view;

        if (type == Message.TYPE_SEND) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.self_msg, viewGroup, false);
        } else {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recv_msg, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int i) {
        Message message = msgList.get(i);
        viewholder.senderName.setText(message.getSender().getName());
        viewholder.senderAvatar.setImageResource(message.getSender().getAvatar());
        viewholder.msgText.setText(message.getText());
        viewholder.msgTime.setText(dateFormat.format(message.getTime()));
    }


    @Override
    public int getItemCount() {
        return msgList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView msgText;
        TextView msgTime;
        TextView senderName;
        ImageView senderAvatar;

        public ViewHolder(@NonNull View view) {
            super(view);

            msgText = view.findViewById(R.id.msgText);
            msgTime = view.findViewById(R.id.msgTime);
            senderName = view.findViewById(R.id.senderName);
            senderAvatar = view.findViewById(R.id.senderAvatar);
        }
    }
}
