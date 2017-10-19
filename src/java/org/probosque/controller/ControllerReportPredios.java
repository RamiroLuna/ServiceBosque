package org.probosque.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.ProgramaDAO;
import org.probosque.dao.SumatoriaDAO;
import org.probosque.dto.ProgramaDTO;
import org.probosque.dto.ReportPredioColumnDTO;
import org.probosque.dto.ReportPredioDTO;
import org.probosque.dto.SumatoriaDTO;
import org.probosque.model.json.ReportPrediosRowJson;

/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información tabular en base a un filtro, y ademàs con perfil ejecutivo
 * @author admin
 */

public class ControllerReportPredios {
    public int reporte=0;
    public ArrayList<ReportPrediosRowJson> getReport(HttpServletRequest request) throws SQLException, Exception {
        ArrayList<ReportPrediosRowJson> listReport = new ArrayList<>();
        //VPA
        String _dato = request.getParameter("dato");
        String _t_busqueda = request.getParameter("t_busqueda");
        reporte=Integer.parseInt(_t_busqueda);
        //VPA
        String _anios = request.getParameter("anios");
        String _programs = request.getParameter("programas");
        System.out.println("tipo"+ _t_busqueda);
        System.out.println("dato"+ _dato);
        String[] anios = _anios.split(",");
        String[] programs = _programs.split(",");
        ProgramaDAO programaDao = new ProgramaDAO();
        
        for (String anio : anios) {
            ReportPrediosRowJson reportRow = new ReportPrediosRowJson();
            reportRow.setAnio(anio);
            
            ArrayList<ReportPredioDTO> listPrograms = new ArrayList<>();
            ArrayList<String> arreglo;
            for(String program : programs){
                ProgramaDTO programa = programaDao.getProgram(Integer.parseInt(program));
                ReportPredioDTO report = new ReportPredioDTO();
                
                SumatoriaDAO sumatoriaDao = new SumatoriaDAO();
                
                List<SumatoriaDTO> sumatorias = sumatoriaDao.getSumatorias(Integer.valueOf(program));
                
                report.setPrograma(programa.getDescripcion());
                
                arreglo = new ArrayList<>();
                for(SumatoriaDTO sumatoria : sumatorias){
                    arreglo.add(sumatoria.getUnidadmedida());
                }
                report.setUnidadmedida(arreglo);
                
                arreglo = new ArrayList<>();
                for(SumatoriaDTO sumatoria : sumatorias){
                    if(sumatoria.getCantidad()!=null && !sumatoria.getCantidad().isEmpty()){
                        Random r = new Random();
                        int limite = 1000;
                        arreglo.add(String.valueOf(r.nextInt(limite + 1)));
                    }
                    else{
                        arreglo.add("");
                    }
                }
                report.setCantidad(arreglo);
                
                arreglo = new ArrayList<>();
                for(SumatoriaDTO sumatoria : sumatorias){
                    arreglo.add(sumatoria.getAccion());
                }
                report.setAccion(arreglo);
                
                arreglo = new ArrayList<>();
                for(SumatoriaDTO sumatoria : sumatorias){
                    if(sumatoria.getMontoapoyoaprobado()!=null && !sumatoria.getMontoapoyoaprobado().isEmpty()){
                        Random r = new Random();
                        int limite = 1000;
                        arreglo.add(String.valueOf(r.nextInt(limite + 1)));
                    }
                    else{
                        arreglo.add("");
                    }
                    //arreglo.add(sumatoria.getMontoapoyoaprobado());
                }
                report.setMontoapoyoaprobado(arreglo);
                
                
                arreglo = new ArrayList<>();
                for(SumatoriaDTO sumatoria : sumatorias){
                    if(sumatoria.getMontoapoyopagado()!=null && !sumatoria.getMontoapoyopagado().isEmpty()){
                        Random r = new Random();
                        int limite = 1000;
                        arreglo.add(String.valueOf(r.nextInt(limite + 1)));
                    }
                    else{
                        arreglo.add("");
                    }
                    //arreglo.add(sumatoria.getMontoapoyopagado());
                }
                report.setMontoapoyopagado(arreglo);
                
                arreglo = new ArrayList<>();
                for(SumatoriaDTO sumatoria : sumatorias){
                    if(sumatoria.getResultado()!=null && !sumatoria.getResultado().isEmpty()){
                        Random r = new Random();
                        int limite = 1000;
                        arreglo.add(String.valueOf(r.nextInt(limite + 1)));
                    }
                    else{
                        arreglo.add("");
                    }
                    arreglo.add(sumatoria.getResultado());
                }
                report.setResultado(arreglo);
                
                 arreglo = new ArrayList<>();
                for(SumatoriaDTO sumatoria : sumatorias){
                    arreglo.add(sumatoria.getObservaciones());
                }
                report.setObservaciones(arreglo);
                
                listPrograms.add(report);
            }
            reportRow.setData(listPrograms);
            
            listReport.add(reportRow);
        }
        
        return listReport;
    }

    public ReportPredioColumnDTO getColumns() {
        ReportPredioColumnDTO columns = new ReportPredioColumnDTO();
        
        columns.setNombreDelPredio("Nombre del Predio");                
        columns.setPrograma("Programa");
        columns.setUnidadmedida("Unidad medida");
        columns.setCantidad("Cantidad");
        columns.setAccion("Acción");
        columns.setMontoapoyoaprobado("Monto de Apoyo Aprobado $");
        columns.setMontoapoyopagado("Monto de Apoyo Pagado $");
        columns.setResultado("Resultado");
        columns.setObservaciones("Observaciones");
        return columns;
    }
}
