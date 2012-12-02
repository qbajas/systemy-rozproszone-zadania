package FileSystem;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import Ice.Current;

public class DirectoryV2I extends _DirectoryV2Disp {

	@Override
	public String[] listFiles(String dirPath, Current __current)
			throws DirectoryNotExistsException {
		File dir = new File(dirPath);
//		String[] entries = dir.list();
		File[] entries2 = dir.listFiles();
		
		
		List<String> files = new LinkedList<String>();


		for(File file : entries2){
			if (file.isFile())
				files.add(file.getName());
		}
		
		// checking existence
		if (entries2 == null)
			throw new DirectoryNotExistsException();
		else
			return files.toArray(new String[files.size()]);
	}

}
