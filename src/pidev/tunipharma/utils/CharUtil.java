

package pidev.tunipharma.utils;

import java.io.IOException;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import pidev.tunipharma.classes.Statistiques;
import pidev.tunipharma.dao.StatistiquesDAO;

public class CharUtil extends ApplicationFrame {

//    public CharUtil(final String title, JPanel p) throws IOException {
//        super(title);
//        final CategoryDataset dataset = createDataset(StatistiquesDAO.getInstance().readByIdType(Statistiques.STAT_INSCRIPTION));
//        final JFreeChart graphe = createChart(dataset);
//        final ChartPanel chartPanel = new ChartPanel(graphe);
//        
//        chartPanel.setPreferredSize(new java.awt.Dimension(400, 400));
//        p=chartPanel;
//        setContentPane(chartPanel);
//    }
    public static void  getChartPanel(String titre, int type,JPanel p) throws IOException{
        CategoryDataset dataset = createDataset(StatistiquesDAO.getInstance().readByIdType(type));
//        JFreeChart graphe = createChart(dataset);
//        p = new ChartPanel(graphe);
    }
    public static CategoryDataset createDataset(List<Statistiques> l) throws IOException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String nM[] = {"Janvier", "Fevrier", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Novembre", "Decembre"};
        for (Statistiques s : l) {
            dataset.addValue(s.getValeur_stat(), nM[s.getMois()], " ");
        }
        return dataset;
    }

    public static JFreeChart createChart(CategoryDataset dataset,String titre,String xName,String yName) {
        final JFreeChart chart = ChartFactory.createBarChart(
                titre, // chart title
                xName, // domain axis label
                yName, // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                true // urls
        );

        final CategoryPlot plot = chart.getCategoryPlot();
        final CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 2.0));
        final CategoryItemRenderer renderer = plot.getRenderer();
        renderer.setItemLabelsVisible(true);
        return chart;
    }

    public static void main(final String[] args) throws IOException {
//        final CharUtil demo = new CharUtil("",null);
//        demo.pack();
//        RefineryUtilities.centerFrameOnScreen(demo);
//        demo.setVisible(true);

    }

    public CharUtil(String title) {
        super(title);
    }
}