package com.uniguides.userscampusapply.StatsData;

import android.content.Context;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class StatisticsGraph {

    public static void drawBarChart(int[] data, String title, String xLabel, String yLabel, Context context, FrameLayout layout) {

        BarChart chart = new BarChart(context);
        chart.setDrawGridBackground(false);
        chart.getDescription().setEnabled(false);

        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            entries.add(new BarEntry(i, data[i]));
            labels.add(StatisticsHelper.getCountries()[i]);
        }

        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        BarData barData = new BarData(dataSets);
        chart.setData(barData);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
        xAxis.setLabelRotationAngle(270f);
        xAxis.setLabelCount(data.length);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setDrawGridLines(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        Legend legend = chart.getLegend();
        legend.setEnabled(false);

        chart.setFitBars(true);
        chart.invalidate();

        layout.addView(chart, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }
}
