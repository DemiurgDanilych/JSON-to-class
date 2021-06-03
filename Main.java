import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		String json = readString("src\\main\\java\\new_data.json");
		List<Employee> list = jsonToList(json);
		System.out.println(list);
	}
	
	
	private static String readString(String file) {
		StringBuilder jsonToString = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String str;
			while (((str = br.readLine()) != null)) {
				jsonToString.append(str);
			}
		} catch (IOException exception) {
			System.out.println(exception.getMessage());
		}
		return jsonToString.toString();
	}
	
	private static ArrayList<Employee> jsonToList(String json) {
		ArrayList<Employee> returnList = new ArrayList<>();
		JsonParser parser = new JsonParser();
		JsonArray employee = (JsonArray) parser.parse(json);
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		for (Object emp: employee){
			returnList.add(gson.fromJson(emp.toString(), Employee.class));
		}
		return returnList;
	}
}