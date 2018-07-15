package com.nov.test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class StringTest {
	@Test
	public void fun1(){
		List<String> imageSrcList = new ArrayList<String>();
		String str = "<p>Hello, World!<img src=\"uploadImg/85fac656-7015-4635-8e31-34bc9de71df8_gakki2.jpg\" /> asdfsdf<img src=\"uploadImg/85fac656-7015-4635-8e31-34bc9de71df8_gakki2.jpg\" /> dfgsdg<img src=\"uploadImg/85fac656-7015-4635-8e31-34bc9de71df8_gakki2.jpg\" /></p>";
		Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\.jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\.jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic)\\b)[^>]*>", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(str);
		String quote = null;
        String src = null;
		while(m.find()){
			quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m.group(2);
            src = src.replaceAll("/", "\\\\");
            imageSrcList.add(src);
		}
		for (String string : imageSrcList) {
			System.out.println(string);
		}
	}
	
	@Test
	public void fun2(){
		String src = "C:\\Users\\Administrator\\Workspaces\\MyEclipse 2017 CI\\.metadata\\.me_tcat85\\webapps\\blog\\uploadImg\\f8_gakki3.jpg";
		File file = new File(src);
		file.delete();
	}
}
