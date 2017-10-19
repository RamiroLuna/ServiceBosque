/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.probosque.dto;

/**
 *
 * @author Jonathan
 */
public class DPAnioDTO {
    private int año;
    private String region;
    private String municipio;
    private int Probosque;
    private float superficie_PROBOSQUE;
    private int CEPANAF;
    private float superficie_CEPANAF;
    private int CGCE;
    private float superficie_CGCE;
    private int SECyBS;
    private float superficie_SECyBS;
    private int Otro_Estatal; 
    private float superficie_Otro_estatal;
    private int SEMARNAT;
    private float superficie_SEMARNAT;
    private int CONAFOR;
    private float superficie_CONAFOR;
    private int CONANP;
    private float superficie_CONANP;
    private int CONAZA;
    private float superficie_CONAZA;
    private int CONAGUA;
    private float superficie_CONAGUA;
    private int SEDESOL;
    private float superficie_SEDESOL;
    private int BID;
    private float superficie_BID;
    private int Otro_federal;
    private float superficie_otro_federal;
    private int Cantidad_planta;
    private float Superficie;

    public DPAnioDTO() {
    }

    public DPAnioDTO(int año, String region, String municipio, int Probosque, float superficie_PROBOSQUE, int CEPANAF, float superficie_CEPANAF, int CGCE, float superficie_CGCE, int SECyBS, float superficie_SECyBS, int Otro_Estatal, float superficie_Otro_estatal, int SEMARNAT, float superficie_SEMARNAT, int CONAFOR, float superficie_CONAFOR, int CONANP, float superficie_CONANP, int CONAZA, float superficie_CONAZA, int CONAGUA, float superficie_CONAGUA, int SEDESOL, float superficie_SEDESOL, int BID, float superficie_BID, int Otro_federal, float superficie_otro_federal, int Cantidad_planta, float Superficie) {
        this.año = año;
        this.region = region;
        this.municipio = municipio;
        this.Probosque = Probosque;
        this.superficie_PROBOSQUE = superficie_PROBOSQUE;
        this.CEPANAF = CEPANAF;
        this.superficie_CEPANAF = superficie_CEPANAF;
        this.CGCE = CGCE;
        this.superficie_CGCE = superficie_CGCE;
        this.SECyBS = SECyBS;
        this.superficie_SECyBS = superficie_SECyBS;
        this.Otro_Estatal = Otro_Estatal;
        this.superficie_Otro_estatal = superficie_Otro_estatal;
        this.SEMARNAT = SEMARNAT;
        this.superficie_SEMARNAT = superficie_SEMARNAT;
        this.CONAFOR = CONAFOR;
        this.superficie_CONAFOR = superficie_CONAFOR;
        this.CONANP = CONANP;
        this.superficie_CONANP = superficie_CONANP;
        this.CONAZA = CONAZA;
        this.superficie_CONAZA = superficie_CONAZA;
        this.CONAGUA = CONAGUA;
        this.superficie_CONAGUA = superficie_CONAGUA;
        this.SEDESOL = SEDESOL;
        this.superficie_SEDESOL = superficie_SEDESOL;
        this.BID = BID;
        this.superficie_BID = superficie_BID;
        this.Otro_federal = Otro_federal;
        this.superficie_otro_federal = superficie_otro_federal;
        this.Cantidad_planta = Cantidad_planta;
        this.Superficie = Superficie;
    }

    /**
     * @return the año
     */
    public int getAño() {
        return año;
    }

    /**
     * @param año the año to set
     */
    public void setAño(int año) {
        this.año = año;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the Probosque
     */
    public int getProbosque() {
        return Probosque;
    }

    /**
     * @param Probosque the Probosque to set
     */
    public void setProbosque(int Probosque) {
        this.Probosque = Probosque;
    }

    /**
     * @return the superficie_PROBOSQUE
     */
    public float getSuperficie_PROBOSQUE() {
        return superficie_PROBOSQUE;
    }

    /**
     * @param superficie_PROBOSQUE the superficie_PROBOSQUE to set
     */
    public void setSuperficie_PROBOSQUE(float superficie_PROBOSQUE) {
        this.superficie_PROBOSQUE = superficie_PROBOSQUE;
    }

    /**
     * @return the CEPANAF
     */
    public int getCEPANAF() {
        return CEPANAF;
    }

    /**
     * @param CEPANAF the CEPANAF to set
     */
    public void setCEPANAF(int CEPANAF) {
        this.CEPANAF = CEPANAF;
    }

    /**
     * @return the superficie_CEPANAF
     */
    public float getSuperficie_CEPANAF() {
        return superficie_CEPANAF;
    }

    /**
     * @param superficie_CEPANAF the superficie_CEPANAF to set
     */
    public void setSuperficie_CEPANAF(float superficie_CEPANAF) {
        this.superficie_CEPANAF = superficie_CEPANAF;
    }

    /**
     * @return the CGCE
     */
    public int getCGCE() {
        return CGCE;
    }

    /**
     * @param CGCE the CGCE to set
     */
    public void setCGCE(int CGCE) {
        this.CGCE = CGCE;
    }

    /**
     * @return the superficie_CGCE
     */
    public float getSuperficie_CGCE() {
        return superficie_CGCE;
    }

    /**
     * @param superficie_CGCE the superficie_CGCE to set
     */
    public void setSuperficie_CGCE(float superficie_CGCE) {
        this.superficie_CGCE = superficie_CGCE;
    }

    /**
     * @return the SECyBS
     */
    public int getSECyBS() {
        return SECyBS;
    }

    /**
     * @param SECyBS the SECyBS to set
     */
    public void setSECyBS(int SECyBS) {
        this.SECyBS = SECyBS;
    }

    /**
     * @return the superficie_SECyBS
     */
    public float getSuperficie_SECyBS() {
        return superficie_SECyBS;
    }

    /**
     * @param superficie_SECyBS the superficie_SECyBS to set
     */
    public void setSuperficie_SECyBS(float superficie_SECyBS) {
        this.superficie_SECyBS = superficie_SECyBS;
    }

    /**
     * @return the Otro_Estatal
     */
    public int getOtro_Estatal() {
        return Otro_Estatal;
    }

    /**
     * @param Otro_Estatal the Otro_Estatal to set
     */
    public void setOtro_Estatal(int Otro_Estatal) {
        this.Otro_Estatal = Otro_Estatal;
    }

    /**
     * @return the superficie_Otro_estatal
     */
    public float getSuperficie_Otro_estatal() {
        return superficie_Otro_estatal;
    }

    /**
     * @param superficie_Otro_estatal the superficie_Otro_estatal to set
     */
    public void setSuperficie_Otro_estatal(float superficie_Otro_estatal) {
        this.superficie_Otro_estatal = superficie_Otro_estatal;
    }

    /**
     * @return the SEMARNAT
     */
    public int getSEMARNAT() {
        return SEMARNAT;
    }

    /**
     * @param SEMARNAT the SEMARNAT to set
     */
    public void setSEMARNAT(int SEMARNAT) {
        this.SEMARNAT = SEMARNAT;
    }

    /**
     * @return the superficie_SEMARNAT
     */
    public float getSuperficie_SEMARNAT() {
        return superficie_SEMARNAT;
    }

    /**
     * @param superficie_SEMARNAT the superficie_SEMARNAT to set
     */
    public void setSuperficie_SEMARNAT(float superficie_SEMARNAT) {
        this.superficie_SEMARNAT = superficie_SEMARNAT;
    }

    /**
     * @return the CONAFOR
     */
    public int getCONAFOR() {
        return CONAFOR;
    }

    /**
     * @param CONAFOR the CONAFOR to set
     */
    public void setCONAFOR(int CONAFOR) {
        this.CONAFOR = CONAFOR;
    }

    /**
     * @return the superficie_CONAFOR
     */
    public float getSuperficie_CONAFOR() {
        return superficie_CONAFOR;
    }

    /**
     * @param superficie_CONAFOR the superficie_CONAFOR to set
     */
    public void setSuperficie_CONAFOR(float superficie_CONAFOR) {
        this.superficie_CONAFOR = superficie_CONAFOR;
    }

    /**
     * @return the CONANP
     */
    public int getCONANP() {
        return CONANP;
    }

    /**
     * @param CONANP the CONANP to set
     */
    public void setCONANP(int CONANP) {
        this.CONANP = CONANP;
    }

    /**
     * @return the superficie_CONANP
     */
    public float getSuperficie_CONANP() {
        return superficie_CONANP;
    }

    /**
     * @param superficie_CONANP the superficie_CONANP to set
     */
    public void setSuperficie_CONANP(float superficie_CONANP) {
        this.superficie_CONANP = superficie_CONANP;
    }

    /**
     * @return the CONAZA
     */
    public int getCONAZA() {
        return CONAZA;
    }

    /**
     * @param CONAZA the CONAZA to set
     */
    public void setCONAZA(int CONAZA) {
        this.CONAZA = CONAZA;
    }

    /**
     * @return the superficie_CONAZA
     */
    public float getSuperficie_CONAZA() {
        return superficie_CONAZA;
    }

    /**
     * @param superficie_CONAZA the superficie_CONAZA to set
     */
    public void setSuperficie_CONAZA(float superficie_CONAZA) {
        this.superficie_CONAZA = superficie_CONAZA;
    }

    /**
     * @return the CONAGUA
     */
    public int getCONAGUA() {
        return CONAGUA;
    }

    /**
     * @param CONAGUA the CONAGUA to set
     */
    public void setCONAGUA(int CONAGUA) {
        this.CONAGUA = CONAGUA;
    }

    /**
     * @return the superficie_CONAGUA
     */
    public float getSuperficie_CONAGUA() {
        return superficie_CONAGUA;
    }

    /**
     * @param superficie_CONAGUA the superficie_CONAGUA to set
     */
    public void setSuperficie_CONAGUA(float superficie_CONAGUA) {
        this.superficie_CONAGUA = superficie_CONAGUA;
    }

    /**
     * @return the SEDESOL
     */
    public int getSEDESOL() {
        return SEDESOL;
    }

    /**
     * @param SEDESOL the SEDESOL to set
     */
    public void setSEDESOL(int SEDESOL) {
        this.SEDESOL = SEDESOL;
    }

    /**
     * @return the superficie_SEDESOL
     */
    public float getSuperficie_SEDESOL() {
        return superficie_SEDESOL;
    }

    /**
     * @param superficie_SEDESOL the superficie_SEDESOL to set
     */
    public void setSuperficie_SEDESOL(float superficie_SEDESOL) {
        this.superficie_SEDESOL = superficie_SEDESOL;
    }

    /**
     * @return the BID
     */
    public int getBID() {
        return BID;
    }

    /**
     * @param BID the BID to set
     */
    public void setBID(int BID) {
        this.BID = BID;
    }

    /**
     * @return the superficie_BID
     */
    public float getSuperficie_BID() {
        return superficie_BID;
    }

    /**
     * @param superficie_BID the superficie_BID to set
     */
    public void setSuperficie_BID(float superficie_BID) {
        this.superficie_BID = superficie_BID;
    }

    /**
     * @return the Otro_federal
     */
    public int getOtro_federal() {
        return Otro_federal;
    }

    /**
     * @param Otro_federal the Otro_federal to set
     */
    public void setOtro_federal(int Otro_federal) {
        this.Otro_federal = Otro_federal;
    }

    /**
     * @return the superficie_otro_federal
     */
    public float getSuperficie_otro_federal() {
        return superficie_otro_federal;
    }

    /**
     * @param superficie_otro_federal the superficie_otro_federal to set
     */
    public void setSuperficie_otro_federal(float superficie_otro_federal) {
        this.superficie_otro_federal = superficie_otro_federal;
    }

    /**
     * @return the Cantidad_planta
     */
    public int getCantidad_planta() {
        return Cantidad_planta;
    }

    /**
     * @param Cantidad_planta the Cantidad_planta to set
     */
    public void setCantidad_planta(int Cantidad_planta) {
        this.Cantidad_planta = Cantidad_planta;
    }

    /**
     * @return the Superficie
     */
    public float getSuperficie() {
        return Superficie;
    }

    /**
     * @param Superficie the Superficie to set
     */
    public void setSuperficie(float Superficie) {
        this.Superficie = Superficie;
    }
    
    
}
