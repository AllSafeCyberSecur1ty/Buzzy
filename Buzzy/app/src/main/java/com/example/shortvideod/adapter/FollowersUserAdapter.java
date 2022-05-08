package com.example.shortvideod.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shortvideod.R;
import com.example.shortvideod.databinding.ItemFollowingListBinding;
import com.example.shortvideod.design.SuggestedUser;

import java.util.ArrayList;
import java.util.List;

public class FollowersUserAdapter extends RecyclerView.Adapter<FollowersUserAdapter.FollowersHolder> {
    List<SuggestedUser> userlist = new ArrayList<>();
    int pagepostion;
    Context context;

    public FollowersUserAdapter() {
    }

    public FollowersUserAdapter(int pos) {
        this.pagepostion = pos;
    }

    @NonNull
    @Override
    public FollowersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new FollowersHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_following_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FollowersHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public List<SuggestedUser> getList() {
        return userlist;
    }


    public void addData(List<SuggestedUser> user) {
        this.userlist.addAll(user);
        notifyItemRangeInserted(this.userlist.size(), user.size());
    }

    public void clear() {
        userlist.clear();
        notifyDataSetChanged();
    }


    public class FollowersHolder extends RecyclerView.ViewHolder {
        ItemFollowingListBinding binding;

        public FollowersHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemFollowingListBinding.bind(itemView);
        }

        public void setData(int position) {
            SuggestedUser user = userlist.get(position);
            Glide.with(binding.getRoot()).load(user.getImage()).into(binding.thumbnail);
            binding.username.setText(user.getName());
            binding.email.setText(user.getEmail());

            if (pagepostion == 0) {
                binding.btnFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_lightblue));
                binding.btnFollow.setText(R.string.follow);

            } else if (pagepostion == 1) {
                binding.btnFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_following));
                binding.btnFollow.setText(R.string.following);
            }

            binding.btnFollow.setOnClickListener(v -> {
                binding.btnFollow.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_following));
                binding.btnFollow.setText(R.string.following);
            });


        }
    }
}
