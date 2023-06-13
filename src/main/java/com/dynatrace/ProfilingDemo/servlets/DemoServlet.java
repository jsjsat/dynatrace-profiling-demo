package com.dynatrace.ProfilingDemo.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.function.Supplier;

import com.dynatrace.ProfilingDemo.demo.Demo;
import com.dynatrace.ProfilingDemo.model.User;
import com.dynatrace.ProfilingDemo.worker.Worker;
import com.dynatrace.ProfilingDemo.worker.WorkerDemoA;
import com.dynatrace.ProfilingDemo.worker.WorkerDemoB;
import com.dynatrace.ProfilingDemo.worker.WorkerDemoC;
import com.dynatrace.ProfilingDemo.worker.WorkerDemoD;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/demo")
public class DemoServlet extends HttpServlet {


	private static String DEMO_A_DESC = "Normal-CPU";
	private static String DEMO_B_DESC = "CPU-Problem";
	private static String DEMO_C_DESC = "Deadlock";
	private static String DEMO_D_DESC = "String-Concatenation-vs-StringBuilder";

	private static Demo<Worker> demo;
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if(demo != null) {
			demo.stopWorkers();
			demo = null;
		}
		PrintWriter out = response.getWriter();
		out.append("<html>");
		renderHead(out);
		renderBody(out, request);
		out.append("</html");
	}

	private void renderBody(PrintWriter out, HttpServletRequest request) {
		out.append("<body class=\"p-5\">");
		out.write("<h1>Dynatrace Profiling Demo</h1>");
		renderButtons(out);
		out.write("<hr/>");
		renderLoadingSpinner(out);
		renderResults(out, request);	
		out.append("</body>");
	}

	private List<User> runDemo(char demoId) {
		if(demo != null) {
			demo.stopWorkers();
		}
		demo = new Demo<Worker>();
		Supplier<Worker> workers;
		switch (demoId) {
		case 'A':
			workers = () -> new WorkerDemoA();
			break;
		case 'B':
			workers = () -> new WorkerDemoB();
			break;
		case 'C':
			workers = () -> new WorkerDemoC();
			break;
		case 'D':
			workers = () -> new WorkerDemoD();
			break;
		default:
			return List.of();
		}

		return demo.runJobs(workers);
	}
	
	private void renderLoadingSpinner(PrintWriter out) {
		out.write("<div id=\"loadingspinner\" class=\"spinner-border\" hidden></div>");
	}

	private void renderResults(PrintWriter out, HttpServletRequest request) {
		out.write("<div id=\"results\">");
		
		Character demoId = parseDemoId(request);
		if (demoId != null) {
			List<User> results = runDemo(demoId);
			out.write("<h2>Results</h2>");
			out.write("<ul>");
			for (User user : results) {
				out.write("<li>" + user.getName() + ": " + user.getPoints() + "</li>");
			}
			out.write("</ul>");
		}
		out.write("</div>");
	}

	private Character parseDemoId(HttpServletRequest request) {
		String demoAttribute = request.getParameter("demo");
		if (demoAttribute != null && !demoAttribute.isEmpty()) {
			return demoAttribute.charAt(0);
		}
		return null;
	}

	private void renderButtons(PrintWriter out){
		String onclick =
				"document.getElementById('loadingspinner').removeAttribute('hidden');" +
				"document.getElementById('stopbutton').removeAttribute('hidden');" +
				"document.getElementById('results')?.setAttribute('hidden', '');" +
				"document.getElementById('demobuttons').setAttribute('hidden', '');";
		out.write("<div id='demobuttons'>");
		out.write("<button type=\"button\" class=\"btn btn-primary \" onclick=\"" + onclick + "location.href='?demo=A'\" title="+ DEMO_A_DESC + ">Demo A</button>");
		out.write("<button type=\"button\" class=\"btn btn-primary  ms-1\" onclick=\"" + onclick + "location.href='?demo=B'\" title="+ DEMO_B_DESC + ">Demo B</button>");
		out.write("<button type=\"button\" class=\"btn btn-primary  ms-1\" onclick=\"" + onclick + "location.href='?demo=C'\" title="+ DEMO_C_DESC + ">Demo C</button>");
		out.write("<button type=\"button\" class=\"btn btn-primary  ms-1\" onclick=\"" + onclick + "location.href='?demo=D'\" title="+ DEMO_D_DESC + ">Demo D</button>");
		out.write("</div>");
		out.write("<button id='stopbutton' type=\"button\" class=\"btn btn-primary  \" onclick=\"location.href='?'\" hidden>Stop</button>");
	}

	private void renderHead(PrintWriter out) {
		    out.println("<head>");
		    out.println("<title>Dynatrace Profiling Demo</title>");
		    out.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\">");
		    out.println("</head>");
	}
}