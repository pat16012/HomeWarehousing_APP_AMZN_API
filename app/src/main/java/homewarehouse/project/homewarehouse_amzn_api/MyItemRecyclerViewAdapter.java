package homewarehouse.project.homewarehouse_amzn_api;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import homewarehouse.project.homewarehouse_amzn_api.ItemFragment.OnListFragmentInteractionListener;
import homewarehouse.project.homewarehouse_amzn_api.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Product_Info} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private final List<Product_Info> products;
    //private final OnListFragmentInteractionListener mListener;

    public MyItemRecyclerViewAdapter(List<Product_Info> items) {
        products = items;
        //mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //holder.mItem = products.get(position);
        //holder.mIdView.setText(products.get(position).brandName);
        //holder.mContentView.setText(products.get(position).);

        holder.UPSC.setText(Integer.toString(products.get(position).getUpsc()));
        holder.Title.setText(products.get(position).getTitle());
        holder.BrandName.setText(products.get(position).getBrandName());
/*
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.Title);
                }
            }
        });
        */
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView UPSC;
        public final TextView BrandName;
        public final TextView Title;
        public DummyItem dummyItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            UPSC = (TextView) itemView.findViewById(R.id.upsc);
            BrandName = (TextView) itemView.findViewById(R.id.brandname);
            Title = (TextView) itemView.findViewById(R.id.title);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + BrandName.getText() + "'";
        }
    }
}
