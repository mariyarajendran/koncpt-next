package app.technotech.koncpt.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.DailyHuntModel;
import app.technotech.koncpt.databinding.ActivityDailyHuntDetailViewBinding;

public class DailyHuntDetailView extends AppCompatActivity {


    private ActivityDailyHuntDetailViewBinding binding;
    private DailyHuntModel.Datum datum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = DataBindingUtil.setContentView(DailyHuntDetailView.this, R.layout.activity_daily_hunt_detail_view);
        binding.setLifecycleOwner(this);

        Intent intent = getIntent();




        binding.toolbar.setTitle("Detail View");
        binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        if(intent != null){
            datum = new Gson().fromJson(intent.getStringExtra("data"), new TypeToken<DailyHuntModel.Datum>(){}.getType());
        }
        int radius =  getResources().getDimensionPixelOffset(R.dimen._4sdp);


        Glide.with(DailyHuntDetailView.this)
                .load(datum.getBlogImage())
                .transform(new CenterCrop(), new RoundedCorners(radius))
                .into(binding.imageHuntProfile);


        binding.textHuntTitle.setText(datum.getTitle());
        binding.textHuntDescription.setText(datum.getContent());
        binding.textHuntCreated.setText(datum.getCreatedAt());


    }
}