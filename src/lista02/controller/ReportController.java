package lista02.controller;


import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.commons.collections4.map.ReferenceMap;

import lista02.model.DatabaseManager;

public class ReportController {

    Connection conn = DatabaseManager.getInstance().getConnection();
    ResultSet rs = null;

    public void report(int id) {

        try {
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM projeto WHERE id = ?");
            statement.setInt(1, id);
            rs = statement.executeQuery();
            String caminhoRelativo = "/reports/report.jasper";
            URL arquivo = getClass().getResource(caminhoRelativo);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(arquivo);
            Map<String, Object> params = new HashMap<>();
            JRResultSetDataSource jrRS = new JRResultSetDataSource(rs);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, params, jrRS);
            JasperViewer.viewReport(print, false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro: " + e);
            System.out.println("Erro: " + e);
        }

    }
}
