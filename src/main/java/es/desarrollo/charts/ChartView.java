package es.desarrollo.charts;

import org.primefaces.model.chart.PieChartModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class ChartView implements Serializable{
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;

    @PostConstruct
    public void init() {
        createPieModels();
    }

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public PieChartModel getPieModel2() {
        return pieModel2;
    }

    private void createPieModels() {
        createPieModel1();
        createPieModel2();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();

        pieModel1.set("Aceite Quemado", 540);
        pieModel1.set("Ctrl. Alimento Piscina", 325);
        pieModel1.set("Agua Purificada", 702);

        pieModel1.setTitle("Reportes Revisados");
        pieModel1.setLegendPosition("w");
    }

    private void createPieModel2() {
        pieModel2 = new PieChartModel();

        pieModel2.set("Brand 1", 540);
        pieModel2.set("Brand 2", 325);
        pieModel2.set("Brand 3", 702);
        pieModel2.set("Brand 4", 421);

        pieModel2.setTitle("Custom Pie");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }

}
