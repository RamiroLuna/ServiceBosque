package org.probosque.controller;

import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.probosque.dao.LayersDAO;
import org.probosque.dao.PrediosDAO;
import org.probosque.dao.TableDAO;
import org.probosque.dao.UserDAO;
import org.probosque.dto.ColumnDTO;
import org.probosque.dto.LayerDTO;
import org.probosque.dto.PointDTO;
import org.probosque.dto.TableDTO;
import org.probosque.dto.UserDTO;
import org.probosque.model.json.DataIdentify;
import org.probosque.model.json.OutputJson;
import org.probosque.model.json.PrediosJson;
import org.probosque.model.json.ResponseJson;


/**
 * Clase que administra cada una de las acciones que hara el servlet en base a la petición de información de predios geográficos
 * @author admin
 */

public class ControllerPredios {

    public PrediosJson getPoints(HttpServletRequest request) throws SQLException, Exception {
        double xmin = Double.parseDouble(request.getParameter("xmin"));
        double xmax = Double.parseDouble(request.getParameter("xmax"));
        double ymin = Double.parseDouble(request.getParameter("ymin"));
        double ymax = Double.parseDouble(request.getParameter("ymax"));
        String _layer = request.getParameter("layer");
        String _user = request.getParameter("user");
        
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));
        
        PrediosDAO dao = new PrediosDAO();
        PrediosJson prediosJson = new PrediosJson();
        prediosJson.setFeatures(dao.getPoints(user, _layer, /*user, */xmin, ymin, xmax, ymax));
        return prediosJson;
    }
    
    public OutputJson identify(HttpServletRequest request) {
        String _folio = request.getParameter("folio");
        String _user = request.getParameter("user");        
        String _layer = request.getParameter("idLayer");        
        String _activity = request.getParameter("activity");

        Gson gson = new Gson();

        OutputJson output = new OutputJson();
        ResponseJson response = new ResponseJson();
        
        try {
            UserDAO userDao = new UserDAO();
            UserDTO user = userDao.getUser(Integer.parseInt(_user));
    
            
            
            response.setSucessfull(true);
            response.setMessage("Formulario");
            
            
            TableDAO dao = new TableDAO();
            
            TableDTO table = new TableDTO();
            table.setColumns((ArrayList<ColumnDTO>) dao.getColumnsByIdentify(user, _activity, _folio));
            
            List<ColumnDTO> columns = dao.getValues(user, _activity, table, _folio).getColumns();
            String folio = "";
            if (_folio == null) {
                for (ColumnDTO column : columns) {
                    if (column.getName().equals("folio")) {
                        folio = String.valueOf(column.getValue());
                    }
                }
            }
            else{
                folio = _folio;
            }
            
            for(ColumnDTO column : columns){
                if(column.getDatatype().equals("records")){
                    column.setRecords(String.valueOf(dao.getRecords(user, column.getName(),folio)));
                }
            }
            table.setColumns((ArrayList<ColumnDTO>) columns);
            
            TableDTO tableShape = new TableDTO();
            tableShape.setColumns((ArrayList<ColumnDTO>) dao.getColumnsShape(user, _layer, folio));
            //table = dao.setValues(table);
            
            List<ColumnDTO> columnsShape = dao.getValuesFromShape(user, tableShape, _layer, folio).getColumns();
            // getValues(user, tableShape, _folio).getColumns();
            folio = "";
            if (_folio == null) {
                for (ColumnDTO column : columnsShape) {
                    if (column.getName().equals("folio")) {
                        folio = String.valueOf(column.getValue());
                    }
                }
            }
            else{
                folio = _folio;
            }
            
            for(ColumnDTO column : columnsShape){
                if(column.getDatatype().equals("records")){
                    column.setRecords(String.valueOf(dao.getRecords(user, column.getName(),folio)));
                }
            }
            tableShape.setColumns((ArrayList<ColumnDTO>) columnsShape);
            
            DataIdentify dataIdentify =  new DataIdentify();
            dataIdentify.setShape(tableShape.getHash());
            dataIdentify.setTabular(table.getHash());
            //output.setData(table.getHash());
            output.setData(dataIdentify);
            
            
            
        } catch (Exception ex) {
            response.setSucessfull(false);
            response.setMessage(ex.getMessage());
        }
        output.setResponse(response);
        return output;

    }
    
    

    public List<PointDTO> find(HttpServletRequest request) throws SQLException, Exception {
        String predio = request.getParameter("folios");

        String _user = request.getParameter("user");
        UserDAO userDao = new UserDAO();
        UserDTO user = userDao.getUser(Integer.parseInt(_user));

        String schemaName = "programaX";
        schemaName = schemaName.replace("X", String.valueOf(user.getProgram()));

        schemaName = "capasgeograficas";
        
        LayersDAO layersDao = new LayersDAO();

        //List<LayerDTO> layers = layersDao.getLayers(schemaName);
        List<LayerDTO> layers = layersDao.getLayers(user);
        
        ArrayList<PointDTO> points = new ArrayList<>();

        PrediosDAO dao = new PrediosDAO();

        for (LayerDTO layer : layers) {

            List<PointDTO> list = dao.find(user, schemaName, layer.getId(), predio);

            for (PointDTO point : list) {
                points.add(point);
            }
        }

        return points;
    }
}