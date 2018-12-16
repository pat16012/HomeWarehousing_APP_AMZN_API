package homewarehouse.project.homewarehouse_amzn_api;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

// Extends Adapter and specify the generic type
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewholder> {

    // Declair list of Product_info Class to hold API Json return.
    private List<Product_Info> list = new ArrayList<>();

    RecyclerAdapter(List<Product_Info> list){
        this.list = list;
    }

    //Changed method to MyViewholder
    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new MyViewholder(view);
    }

    //Changed first perameter to MyViewHolder
    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {

        //Setting text for the activity view, from the List.
        holder.UPSC.setText(Integer.toString(list.get(position).getUpsc()));
        holder.Title.setText(list.get(position).getTitle());
        holder.BrandName.setText(list.get(position).getBrandName());
    }

    // Return size of the List
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewholder extends RecyclerView.ViewHolder{


        // Define Multiple TextView variables. One for each Json return you
        // want to display.
        TextView UPSC,BrandName,Title;

        public MyViewholder(View itemView) {
            super(itemView);
            // Attached textView variables with the activity display variables.
            UPSC = (TextView) itemView.findViewById(R.id.upsc);
            BrandName = (TextView) itemView.findViewById(R.id.brandname);
            Title = (TextView) itemView.findViewById(R.id.title);
        }
    }
}
