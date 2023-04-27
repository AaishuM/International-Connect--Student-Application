/*package com.uniguides.userscampusapply.User;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.uniguides.userscampusapply.R;

import java.util.ArrayList;

public class StatisticsGraph extends AppCompatActivity {

    ArrayList<BarEntry> barArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_graph);


        BarChart barChart = findViewById(R.id.barcharts);
        getDate();
        BarDataSet barDataSet = new BarDataSet(barArrayList, "\n" +
                "No of students from different countries got admission last year and this year ( graph)");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barDataSet.setColors (ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(true);
        //barChart.animateY(2000);
        //define the dataSet variable
        BarDataSet dataSet = new BarDataSet(barArrayList, "");

        String[] labels = {"India", "Pakistan", "Nigeria", "Kenya"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

// Set the data for the BarChart
        barData = new BarData(dataSet);
        barChart.setData(barData);
        barChart.invalidate();
    }

    private void getDate(){
        barArrayList = new ArrayList();
        barArrayList.add(new BarEntry( 2f, 20, "India " ));
        barArrayList.add(new BarEntry( 4f, 58, "Pakistan" ));
        barArrayList.add(new BarEntry( 3f, 62, "Nigeria " ));
        barArrayList.add(new BarEntry( 1f, 44, "kenya" ));
       // barArrayList.add(new BarEntry( 4f, 37 , "Egypt"));
    }


 }
*/
package com.uniguides.userscampusapply.User;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.uniguides.userscampusapply.R;

import java.util.ArrayList;

public class StatisticsGraph extends AppCompatActivity {

    ArrayList<PieEntry> pieArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics_graph);

        PieChart pieChart = findViewById(R.id.piechart);
        getData();
        PieDataSet pieDataSet = new PieDataSet(pieArrayList, "No of students from different countries got admission last year and this year (graph)");

        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(true);
        pieChart.animate();

    }

    private void getData() {
        pieArrayList = new ArrayList();
        pieArrayList.add(new PieEntry(20, "India "));
        pieArrayList.add(new PieEntry(58, "Pakistan"));
        pieArrayList.add(new PieEntry(62, "Nigeria "));
        pieArrayList.add(new PieEntry(44, "Kenya"));
        // pieArrayList.add(new PieEntry( 37 , "Egypt"));
    }
}
