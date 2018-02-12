package Chapter21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class MyThread2 extends Thread {
    private String msg;
    static boolean sta;
    public MyThread2(String s) {
        msg = s;
    }
    public void run() {
        String sr=sendPost("http://www.dalian-jw.gov.cn:8080/lhsfc/querycx.asp", msg);
        System.out.println(sr);
        sta=true;
    }

	public static void main(String[] args) {
	    
		String str="",str1="",str2="";
		
		int i=6,j=10275;
		str1="00"+i;
		str1=str1.substring(str1.length()-3);
		str2="0000"+j;
		str2=str2.substring(str2.length()-5);
		
		str="lsbh="+ str1 + str2 +"&verifycode=2091&backurl=";
		int []age;
		

	    MyThread2 t = new MyThread2(str);
	    MyThread2.sta=false;
	    t.start();
	    
	    while(true){
		    for(;;){
		    	if(MyThread2.sta){
				    j++;
					str2="0000"+j;
					str2=str2.substring(str2.length()-5);
					str="lsbh="+ str1 + str2 +"&verifycode=2091&backurl=";
					System.out.println(str);
			
				    t = new MyThread2(str);
				    t.start();
		    	}
		    	else break;
		    }
	    }
	    
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
            		"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:57.0) Gecko/20100101 Firefox/57.0");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }    

    
}

