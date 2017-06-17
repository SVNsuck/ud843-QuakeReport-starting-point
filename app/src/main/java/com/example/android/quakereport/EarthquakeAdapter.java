package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.R.attr.resource;

/**
 * Created by PWT on 2017/6/6.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        View listItemView = convertView;
        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitudeView = (TextView)listItemView.findViewById(R.id.magnitude);
        // 为震级圆圈设置正确的背景颜色。
        // 从 TextView 获取背景，该背景是一个 GradientDrawable。
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeView.getBackground();

        // 根据当前的地震震级获取相应的背景颜色
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMagnitude());

        // 设置震级圆圈的颜色
        magnitudeCircle.setColor(magnitudeColor);
        magnitudeView.setText(decimalFormat.format(currentEarthquake.getmMagnitude()));


        //处理地址,将地址显示成两个部分:偏移量+详细地址
        TextView locationMainView = (TextView)listItemView.findViewById(R.id.primary_location);
        TextView locationOffsetView = (TextView)listItemView.findViewById(R.id.location_offset);
        String locationStr = currentEarthquake.getmLocation();
        String offsetStr = locationStr.contains(" of ")?locationStr.substring(0,locationStr.indexOf(" of ")+4):"Near the";
        String mainLocStr = locationStr.substring(locationStr.indexOf(" of ")+4);
        locationMainView.setText(mainLocStr);
        locationOffsetView.setText(offsetStr);

        TextView dateView = (TextView)listItemView.findViewById(R.id.date);
        // 根据地震时间（以毫秒为单位）创建一个新的 Date 对象
        Date dateObject = new Date(currentEarthquake.getmTimeInMilliseconds());
        // 设置日期字符串的格式（即 "Mar 3, 1984"）
        String formattedDate = formatDate(dateObject);
        dateView.setText(formattedDate);

        // 找到视图 ID 为 time 的 TextView
        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        // 设置时间字符串的格式（即 "4:30PM"）
        String formattedTime = formatTime(dateObject);
        // 在该 TextView 中显示目前地震的时间
        timeView.setText(formattedTime);
        return listItemView;
    }

    /**
     * 从 Date 对象返回格式化的日期字符串（即 "Mar 3, 1984"）。
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * 从 Date 对象返回格式化的时间字符串（即 "4:30 PM"）。
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
}
