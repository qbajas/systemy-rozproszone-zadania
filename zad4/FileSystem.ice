#include <BuiltinSequences.ice>

module FileSystem
{
	exception DirectoryNotExistsException {};

	interface Directory
	{
		idempotent Ice::StringSeq listContent(string dirPath) throws DirectoryNotExistsException;
	};
	
	interface DirectoryV2
	{
		idempotent Ice::StringSeq listFiles(string dirPath) throws DirectoryNotExistsException;
	};
	
};
