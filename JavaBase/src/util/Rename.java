/**
 * 
 */
/**
 * @author Jiang
 * rename files names in the same folder
 */
package util;

import   java.io.*;     

public   class   Rename   
{     
        public   static   void   main(String[]   args)   {     
        	
          String dir = "G:/计算机学习/ETL/【04.DATA STAGE视频教程】/06.天善智能Datastage 工具实战讲解 2课/";
          File directory = new File(dir); 
          
          File []files=directory.listFiles();
          
          String filename;

          for(int i=0;i<files.length;i++){
        	  filename = files[i].toString();
        	  if(filename.contains("[www.17zixueba.com]")){
        		  filename = filename.substring(filename.indexOf("[www.17zixueba.com]")+19);
        		  File newFile = new File(dir+filename);
        		  files[i].renameTo(newFile);
        		  System.out.println("ok.");
        	  }
        	  System.out.println(filename);
          }
      }     
      
}  