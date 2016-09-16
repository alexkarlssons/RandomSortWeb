package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import model.RandomSort;

/**
 * Servlet implementation class RandomSortServlet
 */
@WebServlet("/RandomSortServlet")
public class RandomSortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RandomSort sort;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RandomSortServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<RandomSort> sortList = new ArrayList<RandomSort>();
		try {
			File fXmlFile = new File(getClass().getClassLoader().getResource("file.xml").getFile());
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("array");
			
			for (int temp = 0; temp < nList.getLength(); temp++) {
				sort = new RandomSort();
				Node nNode = nList.item(temp);
				Element eElement = (Element) nNode;
				
				sort.setTime(Long.parseLong(eElement.getElementsByTagName("time").item(0).getTextContent()));
				sort.setCounter(Integer.parseInt(eElement.getElementsByTagName("counter").item(0).getTextContent()));
				sort.setStartArray(eElement.getElementsByTagName("start").item(0).getTextContent());
				sortList.add(sort);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("sortlist", sortList);
		RequestDispatcher rd = request.getRequestDispatcher("sort.jsp");
		rd.forward(request, response);
	}

}
