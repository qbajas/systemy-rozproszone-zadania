package FileSystem;

public class Client {

	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try {
			ic = Ice.Util.initialize(args);
			Ice.ObjectPrx base = ic.stringToProxy("Directory:default -p 10000");
			FileSystem.DirectoryPrx dirPrx = FileSystem.DirectoryPrxHelper
					.checkedCast(base);			
			FileSystem.DirectoryV2Prx dirPrxV2 = DirectoryV2PrxHelper.checkedCast(dirPrx, "DirectoryV2");
			
			if (dirPrx == null)
				throw new Error("Invalid proxy");
			
			String[] entries = dirPrx.listContent("/");
			System.out.println("-------- FILES AND DIRS : ------------");
			for (String entry : entries)
				System.out.println(entry);
			
			entries = dirPrxV2.listFiles("/");
			System.out.println("-------- FILES ONLY : ------------");
			for (String entry : entries)
				System.out.println(entry);
			
			
		} catch (Ice.LocalException e) {
			e.printStackTrace();
			status = 1;
		} catch (Exception e) {
			System.err.println(e.getMessage());
			status = 1;
		}
		if (ic != null) {
			try {
				ic.destroy();
			} catch (Exception e) {
				System.err.println(e.getMessage());
				status = 1;
			}
		}
		System.exit(status);
	}

}
