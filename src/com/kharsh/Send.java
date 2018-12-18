package com.kharsh;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Send {
    public static String doPost(String url, String postData){
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            conn.setRequestProperty("accept","charset utf-8");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF8"));
     //       out = new PrintWriter(conn.getOutputStream());
            out.print(postData);
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF8"));

            String line;
            while((line = in.readLine()) != null){
                result += line;
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
            result = "noconn";
        }
        finally{
            if(out != null)
                out.close();
            if(in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return(result);
    }
}
