package com.haris.budget4u.presentationutil.ui.adapterutil;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haris.budget4u.presentationutil.ui.activityutil.Base;
import com.haris.budget4u.presentationutil.ui.customutil.GlideApp;
import com.haris.budget4u.presentationutil.ui.interfaceutil.SelectionInterface;
import com.haris.budget4u.presentationutil.ui.objectutil.EmptyObject;
import com.haris.budget4u.presentationutil.ui.objectutil.ProgressObject;
import com.haris.budget4u.presentationutil.ui.objectutil.SelectionObject;
import com.haris.budget4u.R;
import com.haris.budget4u.domainutil.modalutil.BankModal;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class SampleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int NO_DATA_VIEW = 1;
    private final int PROGRESS_VIEW = 2;
    private final int BANK_VIEW = 3;
    private Context context;
    private ArrayList<Object> dataArray = new ArrayList<>();
    private SelectionInterface selectionInterface;

    public SampleAdapter(Context context, ArrayList<Object> dataArray) {
        this.context = context;
        this.dataArray = dataArray;
    }

    public SampleAdapter(Context context, ArrayList<Object> dataArray, SelectionInterface selectionInterface) {
        this.context = context;
        this.dataArray = dataArray;
        this.selectionInterface = selectionInterface;
    }

    @Override
    public int getItemViewType(int position) {

        if (dataArray.get(position) instanceof EmptyObject) {
            return NO_DATA_VIEW;
        } else if (dataArray.get(position) instanceof ProgressObject) {
            return PROGRESS_VIEW;
        } else if (dataArray.get(position) instanceof BankModal) {
            return BANK_VIEW;
        }

        return NO_DATA_VIEW;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case PROGRESS_VIEW:
                return new ProgressHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.progress_item_layout, parent, false));
            case BANK_VIEW:
                return new BankHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.account_selector_item_layout, parent, false));
            default:
                return new EmptyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_item_layout, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((StrategyAdapter) holder).executeHolderFunctionality(holder, position);
    }

    @Override
    public int getItemCount() {
        return dataArray.size();
    }

    protected class EmptyHolder extends RecyclerView.ViewHolder implements StrategyAdapter {
        private ImageView imageIcon;
        private TextView txtTitle;
        private TextView txtDescription;

        public EmptyHolder(View view) {
            super(view);

            imageIcon = (ImageView) view.findViewById(R.id.image_icon);
            txtTitle = (TextView) view.findViewById(R.id.txt_title);
            txtDescription = (TextView) view.findViewById(R.id.txt_description);
        }

        @Override
        public void executeHolderFunctionality(RecyclerView.ViewHolder holder, int position) {


        }


        @Override
        public int getViewTypeIdentifier() {
            return NO_DATA_VIEW;
        }
    }

    protected class ProgressHolder extends RecyclerView.ViewHolder implements StrategyAdapter {

        public ProgressHolder(View view) {
            super(view);
        }

        @Override
        public void executeHolderFunctionality(RecyclerView.ViewHolder holder, int position) {
            //do nothing
        }


        @Override
        public int getViewTypeIdentifier() {
            return PROGRESS_VIEW;
        }
    }
    protected class BankHolder extends RecyclerView.ViewHolder implements StrategyAdapter {
        private RelativeLayout layoutCategory;
        private LinearLayout layoutSelection;
        private LinearLayout layoutType;
        private ImageView imageType;
        private TextView txtName;

        public BankHolder(View view) {
            super(view);

            layoutCategory = (RelativeLayout) view.findViewById(R.id.layout_category);
            ///layoutType = (LinearLayout) view.findViewById(R.id.layout_type);
            imageType = (ImageView) view.findViewById(R.id.image_type);
            layoutSelection = view.findViewById(R.id.layout_selection);
            txtName = view.findViewById(R.id.txt_name);

        }

        @Override
        public void executeHolderFunctionality(RecyclerView.ViewHolder holder, int position) {

            final BankHolder holdr = (BankHolder) holder;
            final BankModal modal = (BankModal) dataArray.get(position);

            holdr.txtName.setText(modal.getName());
            GlideApp.with(context).load(Base.getImage(modal.getImage())).into(holdr.imageType);
            ///holdr.layoutType.setBackgroundColor(Color.parseColor(modal.getColorCode()));

            if (modal.isSelection()) {
                holdr.layoutSelection.setVisibility(View.VISIBLE);
            }
            else {
                holdr.layoutSelection.setVisibility(View.GONE);
            }

            holdr.layoutCategory.setTag(position);
            holdr.layoutCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) holdr.layoutCategory.getTag();
                    if (selectionInterface != null) {
                        selectionInterface.onSelection(new SelectionObject()
                                .setPosition(pos));
                    }
                }
            });


        }

        @Override
        public int getViewTypeIdentifier() {
            return BANK_VIEW;
        }

    }

}
