package org.zzgsc.com.bmobdemo;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VH> {
    private Context ctx;
    private List<String> strs;

    public List<String> getStrs() {
        return strs;
    }

    public MyAdapter(Context ctx, ArrayList<String> str) {
        this.ctx = ctx;
        this.strs=str;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item,parent,false);
         //View view =View.inflate(parent.getContext(),R.layout.rv_item,parent);
         VH vh=new VH(view);
        return vh;
    }
    @Override
    public void onBindViewHolder(VH holder, int position) {
        Picasso.with(ctx).load(strs.get(position)).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return strs.size();
    }
   public void add(int pos,String str){
       getStrs().add(pos,str);
       notifyItemInserted(pos);

}
    public class VH extends RecyclerView.ViewHolder {
        public ImageView iv;

        public VH(View itemView) {
            super(itemView);
            iv= (ImageView) itemView.findViewById(R.id.iv);
        }
    }
}
