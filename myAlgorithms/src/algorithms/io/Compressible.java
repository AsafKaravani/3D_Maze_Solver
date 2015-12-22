package algorithms.io;

public interface Compressible {
	/**
	*@author Yaniv and asaf
	*@return this takes the maze and compress it into along line of bytes like 1,13
	 */
		public byte[] compress();
		/**
		*@author Yaniv and asaf
		*@return takes the byte array and decompress it into a huge line of 1 and 0
		 */
		public byte[] deCompress(byte[] compressed);
		
}
