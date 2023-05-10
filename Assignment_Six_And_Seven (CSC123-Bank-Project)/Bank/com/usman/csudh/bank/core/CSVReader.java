package com.usman.csudh.bank.core;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {
	private static String MSG_FILE_NOT_FOUND_OR_LOADED = 
	"** Currency file could not be loaded, Currency Conversion Service and Foreign Currency accounts are not available **";
	// for CVSReader
	public String[] currencyValues;
	// for HTTPreader
	public String[] currencyValues2;
	public String[] currencyValues3;
	// Map that stores file content, changes depending on which reader method is called.
	public Map<String, String> MAPcurrencyValues=new HashMap<String,String>();
	
	//runs CSVReader.
	public void runCSVReader() {
		String path = "exchange-rate.csv";
		String line="";
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			while((line= br.readLine())!=null){
				this.currencyValues = line.split(",");
				MAPcurrencyValues.put(currencyValues[0], currencyValues[2]);
			}
			br.close();
		} catch (IOException e) {
			System.out.println(MSG_FILE_NOT_FOUND_OR_LOADED);
		}
	}
	
	//runs HTTPreader.
	public void runHTTPreader() {
		HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
		HttpResponse<String> response = null;
		try {
			String endPoint = "http://www.usman.cloud/banking/exchange-rate.csv";
			URI uri = URI.create(endPoint+"?foo=bar&foo2=bar2");
			HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
			response = client.send(request, HttpResponse.BodyHandlers.ofString());
			String line = response.body().toString();
			currencyValues3 = line.split("\n");
			for(int c=0;c<currencyValues3.length;c++) {
				currencyValues2 = currencyValues3[c].split(",");
				MAPcurrencyValues.put(currencyValues2[0], currencyValues2[2]);
			}
			MAPcurrencyValues.put("XAG", "22.2833");
		}catch(Exception e) {
			System.out.println(MSG_FILE_NOT_FOUND_OR_LOADED+"\n");
		}
	}
	
}
