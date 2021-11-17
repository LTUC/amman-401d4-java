package com.d4coders.goodcitizen.ui.dashboard.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amplifyframework.datastore.generated.model.Problem;
import com.d4coders.goodcitizen.R;

import java.util.List;

public class RecentObservationsAdapter extends RecyclerView.Adapter<RecentObservationsAdapter.ViewHolder> {

    private final List<Problem> recentObservations;
    private final RecentObservationClickListener listener;

    public interface RecentObservationClickListener {
        void onObservationClicked(View view);
    }

    public RecentObservationsAdapter(List<Problem> recentObservations, RecentObservationClickListener listener) {
        this.listener = listener;
        this.recentObservations = recentObservations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_observation, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(recentObservations.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return recentObservations.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;

        public ViewHolder(@NonNull View itemView, RecentObservationClickListener listener) {
            super(itemView);

            title = itemView.findViewById(R.id.observation_title);

            itemView.setOnClickListener(view -> {
                listener.onObservationClicked(view);
            });
        }
    }
}
