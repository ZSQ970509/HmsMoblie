package com.hc.hmsmoblie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.classic.adapter.BaseAdapterHelper;
import com.hc.hmsmoblie.R;
import com.hc.hmsmoblie.base.YcViewHolder;
import com.hc.hmsmoblie.bean.json.GetProListByArea;
import com.hc.hmsmoblie.bean.type.SelectProjectTypeEnum;

import java.util.ArrayList;
import java.util.List;

import static com.classic.adapter.BaseAdapterHelper.get;

/**
 * Created by Administrator on 2018/6/11.
 */

public class SelectProjectRouteAdapter extends RecyclerView.Adapter<YcViewHolder> {
    private List<GetProListByArea.FolderListBean> mFolder = new ArrayList<>();//文件夹类型
    private FolderCall mFolderCall;
    private Context mContext;

    public SelectProjectRouteAdapter(Context context) {
        mContext = context;
    }

    @Override
    public void onBindViewHolder(YcViewHolder holder, int position) {
        onUpdate(holder.getAdapterHelper(), position);
    }

    @Override
    public YcViewHolder onCreateViewHolder(ViewGroup parent, @SelectProjectTypeEnum int viewType) {
        final BaseAdapterHelper helper = get(mContext, null, parent, viewType);
        return new YcViewHolder(helper);
    }

    /*
     * 设置布局
     */
    @Override
    public int getItemViewType(int position) {
        return R.layout.item_select_project_fragment_route;
    }

    public void onUpdate(BaseAdapterHelper helper, final int position) {
        final GetProListByArea.FolderListBean item = mFolder.get(position);
        helper.setText(R.id.itemSelectRouteTv, item.getFolderName());
        helper.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFolderCall != null)
                    mFolderCall.call(item);
                removeData(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFolder.size();
    }

    public void setFolderCall(FolderCall folderCall) {
        this.mFolderCall = folderCall;
    }

    public void addDataFolder(GetProListByArea.FolderListBean folder) {
        mFolder.add(folder);
        notifyDataSetChanged();
    }

    public void removeData(int index) {
        for (int i = mFolder.size() - 1; i >= 0; i--) {
            if (i > index) {
                mFolder.remove(i);
            } else {
                break;
            }
        }
        notifyDataSetChanged();
    }

    public interface FolderCall {
        void call(GetProListByArea.FolderListBean folder);
    }
}
