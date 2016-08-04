package com.hcsc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@ManagedBean
@SessionScoped
@Log4j
public class CodeGenerator {
 
 @Getter @Setter
 private int row;
 
 @Getter @Setter
 private int column;
 
 @Getter @Setter
 private String ruleID;
 
 @Getter @Setter
 private String author;
 
 @Getter @Setter
 private String ruleFile;
 
 @Getter @Setter
 private String description;
 
 @Getter @Setter
 private String rulePackage;
 
 @Getter @Setter
 List<ColumnModel> columns;
 
 @Getter @Setter
 private List<ListModel> cars;
 
 public void createDynamicColumns() {

  columns = new ArrayList<ColumnModel>();
  for (int i = 1; i <= column; i++) {
   columns.add(new ColumnModel("column".toUpperCase() + " " + i,
     "column" + i));
  }

  cars = new ArrayList<ListModel>();
  for (int i = 1; i <= row; i++) {
   cars.add(new ListModel());
  }

 }
 
 public static void main(String arg[]){
	 CodeGenerator testObj=new CodeGenerator();
	 testObj.ruleID="MC123";
	 testObj.author="i346645";
	 testObj.ruleFile="Elig Amt Must be Entered when Interim Amt";
	 testObj.description="This method tests the rule MC186 and Populate Error Message";
	 testObj.rulePackage="ManualCalculation";
	 
	 ListModel obj=new ListModel();
	 obj.column1="manualCalcType";
	 obj.column2="majMedInEligAmt";	 
	 obj.column3="majMedInterim";
	 obj.column4="expectedErrorCode";
	 
	 testObj.cars = new ArrayList<ListModel>();
	 testObj.cars.add(obj);
	 
	 testObj.fileGenerator();
 }
 
 public void fileGenerator(){
	 csvFile();
	 junitFile();
 }
 
 
 public void csvFile(){

	  try{
	   String fileName="C:/Apps/"+ruleID+".csv";
	   FileWriter fileWriter = new FileWriter(fileName);
	   
	   for (int i = 0; i < cars.size(); i++) {
	    fileWriter.append(cars.get(i).getDeatil());
	    fileWriter.append("\n");
	   }
	    fileWriter.flush();
	    fileWriter.close();
	    
	   
	  }
	  catch(Exception e){
	   log.error("CSV File Generator Failed !!",e);
	  }
	 
 }
 
 public void junitFile(){
	 try{


		    BufferedWriter writer = new BufferedWriter(new FileWriter("C:/Apps/"+ruleID+".txt"));
		    
		    ArrayList<String> aList=new ArrayList(Arrays.asList(cars.get(0).getDeatil().split(",")));
		    
		    System.out.println(aList.size());
		    System.out.println(aList.toString());
		    
		    writer.write("/**");
		    writer.newLine();
		    writer.write("* RuleID: "+ruleID);
		    writer.newLine();
		    writer.write("*");
		    writer.newLine();
		    writer.write("* @author: "+author);
		    writer.newLine();
		    writer.write("*");
		    writer.newLine();
		    writer.write("* {@link "+ruleFile+".brl}");
		    writer.newLine();
		    writer.write("*");
		    writer.newLine();
		    writer.write("* "+description);		    
		    writer.newLine();
		    writer.write("*/");
		    writer.newLine();
		    
		    writer.write("@Test");
		    writer.newLine();
		    writer.write("@FileParameters(value =\"classpath:testData/"+rulePackage+"/" + ruleID + " .csv\", mapper = CsvWithHeaderMapper.class)");
		    writer.newLine();
		    writer.write("@TestDocumentation(requirement = \"" + ruleID + "\", description = \"" + description + "\", reference = \""+aList.size()+"\", expectations = \""
		        + description + "\")");
		    writer.newLine();
		    writer.write("public void " + ruleID + "( ");
		    		    		   
		    for (int i = 0; i < aList.size(); i++) {
		      if (i < aList.size()-1) {
		        writer.write("String " + aList.get(i) + ", ");
		      } else {
		        writer.write("String " + aList.get(i));
		      }
		    }
		    
		    writer.write(") { ");
		    writer.newLine();	
		    writer.newLine();
		    writer.write("System.out.println(\" INPUT_VALUES FOR  " + ruleID + " ===========================================\"); ");
		    writer.newLine();
		    writer.newLine();
		    
		    for (int i = 0; i < aList.size(); i++) {
		      writer.write("System.out.println(\"" + aList.get(i) + " : \" +" + aList.get(i) + ");");
		      writer.newLine();
		    }
		    
		    writer.newLine();
		    writer.write("System.out.println(\" ===================================================================\"); ");
		    writer.newLine();
		    writer.newLine();
		    
		    
		    writer.write("InsuranceClaim insuranceClaim = InsuranceClaimBuilderMock.builder().build();");
		    writer.newLine();
		    writer.newLine();

		    writer.write("}");
		    writer.newLine();

		    writer.close();
		  
	 }
	 catch(Exception e){
		 log.error("Junit File Generator Failed !!",e);
	 }
 }
 
 
 static public class ColumnModel implements Serializable {

  private static final long serialVersionUID = 1L;
  private String header;
  private String property;

  public ColumnModel(String header, String property) {
   this.header = header;
   this.property = property;
  }

  public String getHeader() {
   return header;
  }

  public String getProperty() {
   return property;
  }
 }
 
 static public class ListModel {
  
  @Getter @Setter
  private String column1;
  @Getter @Setter
  private String column2;
  @Getter @Setter
  private String column3;
  @Getter @Setter
  private String column4;
  @Getter @Setter
  private String column5;
  @Getter @Setter
  private String column6;
  @Getter @Setter
  private String column7;
  @Getter @Setter
  private String column8;
  @Getter @Setter
  private String column9;
  @Getter @Setter
  private String column10;
  
  
  public String getDeatil() {
   StringBuilder sb = new StringBuilder();
   if(!StringUtils.isEmpty(column1))
    sb.append(column1);
   if(!StringUtils.isEmpty(column2))
    sb.append(","+column2);
   if(!StringUtils.isEmpty(column3))
    sb.append(","+column3);
   if(!StringUtils.isEmpty(column4))
    sb.append(","+column4);
   if(!StringUtils.isEmpty(column5))
    sb.append(","+column5);
   if(!StringUtils.isEmpty(column5))
    sb.append(","+column5);
   if(!StringUtils.isEmpty(column6))
    sb.append(","+column6);
   if(!StringUtils.isEmpty(column7))
    sb.append(","+column7);
   if(!StringUtils.isEmpty(column8))
    sb.append(","+column8);
   if(!StringUtils.isEmpty(column9))
    sb.append(","+column9);
   if(!StringUtils.isEmpty(column10))
    sb.append(","+column10);
   return sb.toString();
  }
  
  
 }

}


