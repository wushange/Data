package cn.datamining.dat.ui.login;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.datamining.dat.R;
import cn.datamining.dat.db.User;

public class UserListAdapter extends RecyclerArrayAdapter<User> {

    @Inject
    public UserListAdapter(Context context) {
        super(context);
    }
    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }
    class ViewHolder extends BaseViewHolder<User> {

        @BindView(R.id.tv_item_name)
        TextView tvText;

        @Override
        public void setData(User data) {
            super.setData(data);
            tvText.setText(""+ data.getNickName());
        }


        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.user_item);
            ButterKnife.bind(this, itemView);
        }

    }
}