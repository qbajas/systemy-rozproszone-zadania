package server;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;


public class NoteBoardServer {
	static INoteBoard nbi;

	public static void main(String[] args) {
		try {
			nbi = new NoteBoardImpl();
			UnicastRemoteObject.exportObject(nbi, 0);
			System.out.println("Export OK");
			Naming.rebind("rmi://127.0.0.1:1099/note", nbi);
			System.out.println("Rebind OK");
			System.out.println("Server active");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
