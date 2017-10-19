package org.probosque.ejecutivodto;

public class PersonasAseguradasGeneralDTOP8 {

    public PersonasAseguradasGeneralDTOP8() {
    }

    public PersonasAseguradasGeneralDTOP8(PersonasAseguradasDetalleDTOP8 personasOperativos, PersonasAseguradasDetalleDTOP8 personasPredios) {
        this.personasOperativos = personasOperativos;
        this.personasPredios = personasPredios;
    }

PersonasAseguradasDetalleDTOP8 personasOperativos; 
PersonasAseguradasDetalleDTOP8 personasPredios; 

    public PersonasAseguradasDetalleDTOP8 getPersonasOperativos() {
        return personasOperativos;
    }

    public void setPersonasOperativos(PersonasAseguradasDetalleDTOP8 personasOperativos) {
        this.personasOperativos = personasOperativos;
    }

    public PersonasAseguradasDetalleDTOP8 getPersonasPredios() {
        return personasPredios;
    }

    public void setPersonasPredios(PersonasAseguradasDetalleDTOP8 personasPredios) {
        this.personasPredios = personasPredios;
    }
    
}
