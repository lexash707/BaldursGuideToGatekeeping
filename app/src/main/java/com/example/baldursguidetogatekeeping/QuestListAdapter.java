package com.example.baldursguidetogatekeeping;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestListAdapter extends RecyclerView.Adapter<QuestListAdapter.QuestListHolder> {

    private List<Quest> quests;

    public QuestListAdapter(List<Quest> quests) {
        this.quests = quests;
    }

    @NonNull
    @Override
    public QuestListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.quest, parent, false);

        return new QuestListHolder(view, quests);
    }


    @Override
    public void onBindViewHolder(@NonNull QuestListHolder holder, int position) {
        holder.textView.setText(quests.get(position).getName());
        holder.isPrimary.setTag(position);
        holder.isPrimary.setChecked(quests.get(position).isPrimary());
        holder.dateFound.setText(quests.get(position).getDateFound().toString());
        holder.description.setText(quests.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return quests.size();
    }

    public class QuestListHolder extends RecyclerView.ViewHolder {

        public TextView textView;
        public CheckBox isPrimary;
        public TextView dateFound;
        public TextView description;

        public QuestListHolder(View view, List<Quest> quests) {
            super(view);

            textView = view.findViewById(R.id.textView);
            isPrimary = view.findViewById(R.id.isPrimary);
            dateFound = view.findViewById(R.id.dateFound);
            description = view.findViewById(R.id.questDescription);

            isPrimary.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    quests.get((Integer) buttonView.getTag()).setPrimary(isChecked);
                }
            });
        }
    }
}
