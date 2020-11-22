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
import ui.windows.subwindows.inputwindows.GetterWindow;

import java.awt.*;

public class ViewGraph extends GetterWindow {

    public ViewGraph(Container container, Home home) {
        super(container, home);
    }

    @Override
    public void updateGUI() {
        getAccount(this::loadGraph);
    }

    // https://stackoverflow.com/questions/12837986/how-to-display-date-in-a-x-axis-of-line-graph-using-jfreechart
    // https://caveofprogramming.com/java/charts-in-java-swing-with-jfreechart.html
    private void loadGraph() {
        TimeSeriesCollection<String> collection = getTimeSeriesCollection(acc);
        buildGraph(acc, collection);
    }

    private void buildGraph(Account acc, TimeSeriesCollection<String> collection) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(acc.getName(),"Date",
                "Total (" + acc.getCurrency().getSymbol() + ")", collection);

        chart.removeLegend();

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension((int)(container.getWidth() * 0.5),
                (int)(container.getHeight() * 0.5)));
        reset();
        container.add(chartPanel);
        container.add(back);
        addBackButtonListener();
    }


    private TimeSeriesCollection<String> getTimeSeriesCollection(Account acc) {
        History data = MoveMoneyFunctions.buildData(acc);
        TimeSeries<String> timeSeries = new TimeSeries<>("Data");
        for (LogEntry entry : data) {
            Day day = new Day(entry.getDay(),entry.getMonth(),entry.getYear());
            timeSeries.add(day, (double) entry.getTotal() / 100);
        }

        TimeSeriesCollection<String> collection = new TimeSeriesCollection<>();
        collection.addSeries(timeSeries);
        return collection;
    }

}
