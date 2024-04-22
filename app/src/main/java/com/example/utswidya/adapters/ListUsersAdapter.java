package com.example.utswidya.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.utswidya.DetailActivity;
import com.example.utswidya.databinding.ListItemBinding;
import com.example.utswidya.models.UsersItem;

public class ListUsersAdapter extends ListAdapter<UsersItem, ListUsersAdapter.ViewHolder> {

    public ListUsersAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ListUsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = ListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListUsersAdapter.ViewHolder holder, int position) {
        UsersItem item = getItem(position);
        holder.bind(item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;

        public ViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(UsersItem usersItem) {
            binding.namaProfile.setText(usersItem.getLogin());
            Glide.with(itemView.getContext())
                    .load(usersItem.getAvatarUrl())
                    .into(binding.fotoProfile);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra("nama", usersItem.getLogin());
                view.getContext().startActivity(intent);
            });
        }
    }

    private static final DiffUtil.ItemCallback<UsersItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<UsersItem>() {
        @Override
        public boolean areItemsTheSame(UsersItem oldItem, UsersItem newItem) {
            return oldItem.getLogin().equals(newItem.getLogin());
        }

        @Override
        public boolean areContentsTheSame(UsersItem oldItem, UsersItem newItem) {
            return oldItem.getLogin().equals(newItem.getLogin()) &&
                    oldItem.getAvatarUrl().equals(newItem.getAvatarUrl()) &&
                    (oldItem.getBio() != null ? oldItem.getBio().equals(newItem.getBio()) : newItem.getBio() == null);
        }
    };
}
