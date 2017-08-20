package com.rbt.function;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.read.biff.BiffException;  
/**
 * @function 功能  excel 导入导出操作
 * @author  创建人  HXK
 * @date  创建日期  2014-05-9
 */

public class ExcelOperFuc extends CreateSpringContext{
	
	
	/** 
     * @param args 
     */  
    public static void main(String[] args) {  
        String fileName = "e:fashcard.xls";  
     // System.out.println(ExcelOperFuc.readExcelJoyCard("1",fileName));  
    }  
  
    /** 
     * 從excel文件中讀取所有的內容 
     *  
     * @param file 
     *            excel文件 
     * @return excel文件的內容 
     */  
    public static  String readExcelJoyCard(String user_id,String fileName) {  
    	//IJoycardService joycardService = (IJoycardService)CreateSpringContext.getContext().getBean("joycardService");
    	Integer coutnumber=0;//统计获得的条数
        Workbook wb = null;  
        try {  
            // 构造Workbook（工作薄）对象  
            wb = Workbook.getWorkbook(new File(fileName));  
        } catch (BiffException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        if (wb == null)  
            return null;  
        // 获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了  
        Sheet[] sheet = wb.getSheets();  
        if (sheet != null && sheet.length > 0) {  
        	    //获取所以数据
        	    List<Map<String,String>> joycardlist =new ArrayList<Map<String,String>>();
                // 得到当前工作表的行数  
                int rowNum = sheet[0].getRows();  
                for (int j = 2; j < rowNum; j++) {  
                    // 得到当前行的所有单元格  
                    Cell[] cells = sheet[0].getRow(j);  
                    if (cells != null && cells.length > 0&& cells[6].getContents()!=null&&!"".equals(cells[6].getContents())) {  
                    	 // 对每个单元格进行循环 且身份证不为空
                    	HashMap<String,String> joycardMap=new HashMap<String, String>();
                        for (int k = 0; k < cells.length; k++) {  
                            // 读取当前单元格的值  
                            String cellValue ="";
                            cellValue=cells[k].getContents(); 
                            System.out.println(cellValue);  
                            if(k==1){
                            	//发卡时间
                            	 joycardMap.put("pub_date", cellValue);
                            }else if(k==2){
                            	//会员卡号
                            	joycardMap.put("card_number", cellValue);
                            }else if(k==3){
                            	//姓名
                            	joycardMap.put("card_name", cellValue);
                            }else if(k==4){
                            	//性别
                            	if(cellValue.equals("男")){
                            		joycardMap.put("card_sex", "1");
                            	}else if (cellValue.equals("女")){
                            		joycardMap.put("card_sex", "2");
								}
                            	
                            }else if(k==5){
                            	//出生日期
                            	joycardMap.put("card_birthday", cellValue);
                            }else if(k==6){
                            	//身份证号码
                            	joycardMap.put("card_identity", cellValue);
                            }else if(k==7){
                            	//家庭住址
                            	joycardMap.put("card_address", cellValue);
                            }else if(k==8){
                            	//邮编
                            	joycardMap.put("card_post", cellValue);
                            }else if(k==9){
                            	//联系电话
                            	joycardMap.put("card_tel", cellValue);
                            }else if(k==10){
                            	//电子邮件
                            	joycardMap.put("card_email", cellValue);
                            }else if(k==11){
                            	//QQ号码
                            	joycardMap.put("card_qq", cellValue);
                            }else if(k==12){
                            	//微信号码
                            	joycardMap.put("card_wx", cellValue);
                            }else if(k==13){
                            	//附属卡姓名
                            	joycardMap.put("sup_card_name", cellValue);
                            }else if(k==14){
                            	//附属卡性别
                            	if(cellValue.equals("男")){
                            		joycardMap.put("sup_card_sex", "1");
                            	}else if (cellValue.equals("女")){
                            		joycardMap.put("sup_card_sex", "2");
								}
                            }else if(k==15){
                            	//附属卡身份证
                            	joycardMap.put("sup_card_identity", cellValue);
                            }else if(k==16){
                            	//附属卡联系电话
                            	joycardMap.put("sup_card_tel", cellValue);
                            }else if(k==17){
                            	//附属卡电子邮箱
                            	joycardMap.put("sup_card_email", cellValue);
                            }else if(k==18){
                            	//附属卡QQ号码
                            	joycardMap.put("sup_card_qq", cellValue);
                            }else if(k==19){
                            	//附属卡微信号码
                            	joycardMap.put("sup_card_wx", cellValue);
                            }
                        }
                        //操作人 
                        joycardMap.put("user_id",user_id);
                        //附属卡类型 1:时尚卡，2：欢乐卡
                        joycardMap.put("card_type", "2");  
                        joycardlist.add(joycardMap);
                        coutnumber++;
                    }
                    
                }  
               // joycardService.importExcelList(joycardlist);
        }  
        // 最后关闭资源，释放内存  
        wb.close();  
        return coutnumber.toString();  
       
    }  
    
    /** 
     * 從excel文件中讀取所有的內容 
     *  
     * @param file 
     *            excel文件 
     * @return excel文件的內容 
     */  
    public String readExcelFashCard(String user_id,String fileName) {  
    	//IFashcardService fashcardService = (IFashcardService)CreateSpringContext.getContext().getBean("fashcardService");
    	Integer coutnumber=0;//统计获得的条数
        Workbook wb = null;  
        try {  
            // 构造Workbook（工作薄）对象  
            wb = Workbook.getWorkbook(new File(fileName));  
        } catch (BiffException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        if (wb == null)  
            return null;  
        // 获得了Workbook对象之后，就可以通过它得到Sheet（工作表）对象了  
        Sheet[] sheet = wb.getSheets();  
        if (sheet != null && sheet.length > 0) {  
        	    //获取所以数据
        	    List<Map<String,String>> fashcardlist =new ArrayList<Map<String,String>>();
                // 得到当前工作表的行数  
                int rowNum = sheet[0].getRows();  
                for (int j = 2; j < rowNum; j++) {  
                    // 得到当前行的所有单元格  
                    Cell[] cells = sheet[0].getRow(j);  
                    if (cells != null && cells.length > 0&& cells[8].getContents()!=null&&!"".equals(cells[8].getContents())) {  
                    	 // 对每个单元格进行循环 且身份证不为空
                    	HashMap<String,String> fashcardMap=new HashMap<String, String>();
                        for (int k = 0; k < cells.length; k++) {  
                            // 读取当前单元格的值  
                            String cellValue ="";
                            cellValue=cells[k].getContents(); 
                            if(k==1){
                            	//发卡时间
                            	fashcardMap.put("pub_date", cellValue);
                            }else if(k==2){
                            	//会员卡号
                            	fashcardMap.put("card_number", cellValue);
                            }else if(k==3){
                            	//成交铺位号
                            	fashcardMap.put("card_pos", cellValue);
                            }else if(k==4){
                            	//客户经理
                            	fashcardMap.put("cus_manager", cellValue);
                            }else if(k==5){
                            	//姓名
                            	fashcardMap.put("card_name", cellValue);
                            }else if(k==6){
                            	//性别
                            	if(cellValue.equals("男")){
                            		fashcardMap.put("card_sex", "1");
                            	}else if (cellValue.equals("女")){
                            		fashcardMap.put("card_sex", "2");
								}
                            	
                            }else if(k==7){
                            	//出生日期
                            	fashcardMap.put("card_birthday", cellValue);
                            }else if(k==8){
                            	//身份证号码
                            	fashcardMap.put("card_identity", cellValue);
                            }else if(k==9){
                            	//家庭住址
                            	fashcardMap.put("card_address", cellValue);
                            }else if(k==10){
                            	//邮编
                            	fashcardMap.put("card_post", cellValue);
                            }else if(k==11){
                            	//联系电话
                            	fashcardMap.put("card_tel", cellValue);
                            }else if(k==12){
                            	//电子邮件
                            	fashcardMap.put("card_email", cellValue);
                            }else if(k==13){
                            	//QQ号码
                            	fashcardMap.put("card_qq", cellValue);
                            }else if(k==14){
                            	//微信号码
                            	fashcardMap.put("card_wx", cellValue);
                            }else if(k==15){
                            	//附属卡姓名
                            	fashcardMap.put("sup_card_name", cellValue);
                            }else if(k==16){
                            	//附属卡性别
                            	if(cellValue.equals("男")){
                            		fashcardMap.put("sup_card_sex", "1");
                            	}else if (cellValue.equals("女")){
                            		fashcardMap.put("sup_card_sex", "2");
								}
                            }else if(k==17){
                            	//附属卡身份证
                            	fashcardMap.put("sup_card_identity", cellValue);
                            }else if(k==18){
                            	//附属卡联系电话
                            	fashcardMap.put("sup_card_tel", cellValue);
                            }else if(k==19){
                            	//附属卡电子邮箱
                            	fashcardMap.put("sup_card_email", cellValue);
                            }else if(k==20){
                            	//附属卡QQ号码
                            	fashcardMap.put("sup_card_qq", cellValue);
                            }else if(k==21){
                            	//附属卡微信号码
                            	fashcardMap.put("sup_card_wx", cellValue);
                            }
                        }
                        //操作人 
                        fashcardMap.put("user_id",user_id);
                        //附属卡类型 1:时尚卡，2：欢乐卡
                        fashcardMap.put("card_type", "1");  
                        fashcardlist.add(fashcardMap);
                        coutnumber++;
                    }
                    
                }  
             //   fashcardService.importExcelList(fashcardlist);
        }  
        // 最后关闭资源，释放内存  
        wb.close();  
        return coutnumber.toString();  
       
    }  
	
}
