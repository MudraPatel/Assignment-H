package com.assignment.heady.listItems;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assignment.heady.R;
import com.assignment.heady.db.VariantModel;
import com.assignment.heady.network.ProductInterface;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class ProductDisplayFragment extends Fragment  {


    RecyclerView recyclerView;
    LinearLayoutManager mLayoutManager;
    LinearLayout fragment_parent;
    List<VariantModel> arrayList;
    ProductDisplayAdapter mAdapter;
    private ProductInterface productInterface;

    public ProductDisplayFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public ProductDisplayFragment(LinearLayout fragment_parent, List<VariantModel> arrayList) {
        this.fragment_parent = fragment_parent;
        this.arrayList = arrayList;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_display, container, false);


        recyclerView = view.findViewById(R.id.recycler_view);
        mAdapter = new ProductDisplayAdapter(getContext(), arrayList, productInterface);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager mLayoutManagerHori = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mLayoutManagerHori.setReverseLayout(false);
        recyclerView.setLayoutManager(mLayoutManagerHori);
        recyclerView.setAdapter(mAdapter);

        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ProductInterface) {
            productInterface = (ProductInterface) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnGreenFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        productInterface = null;
    }

    class ProductDisplayAdapter extends RecyclerView.Adapter<ProductDisplayAdapter.MyViewHolder> {

        private Context context;
        private List<VariantModel> arrayList;
        private ProductInterface productInterface;

        ProductDisplayAdapter(Context context, List<VariantModel> arrayList,  ProductInterface productInterface) {
            this.context = context;
            this.arrayList = arrayList;
            this.productInterface = productInterface;
        }

        @Override
        public ProductDisplayAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_fragment, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            final VariantModel model = arrayList.get(position);
            holder.tvSize.setText(String.valueOf(model.getSize()));
            String[] colorsTxt = context.getApplicationContext().getResources().getStringArray(R.array.colors);
            String[] colorsName = context.getApplicationContext().getResources().getStringArray(R.array.colors_name);

            int pos = new ArrayList<String>(Arrays.asList(colorsName)).indexOf(model.getColor());
            Log.e("POSITION", pos + "POSITIO");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                if(pos == -1)
                    pos = 6;

                int newColor = Color.parseColor(colorsTxt[pos]);
                holder.imageview.setColorFilter(newColor);
            }

            holder.imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    productInterface.getVariantData(model);
                    fragment_parent.setVisibility(View.GONE);
                }
            });
        }


        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvSize;
            ImageView imageview;

            public MyViewHolder(View itemView) {
                super(itemView);

                tvSize = itemView.findViewById(R.id.nameTextView);
                imageview = itemView.findViewById(R.id.imageview);
            }
        }
    }


}


