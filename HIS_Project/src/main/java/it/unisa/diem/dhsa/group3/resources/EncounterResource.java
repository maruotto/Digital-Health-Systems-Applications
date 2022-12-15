package it.unisa.diem.dhsa.group3.resources;

//import java.util.Date;
import java.sql.Date;

import org.hl7.fhir.r4.model.*;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import it.unisa.diem.dhsa.group3.state.Memory;

public class EncounterResource {
	
	//Id,START,STOP,PATIENT,ORGANIZATION,PROVIDER,PAYER,ENCOUNTERCLASS,CODE,DESCRIPTION,BASE_ENCOUNTER_COST,TOTAL_CLAIM_COST,
	//PAYER_COVERAGE,REASONCODE,REASONDESCRIPTION
	
	
	@CsvBindByName
	private String Id;

	@CsvBindByName
	@CsvDate("yyyy-MM-dd")
	private Date START;
	
	@CsvBindByName
	@CsvDate("yyyy-MM-dd")
	private Date STOP;
	
	@CsvBindByName
	private String PATIENT = "";
	
	@CsvBindByName
	private String ORGANIZATION = "";
	
	@CsvBindByName
	private String PROVIDER = "";
	
	@CsvBindByName
	private String PAYER = "";
	
	@CsvBindByName
	private String ENCOUNTERCLASS = "";
	
	@CsvBindByName
	private Float CODE;
	
	@CsvBindByName
	private String DESCRIPTION = "";
	
	@CsvBindByName
	private Float BASE_ENCOUNTER_COST;
	
	@CsvBindByName
	private Float TOTAL_CLAIM_COST;
	
	@CsvBindByName
	private Float PAYER_COVERAGE;
	
	@CsvBindByName
	private String REASONCODE; //o è un numero o è null
	
	@CsvBindByName
	private String REASONDESCRIPTION;
	
	
	public String getId() {
		return Id;
	}


	public void setId(String id) {
		Id = id;
	}


	public Date getSTART() {
		return START;
	}


	public void setSTART(Date sTART) {
		START = sTART;
	}


	public Date getSTOP() {
		return STOP;
	}


	public void setSTOP(Date sTOP) {
		STOP = sTOP;
	}


	public String getPATIENT() {
		return PATIENT;
	}


	public void setPATIENT(String pATIENT) {
		PATIENT = pATIENT;
	}


	public String getORGANIZATION() {
		return ORGANIZATION;
	}


	public void setORGANIZATION(String oRGANIZATION) {
		ORGANIZATION = oRGANIZATION;
	}


	public String getPROVIDER() {
		return PROVIDER;
	}


	public void setPROVIDER(String pROVIDER) {
		PROVIDER = pROVIDER;
	}


	public String getPAYER() {
		return PAYER;
	}


	public void setPAYER(String pAYER) {
		PAYER = pAYER;
	}


	public String getENCOUNTERCLASS() {
		return ENCOUNTERCLASS;
	}


	public void setENCOUNTERCLASS(String eNCOUNTERCLASS) {
		ENCOUNTERCLASS = eNCOUNTERCLASS;
	}


	public Float getCODE() {
		return CODE;
	}


	public void setCODE(Float cODE) {
		CODE = cODE;
	}


	public String getDESCRIPTION() {
		return DESCRIPTION;
	}


	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}


	public Float getBASE_ENCOUNTER_COST() {
		return BASE_ENCOUNTER_COST;
	}


	public void setBASE_ENCOUNTER_COST(Float bASE_ENCOUNTER_COST) {
		BASE_ENCOUNTER_COST = bASE_ENCOUNTER_COST;
	}


	public Float getTOTAL_CLAIM_COST() {
		return TOTAL_CLAIM_COST;
	}


	public void setTOTAL_CLAIM_COST(Float tOTAL_CLAIM_COST) {
		TOTAL_CLAIM_COST = tOTAL_CLAIM_COST;
	}


	public Float getPAYER_COVERAGE() {
		return PAYER_COVERAGE;
	}


	public void setPAYER_COVERAGE(Float pAYER_COVERAGE) {
		PAYER_COVERAGE = pAYER_COVERAGE;
	}


	public String getREASONCODE() {
		return REASONCODE;
	}


	public void setREASONCODE(String rEASONCODE) {
		REASONCODE = rEASONCODE;
	}


	public String getREASONDESCRIPTION() {
		return REASONDESCRIPTION;
	}


	public void setREASONDESCRIPTION(String rEASONDESCRIPTION) {
		REASONDESCRIPTION = rEASONDESCRIPTION;
	}

	
	@Override
	public String toString() {
		return "EncounterResource [Id=" + Id + ", START=" + START + ", STOP=" + STOP + ", PATIENT=" + PATIENT
				+ ", ORGANIZATION=" + ORGANIZATION + ", PROVIDER=" + PROVIDER + ", PAYER=" + PAYER + ", ENCOUNTERCLASS="
				+ ENCOUNTERCLASS + ", CODE=" + CODE + ", DESCRIPTION=" + DESCRIPTION + ", BASE_ENCOUNTER_COST="
				+ BASE_ENCOUNTER_COST + ", TOTAL_CLAIM_COST=" + TOTAL_CLAIM_COST + ", PAYER_COVERAGE=" + PAYER_COVERAGE
				+ ", REASONCODE=" + REASONCODE + ", REASONDESCRIPTION=" + REASONDESCRIPTION + "]";
	}


	public Resource createResource() {

		Encounter e = new Encounter() ;
		// Definition of the considered profile
		e.setMeta(new Meta().addProfile("http://hl7.org/fhir/us/core/StructureDefinition/us-core-encounter"));
		
		//set identifier
		e.addIdentifier().setSystem("https://github.com/synthetichealth/synthea").setValue(Id);
		
		//set period
		Period period = new Period();
		period.setStart(START).setEnd(STOP);
		e.setPeriod(period);
		
		Memory memory = Memory.getMemory();
		
		//set patient
		Patient patient = (Patient) memory.get(PatientResource.class).get(PATIENT); //get the patient with id PATIENT
		Reference subject = new Reference();
		subject.setIdentifier(patient.getIdentifier().get(0)); //associate reference to patient 
		e.setSubject(subject);
		
		//set organization
		Organization o = (Organization) memory.get(OrganizationResource.class).get(ORGANIZATION);
		Reference serviceProvider = new Reference();
		serviceProvider.setIdentifier(o.getIdentifier().get(0));
		e.setServiceProvider(serviceProvider);
		
		
		//set provider....forse è participant?
		PractitionerRole practitioner = (PractitionerRole) memory.get(ProviderResource.class).get(PROVIDER);
		Encounter.EncounterParticipantComponent prac = new Encounter.EncounterParticipantComponent();
		
		Reference ref = new Reference();
		ref.setIdentifier(practitioner.getPractitionerTarget().getIdentifier().get(0));
		
		prac.setIndividual(ref).setIndividualTarget(practitioner.getPractitionerTarget()).setType(practitioner.getSpecialty());
		e.addParticipant(prac);
		
		//set payer
		//Resource payer = memory.get(PayerResource.class).get(PAYER);
		
		
		return e;
		
		
	}

	
	
	
	
	
	
	

}
