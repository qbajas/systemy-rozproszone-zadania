package FileSystem;

import java.io.File;

import Ice.Current;

public class DirectoryI extends _DirectoryDisp {

	@Override
	public String[] listContent(String dirPath, Current __current)
			throws DirectoryNotExistsException {
		File dir = new File(dirPath);
		String[] entries = dir.list();

		// checking existence
		if (entries == null)
			throw new DirectoryNotExistsException();
		else
			return entries;
	}

}
