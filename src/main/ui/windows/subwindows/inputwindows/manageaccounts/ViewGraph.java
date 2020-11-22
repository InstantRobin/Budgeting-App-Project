package ui.windows.subwindows.inputwindows.manageaccounts;

import model.Account;
import model.History;
import model.LogEntry;
import model.MoveMoneyFunctions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.*;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.AccountSelectWindow;

import java.awt.*;

public class ViewGraph extends AccountSelectWindow {

    public ViewGraph(Container container, Home home) {
        super(container, home);
    }

    @Override
    public void updateGUI() {
        super.updateGUI(this::loadGraph);
    }

    // https://stackoverflow.com/questions/12837986/how-to-display-date-in-a-x-axis-of-line-graph-using-jfreechart

    private void loadGraph(Account acc) {
        History data = MoveMoneyFunctions.buildData(acc);
        TimeSeries<String> timeSeries = new TimeSeries<String>("Data");
        for (LogEntry entry : data) {
            Day day = new Day(entry.getDay(),entry.getMonth(),entry.getYear());
            timeSeries.add(day,Double.valueOf(entry.getTotal()) / 100);
        }

        TimeSeriesCollection<String> collection = new TimeSeriesCollection<String>();
        collection.addSeries(timeSeries);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(acc.getName(),"Date","Total",collection);
        ChartPanel chartPanel = new ChartPanel(chart);
        reset();
        container.add(chartPanel);
    }

}
