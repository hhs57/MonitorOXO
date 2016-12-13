package com.ale.ttt;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class ReadJson {
	 private static String ConvertStream2Json(InputStream inputStream)
	    {
	        String jsonStr = "";
	        // ByteArrayOutputStream�൱���ڴ������
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        byte[] buffer = new byte[1024];
	        int len = 0;
	        // ��������ת�Ƶ��ڴ��������
	        try
	        {
	            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1)
	            {
	                out.write(buffer, 0, len);
	            }
	            // ���ڴ���ת��Ϊ�ַ���
	            jsonStr = new String(out.toByteArray());
	        }
	        catch (IOException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return jsonStr;
	    }

	
	public static void main(String[] args) throws FileNotFoundException {
		
		InputStream is = ReadJson.class.getClassLoader().getResourceAsStream(
				"get_data.json");
		System.out.println(ConvertStream2Json(is));		
	}

}
