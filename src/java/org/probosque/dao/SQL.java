package org.probosque.dao;

import org.probosque.dto.UserDTO;

/**
 * Clase que retorna las tablas en base al usuario y programa; además en algunos programas recibe el parámetro de actividad para determinar que tabla retornar
 * @author admin
 */

public class SQL {

    public static final String TABLE_PREDIOS_INFO = "formularios.principal_predios_info";
    public static final String TABLE_PREDIOS_ANIOS_INFO = "formularios.principal_predios_anios_info";

    public static final String SCHEMA_CAPAS = "capasgeograficas";
    public static final String SCHEMA_FORMULARIOS = "formularios";
    public static final String TABLE_REGIONES = "general.regiones";
    public static final String TABLE_PROGRAMAS = "catalogos.programa";
    public static final String TABLE_SUMATORIAS = "formularios.sumatorias";

    public static final String TABLE_MAIN = "formularios.principal";
    public static final String TABLE_MAIN_9_SEMILLA = "formularios.principal_semilla";
    public static final String TABLE_MAIN_9_PLANTA = "formularios.principal_planta";
    public static final String TABLE_MAIN_7_INCENDIOS = "formularios.principal_incendios";
    public static final String TABLE_MAIN_7_ACTIVIDADES = "formularios.principal_actividades";

    public static final String TABLE_MAIN_reportes = "general.reportes";
    public static final String TABLE_MAIN_7_INCENDIOS_reportes = "general.reportes_incendios";
    public static final String TABLE_MAIN_7_ACTIVIDADES_reportes = "general.reportes_actividades";

    public static final String TABLE_MAIN_campos = "general.campo";
    public static final String TABLE_MAIN_7_INCENDIOS_campos = "general.campo_incendios";
    public static final String TABLE_MAIN_7_ACTIVIDADES_campos = "general.campo_actividades";


    
    public static final String TABLE_SEMILLA_BAJA = "formularios.semilla_bajasemilla";
    public static final String TABLE_PLANTA_BAJA = "formularios.planta_bajaplanta";
    
    public static final String TABLE_MAIN_EJECUTIVO_REQUEST = "formularios.principal_predios_request";

    public static final String TABLE_MAIN_EJECUTIVO_SUMMARY = "formularios.principal_predios_summary";

    public static String getTablePrediosInfo() {
        String tableInfo = TABLE_PREDIOS_INFO;
        return tableInfo;
    }

    public static String getTablePrediosAniosInfo() {
        String tableInfo = TABLE_PREDIOS_ANIOS_INFO;
        return tableInfo;
    }

    public static String getTableMain() {
        String tableMain = TABLE_MAIN;
        return tableMain;
    }

    
    public static String getTableMainCampos(UserDTO user, String activity) {
        String tableMain = TABLE_MAIN_campos;
        if (user.getProgram() == 7) {
            if (user.getActivity() == 3) {
                tableMain = TABLE_MAIN_7_INCENDIOS_campos;
            }
            if (user.getActivity() == 4) {
                tableMain = TABLE_MAIN_7_ACTIVIDADES_campos;
            }
        }
       
        return tableMain;
    }
    
    
    public static String getTableMainReportes(UserDTO user, String activity) {
        String tableMain = TABLE_MAIN_reportes;
        if (user.getProgram() == 7) {
            if (user.getActivity() == 3) {
                tableMain = TABLE_MAIN_7_INCENDIOS_reportes;
            }
            if (user.getActivity() == 4) {
                tableMain = TABLE_MAIN_7_ACTIVIDADES_reportes;
            }
        }
       
        return tableMain;
    }

    
    
    
    
    public static String getTableMain(UserDTO user, String activity) {
        String tableMain = TABLE_MAIN;
        if (user.getProgram() == 7) {
            if (user.getActivity() == 3) {
                tableMain = TABLE_MAIN_7_INCENDIOS;
            }
            if (user.getActivity() == 4) {
                tableMain = TABLE_MAIN_7_ACTIVIDADES;
            }
        }
        if (user.getProgram() == 9) {
            if (activity == null) {
                if (user.getActivity() == 1) {
                    tableMain = TABLE_MAIN_9_SEMILLA;
                }
                if (user.getActivity() == 2) {
                    tableMain = TABLE_MAIN_9_PLANTA;
                }
            } else {
                if (user.getActivity() == 1) {
                    tableMain = TABLE_SEMILLA_BAJA;
                }
                if (user.getActivity() == 2) {
                    tableMain = TABLE_PLANTA_BAJA;
                }
                //tableMain = TABLE_PLANTA_BAJA;
            }
        }
        return tableMain;
    }

    public static String getTableInfo(UserDTO user, String activity) {
        String info = "_info";
        String tableInfo = TABLE_MAIN + info;
        if (user.getProgram() == 7) {
            if (user.getActivity() == 3) {
                tableInfo = TABLE_MAIN_7_INCENDIOS + info;
            }
            if (user.getActivity() == 4) {
                tableInfo = TABLE_MAIN_7_ACTIVIDADES + info;
            }
        }
        if (user.getProgram() == 9) {
            if (activity == null) {
                if (user.getActivity() == 1) {
                    tableInfo = TABLE_MAIN_9_SEMILLA + info;
                }
                if (user.getActivity() == 2) {
                    tableInfo = TABLE_MAIN_9_PLANTA + info;
                }
            } else {
                if (user.getActivity() == 1) {
                    tableInfo = TABLE_SEMILLA_BAJA + info;
                }
                if (user.getActivity() == 2) {
                    tableInfo = TABLE_PLANTA_BAJA + info;
                }
                //tableInfo = TABLE_PLANTA_BAJA + info;
            }
        }
        return tableInfo;
    }

    public static String getTableOperations(UserDTO user, String activity) {
        String operations = "_operations";
        String tableInfo = TABLE_MAIN + operations;
        if (user.getProgram() == 7) {
            if (user.getActivity() == 3) {
                tableInfo = TABLE_MAIN_7_INCENDIOS + operations;
            }
            if (user.getActivity() == 4) {
                tableInfo = TABLE_MAIN_7_ACTIVIDADES + operations;
            }
        }
        if (user.getProgram() == 9) {
            if (activity == null) {
                if (user.getActivity() == 1) {
                    tableInfo = TABLE_MAIN_9_SEMILLA + operations;
                }
                if (user.getActivity() == 2) {
                    tableInfo = TABLE_MAIN_9_PLANTA + operations;
                }
            } else {
                if (user.getActivity() == 1) {
                    tableInfo = TABLE_SEMILLA_BAJA + operations;
                }
                if (user.getActivity() == 2) {
                    tableInfo = TABLE_PLANTA_BAJA + operations;
                }
                //tableInfo = TABLE_PLANTA_BAJA + operations;
            }
        }
        return tableInfo;
    }

    public static String getTableRequest(UserDTO user, String activity) {
        String request = "_request";
        String tableInfo = TABLE_MAIN + request;
        if (user.getProgram() == 7) {
            if (user.getActivity() == 3) {
                tableInfo = TABLE_MAIN_7_INCENDIOS + request;
            }
            if (user.getActivity() == 4) {
                tableInfo = TABLE_MAIN_7_ACTIVIDADES + request;
            }
        }
        if (user.getProgram() == 9) {
            if (activity == null) {
                if (user.getActivity() == 1) {
                    tableInfo = TABLE_MAIN_9_SEMILLA + request;
                }
                if (user.getActivity() == 2) {
                    tableInfo = TABLE_MAIN_9_PLANTA + request;
                }
            } else {
                if (user.getActivity() == 1) {
                    tableInfo = TABLE_SEMILLA_BAJA + request;
                }
                if (user.getActivity() == 2) {
                    tableInfo = TABLE_PLANTA_BAJA + request;
                }
                //tableInfo = TABLE_PLANTA_BAJA + request;
            }
        }
        if (user.getRole_id() == 4) {
            tableInfo = TABLE_MAIN_EJECUTIVO_REQUEST;
        }
        return tableInfo;
    }
    
    public static String getTableSummary(UserDTO user, String activity) {
        String summary = "_summary";
        String tableInfo = TABLE_MAIN + summary;
        if (user.getProgram() == 7) {
            if (user.getActivity() == 3) {
                tableInfo = TABLE_MAIN_7_INCENDIOS + summary;
            }
            if (user.getActivity() == 4) {
                tableInfo = TABLE_MAIN_7_ACTIVIDADES + summary;
            }
        }
        if (user.getProgram() == 9) {
            if (activity == null) {
                if (user.getActivity() == 1) {
                    tableInfo = TABLE_MAIN_9_SEMILLA + summary;
                }
                if (user.getActivity() == 2) {
                    tableInfo = TABLE_MAIN_9_PLANTA + summary;
                }
            } else {
                if (user.getActivity() == 1) {
                    tableInfo = TABLE_SEMILLA_BAJA + summary;
                }
                if (user.getActivity() == 2) {
                    tableInfo = TABLE_PLANTA_BAJA + summary;
                }
                //tableInfo = TABLE_PLANTA_BAJA + request;
            }
        }
        if (user.getRole_id() == 4) {
            tableInfo = TABLE_MAIN_EJECUTIVO_REQUEST;
        }
        return tableInfo;
    }

    public static String getDirectory(UserDTO user) {
        String directoryName = "programaX";
        directoryName = directoryName.replace("X", String.valueOf(user.getProgram()));
        return directoryName;
    }
}
