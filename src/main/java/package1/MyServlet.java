package package1;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/serv1")
public class MyServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String city = req.getParameter("city");
		System.out.println(city);
		
		String key = "64f831a08ae9bf2795467495abea233e";
		
		String apiURL = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid="+key;
		URL url = new URL(apiURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		
		InputStream is = connection.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(isr);
		
		while (sc.hasNext()) {
			sb.append(sc.nextLine());
		}
		sc.close();
		
		System.out.println(sb);
		
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(sb.toString(), JsonObject.class);
		
		
		
	}
}
