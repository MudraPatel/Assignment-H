package com.assignment.heady.listItems;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.assignment.heady.R;
import com.assignment.heady.db.ProductModel;
import com.assignment.heady.db.VariantModel;
import com.assignment.heady.network.ProductInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class DetailsActivity extends AppCompatActivity implements ProductInterface {
//    private DataDetailsViewModel viewModel;
    int id;

    TextView headerText;
    Toolbar toolbar;
    ImageView back;
    ProductListViewModel viewModel;

    ImageView ivWishList, productImage, shareImage;
    TextView tvPrice, tvSize, tvProductName;

    LinearLayout linearLayout;
    LinearLayout.LayoutParams layoutParamsAdd;
    LinearLayout fragment_parent;
    ProductModel dataModel;
    boolean wishListStatus = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        id = getIntent().getExtras().getInt("id");
        viewModel = ViewModelProviders.of(this).get(ProductListViewModel.class);


        findViewId();
        layoutParamsAdd = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);


        viewModel.getItemAndProductId(id).observe(this, new Observer<ProductModel>() {
            @Override
            public void onChanged(ProductModel productModel) {
                dataModel = productModel;
                Log.e("PRODUCT MODEL", productModel.getName());
                colorImage(productImage, productModel.getVariantModel().get(0).getColor());
                tvProductName.setText(productModel.getName());
                tvPrice.setText(String.format("Price : RS %s", productModel.getVariantModel().get(0).getPrice()));
                tvSize.setText(String.format("Size :  %s", productModel.getVariantModel().get(0).getSize()));
                for(VariantModel variantModel : productModel.getVariantModel()){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        addViewChild(variantModel);
                    }
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void addViewChild(VariantModel variantModel){
        View viewAdd = LayoutInflater.from(this).inflate(R.layout.add_layout_view, null);
        LinearLayout layoutViewAdd =   viewAdd.findViewById(R.id.layout_add_view);
        linearLayout.addView(layoutViewAdd, layoutParamsAdd);
        ImageView imageView = viewAdd.findViewById(R.id.add_image);
        colorImage(imageView, variantModel.getColor());
        /*String[] colorsTxt = getApplicationContext().getResources().getStringArray(R.array.colors);
        String[] colorsName = getApplicationContext().getResources().getStringArray(R.array.colors_name);

        int pos = new ArrayList<String>(Arrays.asList(colorsName)).indexOf(variantModel.getColor());
        Log.e("POSITION", pos + "POSITIO");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int newColor = Color.parseColor(colorsTxt[pos]);
            imageView.setColorFilter(newColor);
        }*/

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment_parent.setVisibility(View.VISIBLE);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                ProductDisplayFragment fragment = new ProductDisplayFragment(fragment_parent, dataModel.getVariantModel()) ;
                fragmentTransaction.replace(R.id.fragment_container_parent,fragment);
                fragmentTransaction.commit();

                //to hide - soft keyboard
                InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                keyboard.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });


    }

    public void findViewId(){
        toolbar = findViewById(R.id.toolbar_header);
        headerText = findViewById(R.id.header_text);
        back = toolbar.findViewById(R.id.iv_back);
        headerText.setText(getString(R.string.product_details));

        productImage = findViewById(R.id.image_icon);
        tvPrice = findViewById(R.id.price);
        tvSize = findViewById(R.id.size);
        tvProductName = findViewById(R.id.product_name);
        linearLayout = findViewById(R.id.linear_layout_view);
        fragment_parent = findViewById(R.id.fragment_container_parent);
        ivWishList  = findViewById(R.id.image_wishlist);
        fragment_parent.setVisibility(View.GONE);
        shareImage = findViewById(R.id.share);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ivWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!wishListStatus){
                    ivWishList.setColorFilter(getResources().getColor(R.color.colorAccent));
                    wishListStatus = true;
                }else{
                    ivWishList.setColorFilter(getResources().getColor(R.color.gray));
                    wishListStatus = false;
                }
            }
        });

        shareImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String shareBody = "Here is the share content body";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent,"Share using"));
            }
        });

    }

    public void onClickLayout(View view) {
        fragment_parent.setVisibility(View.GONE);
    }


    @Override
    public void getVariantData(VariantModel variantModel) {
        Log.e("CHECK INTERFACE", variantModel.getColor() + "***");
        tvPrice.setText(String.format("Price : RS %s", variantModel.getPrice()));
        tvSize.setText(String.format("Size :  %s", variantModel.getSize()));
        colorImage(productImage, variantModel.getColor());
    }

    public void colorImage(ImageView imageView, String stringColor){
        String[] colorsTxt = getApplicationContext().getResources().getStringArray(R.array.colors);
        String[] colorsName = getApplicationContext().getResources().getStringArray(R.array.colors_name);
        int pos = new ArrayList<String>(Arrays.asList(colorsName)).indexOf(stringColor);
        Log.e("POSITION", pos + "POSITIO");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if(pos == -1)
                pos = 6;

            int newColor = Color.parseColor(colorsTxt[pos]);
            imageView.setColorFilter(newColor);
        }
    }
}
