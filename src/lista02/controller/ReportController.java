package lista02.controller;


import lista02.model.DatabaseManager;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

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
            assert arquivo != null;
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
