class ServletRep{
	// Heavy
	int x;
	public void doGet(){
		x++;
		System.out.println("Servlet DoGet...");
	}
	public void doPost(){
		System.out.println("Servlet DoPost");
		x--;
	}
}
class ServletThread implements Runnable{
	ServletRep rep;
	ServletThread(ServletRep rep){
		this.rep = rep;
	}
	public void run(){
		rep.doGet();
		rep.doPost();
	}
}
public class MyServlet {
public static void main(String[] args) {
	ServletRep ob = new ServletRep();
	
	for(int i = 1; i<=100000;i++){
		new Thread(new ServletThread(ob)).start();
	}
	
}
}
