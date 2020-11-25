package ui.windows.subwindows.inputwindows.manageaccounts;

import model.History;
import model.LogEntry;
import model.MoveMoneyFunctions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.*;
import ui.windows.Home;
import ui.windows.subwindows.inputwindows.InputWindow;

import java.awt.*;

// Represents a window where the user can choose an account to make a graph of it's total values over time
public class ViewGraph extends InputWindow {

    public ViewGraph(Container container, Home home) {
        super(container, home);
    }

    // EFFECTS: Prompts user for an account, creates graph
    @Override
    public void updateGUI() {
        getAccount(this::buildGraph);
    }

    //// SOURCES: ////
    // https://stackoverflow.com/questions/12837986/how-to-display-date-in-a-x-axis-of-line-graph-using-jfreechart
    // https://caveofprogramming.com/java/charts-in-java-swing-with-jfreechart.html
    // I used the response by GrahamA on stackoverflow, and the overall structure of the examples given by
    // caveofprogramming in tandem to design the methods of this class, and adapted them for the needs of this program

    // MODIFIES: container
    // EFFECTS: Builds the frame of the graph, fills it with data, adds to GUI
    private void buildGraph() {
        TimeSeriesCollection<String> collection = getTimeSeriesCollection();
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

    // EFFECTS: Builds collection of dates and corresponding totals, from acc history
    private TimeSeriesCollection<String> getTimeSeriesCollection() {
        History data = MoveMoneyFunctions.buildData(acc);

        int dif = getInitBal();

        TimeSeries<String> timeSeries = new TimeSeries<>("Data");
        for (LogEntry entry : data) {
            Day day = new Day(entry.getDay(),entry.getMonth(),entry.getYear());
            timeSeries.add(day, (double)(entry.getTotal() + dif) / 100);
        }

        TimeSeriesCollection<String> collection = new TimeSeriesCollection<>();
        collection.addSeries(timeSeries);
        return collection;
    }

    // EFFECTS: Returns the initial value of acc
    // This needs to be separate as an account's initial balance is technically never deposited, as is not
    //      stored so dif is equal to that amount, as calculated by subtracting the total of all past actions
    // This is probably not great design, should really be overhauled at some point
    private int getInitBal() {
        int sum = 0;
        for (LogEntry entry: acc.getHistory()) {
            sum += entry.getVal();
        }
        return acc.getBalance() - sum;
    }

}
