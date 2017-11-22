package com.example.view;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ding Ding on 2017/11/8.
 */
public class AdapterCommonCleanRv extends RecyclerView.Adapter<AdapterCommonCleanRv.MyViewHolder> {

    private final Context context;
    private final List<WashCarShopBean> commonCleanList;


    public AdapterCommonCleanRv(Context context, List<WashCarShopBean> commonCleanList) {
        this.context = context;
        this.commonCleanList = commonCleanList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item_all_clean_car, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final int adapterPosition = holder.getAdapterPosition();
        final WashCarShopBean washCarShop = commonCleanList.get(adapterPosition);
        holder.mTvShopName.setText(washCarShop.getShopname());
        holder.mTvShopProject.setText(washCarShop.getName());
        holder.mTvShopPrice.setText("￥"+washCarShop.getShopprice());
        holder.mTvShopPrice2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.mTvShopPrice2.setText(washCarShop.getMarketprice());
        holder.mDistance.setText(washCarShop.getDistance()+"KM");
        holder.mAddress.setText(washCarShop.getAddress());
        if (washCarShop.getIscoop().equals("1")){
            holder.mTvJoin.setVisibility(View.VISIBLE);
        }else {
            holder.mTvJoin.setVisibility(View.INVISIBLE);
        }
        if (washCarShop.getIscoupon().equals("1")){
            holder.mCoupon.setVisibility(View.VISIBLE);
        }else {
            holder.mCoupon.setVisibility(View.INVISIBLE);
        }
        Glide.with(context)
                .load("http://www.xinhangxian.cn"+washCarShop.getSmallimage())
                .placeholder(R.mipmap.placeholder)
                .into(holder.mIv);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(context, NewGroupBuyDetailActivity.class);
                intent.putExtra("type","cleanCar");
                intent.putExtra("id",washCarShop.getGuid());
                intent.putExtra("distance",washCarShop.getDistance());
                context.startActivity(intent);*/
            }
        });
    }

    @Override
    public int getItemCount() {

           return commonCleanList.size();

    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView mIv;
        @BindView(R.id.tv_shop_name)
        TextView mTvShopName;
        @BindView(R.id.tv_join)
        TextView mTvJoin;
        @BindView(R.id.tv_shop_project)
        TextView mTvShopProject;
        @BindView(R.id.tv_shop_price)
        TextView mTvShopPrice;
        @BindView(R.id.tv_shop_price2)
        TextView mTvShopPrice2;
        @BindView(R.id.member)
        TextView mMember;
        @BindView(R.id.distance)
        TextView mDistance;
        @BindView(R.id.view)
        View mView;
        @BindView(R.id.address)
        TextView mAddress;
        @BindView(R.id.ll)
        LinearLayout mLl;
        @BindView(R.id.coupon)
        TextView mCoupon;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
