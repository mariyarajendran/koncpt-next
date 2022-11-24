package app.technotech.koncpt.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

import app.technotech.koncpt.R;
import app.technotech.koncpt.data.network.model.FacultyModel;

public class FragmentAdapter extends ArrayAdapter<FacultyModel.FacultyDatum> {

    Context context;
    List<FacultyModel.FacultyDatum> moduleDatumList;
    int resource;

    public FragmentAdapter(Context context, int resource, List<FacultyModel.FacultyDatum> moduleDatumList) {
        super(context,resource,moduleDatumList);
        this.context = context;
        this.moduleDatumList = moduleDatumList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.faculty_item,null,true);
        }
        FacultyModel.FacultyDatum listdata=getItem(position);
        ImageView img=(ImageView)convertView.findViewById(R.id.img_faculty);
        TextView user_name=(TextView)convertView.findViewById(R.id.user_name);
        user_name.setText(listdata.getFacultyName());
        TextView user_description=(TextView)convertView.findViewById(R.id.user_description);
        user_description.setText(listdata.getEditor());
        TextView user_title=(TextView)convertView.findViewById(R.id.user_title);
        user_title.setText(listdata.getSlug());
        TextView user_content=(TextView)convertView.findViewById(R.id.user_content);
        user_content.setText(listdata.getContent());

        Glide.with(context)
                .load(listdata.getFacultyImage())
                .error(R.drawable.app_logo)
                .into(img);


        return convertView;
    }

}
