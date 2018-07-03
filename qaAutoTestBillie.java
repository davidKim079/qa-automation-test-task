package org.BillieTesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.testng.annotations.*;

public class qaAutoTestBillie {
	int lineCnt = 0;
	BufferedReader br;
	String compareName = "name: pariatur aspernatur nam atque quis";
	String compareEmail = "email: Cooper_Boehm@damian.biz";
	String compareBody = "body: veniam eos ab voluptatem in fugiat ipsam quis\\nofficiis non qui\\nquia ut id voluptates et a molestiae commodi quam\\ndolorem enim soluta impedit autem nulla";
	
	// Initiate.
	@BeforeTest
	public void beforeTest() throws IOException {
		URL url = new URL("https://jsonplaceholder.typicode.com/comments");
		URLConnection urlConn = url.openConnection();
		br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

		System.out.println("READY TO TEST");
	}
	
	// Find where is ID 40.
	@Test(priority=1)
	public void test () {
		try {			
			String str;
			while ((str = br.readLine())!=null) {
				lineCnt++;
				
				if (str.contains("id") & str.contains("40"))
				{
					break;
					}
			}
			br.close();
	
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		} 
	} 
	
	// Check ID 40 has 5 comments and compare.
	@Test(priority=2)
	public void checkComment() {
		
		int i=0;
		ArrayList<String> idFourty = new ArrayList<String>();
		
		try {
			// Initiate again for test.
			URL url = new URL("https://jsonplaceholder.typicode.com/comments");
			URLConnection urlConn = url.openConnection();
			br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
			
			// Check ID 40 has 5 comments.
			String str;
			while ((str = br.readLine())!=null) {
				i++;
				if (lineCnt-2 < i && i < lineCnt+4) {
					idFourty.add(str);
				}
				else if (i==lineCnt+4) {
					break;
				}
			} 
			if (idFourty.size()==5) {
				System.out.println();
				System.out.println("id 40 has 5 comments");
				
				for (int j=0; j<idFourty.size(); j++) {
					System.out.println(idFourty.get(j));
				}
			}
			else {
				System.out.println();
				System.out.println("id 40 does not have 5 comments");
				for (int j=0; j<idFourty.size(); j++) {
					System.out.println(idFourty.get(j));
					}
			} System.out.println();
			
			// Compare comments "name", "email" and "body".
			int countResults = 0;
			
			if (idFourty.get(2)!=compareName) {
				System.out.println("ID 40 does not contain "+compareName);
				countResults++;
			} 
			if (idFourty.get(3)!=compareEmail) {
				System.out.println("ID 40 does not contain "+compareEmail);
				countResults++;
			} 
			if (idFourty.get(4)!=compareBody) {
				System.out.println("ID 40 does not contain "+compareBody);
				countResults++;
			} 
			if (countResults==0) {
				System.out.println("*=========================================================*");
				System.out.println("          All information are contained with ID 40.        ");
				System.out.println("*=========================================================*");
			} else {
				System.out.println("*=========================================================*");
				System.out.println("    ID 40 does not contain "+countResults+" information.   ");
				System.out.println("*=========================================================*");
			}
			
			br.close();
			
			
		} catch (MalformedURLException e) {
		} catch (IOException e) {
		} 

	}

}