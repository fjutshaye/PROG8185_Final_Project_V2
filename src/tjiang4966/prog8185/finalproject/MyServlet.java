package tjiang4966.prog8185.finalproject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//Database Connection
    protected Connection connection;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
		connection = DatabaseTools.connectToSqlServer();
		
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String chartNumStr = request.getParameter("chartNum");
		String filterNumStr = request.getParameter("filterNum");
		String yearStr = request.getParameter("year_c3");
		String requestId = request.getParameter("requestId");
		if(chartNumStr!=null && filterNumStr!=null){
			int chartNum =  Integer.parseInt(chartNumStr);
			int filterNum = Integer.parseInt(filterNumStr);
			switch (chartNum) {
			//chart 1
			case 1:
				String sql;
				switch (filterNum) {
				case 1:
					sql = DatabaseTools.SQL_CHART_1_FILTER_1;
					break;
				case 2:
					sql = DatabaseTools.SQL_CHART_1_FILTER_2;
					break;
				case 3:
					sql = DatabaseTools.SQL_CHART_1_FILTER_3;
					break;
				default:
					sql = null;
					break;
				}
				jsonMethod_1(sql, response);
				break;
			//chart 2
			case 2:
				switch (filterNum) {
				case 1:
					sql = DatabaseTools.SQL_CHART_2_FILTER_1;
					break;
				case 2:
					sql = DatabaseTools.SQL_CHART_2_FILTER_2;
					break;
				case 3:
					sql = DatabaseTools.SQL_CHART_2_FILTER_3;
					break;
				default:
					sql = null;
					break;
				}
				jsonMethod_1(sql, response);
				break;
			//chart 3
			case 3:
				if(yearStr != null){
					jsonMethod_2(yearStr, response);					
				}
				else{
					ServletException exception = new ServletException("year is empty");
					throw exception;
				}
				break;
			case 4:
				jsonMethod_3(filterNum, response);
				break;
			default:
				break;
			}
		}
		if(requestId != null){
			List<Integer> yearList = getYearList();
			Type listType = new TypeToken<List<Double>>() {}.getType();
			String json = new Gson().toJson(yearList, listType);
			response.setContentType("application/json");
			response.getWriter().write(json);
			response.getWriter().flush(); 
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void jsonMethod_1(String sql, HttpServletResponse response){
		try {
			ResultSet rs;
			rs = connection.createStatement().executeQuery(sql);
			JSONArray dataArray = new JSONArray();
			while(rs.next()){
				Object[] row = {rs.getString(2), rs.getInt(1)}; 
				dataArray.put(row);
			}
			//out.write(dataArray.toString());
			String json = new Gson().toJson(dataArray);
			response.setContentType("application/json");
			response.getWriter().write(json);
			response.getWriter().flush();
		} catch (SQLException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void jsonMethod_2(String year, HttpServletResponse response){
		ResultSet rs = null;
		PreparedStatement preparedStatement = null;
		String sqlString = "SELECT SUM(Total)"
				+ " FROM Invoice"
				+ " WHERE DATEPART(yyyy, InvoiceDate) = ?"
				+ " GROUP BY DATEPART(MM, InvoiceDate)"
				+ " ORDER BY DATEPART(MM, InvoiceDate) ASC";
		try {
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setString(1, year);
			rs = preparedStatement.executeQuery();
			
			List<Double> list = new ArrayList<Double>();
			while(rs.next()){
				list.add(rs.getDouble(1));
			}
			Type listType = new TypeToken<List<Double>>() {}.getType();
			String json = new Gson().toJson(list, listType);
			response.setContentType("application/json");
			response.getWriter().write(json);
			response.getWriter().flush();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void jsonMethod_3(int filterNum, HttpServletResponse response){
		String para = new String();
		switch (filterNum) {
		case 1:
			para = "SUM(Total)";
			break;
		case 2:
			para = "COUNT(*)";
			break;
		case 3:
			para = "AVG(Total)";
			break;
		default:
			System.out.println("No such a filter");
			break;
		}
		String sql = "SELECT " + para +", DATEPART(yyyy, InvoiceDate)"
				+ " FROM Invoice"
				+ " GROUP BY DATEPART(yyyy, InvoiceDate)"
				+ " ORDER BY DATEPART(yyyy, InvoiceDate) ASC";
		ResultSet rs = null;
		try {
			Statement statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			List<String> category = new ArrayList<String>();
			List<Object> jsonList = new ArrayList<Object>();
			String dataName = new String();
			if(filterNum == 3){
				dataName = "Average Amount per Purchase";
				List<Double> dataList = new ArrayList<Double>();
				while(rs.next()){
					dataList.add(rs.getDouble(1));
					category.add(rs.getString(2));
				}
				jsonList.add(category);
				jsonList.add(dataList);
				jsonList.add(dataName);
			}
			else{
				if(filterNum == 1)
					dataName = "Total Amount of Sales";
				else
					dataName = "Total Number of Sales";
				List<Integer> dataList = new ArrayList<Integer>();
				while(rs.next()){
					dataList.add(rs.getInt(1));
					category.add(rs.getString(2));
				}
				jsonList.add(category);
				jsonList.add(dataList);
				jsonList.add(dataName);
			}
			
			Type listType = new TypeToken<List<Object>>() {}.getType();
			String json = new Gson().toJson(jsonList, listType);
			response.setContentType("application/json");
			response.getWriter().write(json);
			response.getWriter().flush();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private List<Integer> getYearList(){
		ResultSet rSet = null;
		List<Integer> list = new ArrayList<Integer>();
		Statement statement;
		try {
			statement = connection.createStatement();
			String sql = "SELECT DISTINCT DATEPART(yyyy, InvoiceDate) FROM Invoice";
			rSet = statement.executeQuery(sql);
			while(rSet.next()){
				list.add(rSet.getInt(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
