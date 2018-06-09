package controller;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ConnectTimeoutException;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


import util.HttpClientUtils;

public class test {
	
	public static void main(String[] args) throws  Exception {
		
	
		
	}
	
	public static String ceshi() throws ConnectTimeoutException, SocketTimeoutException, Exception {
		
		String url="https://moa.58lz.com/api/wxlz/customer?client_secret=c74b71cfe5bd3c2083f25958b93034";
	
		HashMap map = new HashMap(); 
		Map returnMap = new HashMap();
	
		
		map.put("usex", "1");
		map.put("nickname", "李海成第一次");
		map.put("wxid", "lihaicheng");
		map.put("umobile", "18249172212");
		String mmm=HttpClientUtils.postForm(url, map, returnMap, 10000, 10000);
		System.out.println(mmm);
		return mmm;
	
	
	}
	@Test
	public static void fapiao() {
		String url="https://moa.58lz.com/api/wxlz/customer?wxid=aaaaaaabbbbbb&mthod=invoices&client_secret=c74b71cfe5bd3c2083f25958b93034";
		
	}
	@Test
	public void test() {
		System.out.println(123);
	}

}
