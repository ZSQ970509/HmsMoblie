package com.hc.hmsmoblie.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

public class SelectProjectAdapter extends RecyclerView.Adapter<YcViewHolder> {
    private List<GetProListByArea.FolderListBean> mFolder = new ArrayList<>();//文件夹类型
    private List<GetProListByArea.FileListBean> mFile = new ArrayList<>();//文件类型
    private FolderCall mFolderCall;
    private FileCall mFileCall;
    private Context mContext;

    public SelectProjectAdapter(Context context) {
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


    /**
     * 根据position获取item的类型
     */
    @SelectProjectTypeEnum
    private int getType(int position) {
        if (position < mFolder.size()) {
            return SelectProjectTypeEnum.FOLDER;
        } else {
            return SelectProjectTypeEnum.FILE;
        }
    }

    /**
     * 设置布局
     */
    @Override
    public int getItemViewType(int position) {
        int layoutId = -1;
        switch (getType(position)) {
            case SelectProjectTypeEnum.FILE:
                layoutId = R.layout.item_select_project_fragment_file;
                break;
            case SelectProjectTypeEnum.FOLDER:
                layoutId = R.layout.item_select_project_fragment_folder;
                break;
        }
        return layoutId;
    }

    public void onUpdate(BaseAdapterHelper helper, int position) {
        switch (getType(position)) {
            case SelectProjectTypeEnum.FILE:
                final GetProListByArea.FileListBean fileListBean = mFile.get(getItemIndex(position));
                helper.setText(R.id.itemSelectProFileName, fileListBean.getProName());
                helper.setText(R.id.itemSelectProFileAddress, fileListBean.getProAddress());
                helper.setText(R.id.itemSelectProFileStatus, fileListBean.getProStatusCurrent());
                helper.getView().setOnClickListener(v -> {
                    if (mFileCall != null)
                        mFileCall.call(fileListBean);
                });
                break;
            case SelectProjectTypeEnum.FOLDER:
                final GetProListByArea.FolderListBean folderListBean = mFolder.get(getItemIndex(position));
                helper.setText(R.id.itemSelectProFolderName, folderListBean.getFolderName());
                helper.getView().setOnClickListener(v -> {
                    if (mFolderCall != null)
                        mFolderCall.call(folderListBean);
                });
                break;
        }
    }

    /**
     * 获取position在各个数据对应的index
     */
    public int getItemIndex(int position) {
        switch (getType(position)) {
            case SelectProjectTypeEnum.FILE:
                return position - mFolder.size();
            case SelectProjectTypeEnum.FOLDER:
                return position;
        }
        return position;
    }

    @Override
    public int getItemCount() {
        return mFolder.size() + mFile.size();
    }


    public void setFolderCall(FolderCall folderCall) {
        this.mFolderCall = folderCall;
    }

    public void setFileCall(FileCall fileCall) {
        this.mFileCall = fileCall;
    }

    public void addAllDataFolder(List<GetProListByArea.FolderListBean> folder) {
        addAllDataFolder(folder, false);
    }

    public void addAllDataFolder(List<GetProListByArea.FolderListBean> folder, boolean isClear) {
        if (isClear)
            mFolder.clear();
        if (null != folder && folder.size() > 0)
            mFolder.addAll(folder);
        notifyDataSetChanged();
//        notifyItemRangeInserted(mFolder.size() - folder.size(), folder.size());
    }

    public void addAllDataFile(List<GetProListByArea.FileListBean> file) {
        addAllDataFile(file, false);
    }

    public void addAllDataFile(List<GetProListByArea.FileListBean> file, boolean isClear) {
        if (isClear)
            mFile.clear();
        if (null != file && file.size() > 0)
            mFile.addAll(file);
        notifyDataSetChanged();
    }

    public void clearFile() {
        final int size = mFile.size();
        mFile.clear();
        notifyItemRangeRemoved(0, size);
    }

    public void clearFolder() {
        final int size = mFolder.size();
        mFolder.clear();
        notifyItemRangeRemoved(0, size);
    }

    public interface FileCall {
        void call(GetProListByArea.FileListBean file);
    }

    public interface FolderCall {
        void call(GetProListByArea.FolderListBean folder);
    }
}
