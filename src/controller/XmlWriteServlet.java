package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import model.RandomSort;

/**
 * Servlet implementation class xmlwriteServlet
 */
@WebServlet("/XmlWriteServlet")
public class XmlWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RandomSort sort = new RandomSort();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XmlWriteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			sort.sort(Integer.parseInt(request.getParameter("size")));
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			Document document = documentBuilder.parse(getClass().getClassLoader().getResource("file.xml").getFile());
			Element root = document.getDocumentElement();
			
			Element newArray = document.createElement("array");

            Element start = document.createElement("start");
            start.appendChild(document.createTextNode(sort.getStartArray()));
            newArray.appendChild(start);
            
            Element time = document.createElement("time");
            time.appendChild(document.createTextNode(String.valueOf(sort.getTime())));
            newArray.appendChild(time);
            
            Element counter = document.createElement("counter");
            counter.appendChild(document.createTextNode(Integer.toString(sort.getCounter())));
            newArray.appendChild(counter);

            root.appendChild(newArray);
			
			DOMSource source = new DOMSource(document);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        StreamResult result = new StreamResult(getClass().getClassLoader().getResource("file.xml").getFile());
	        transformer.transform(source, result);
	        
	        response.sendRedirect("RandomSortServlet");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
