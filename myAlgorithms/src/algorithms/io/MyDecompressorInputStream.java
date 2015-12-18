package algorithms.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

public class MyDecompressorInputStream<T extends Compressible> extends InputStream{
	InputStream in;
	 byte[] buffer = new byte[1];
	
	public MyDecompressorInputStream(InputStream in){
		this.in=in;
	}
	/**
	*@author Yaniv and asaf
	*@return Read from the stream.
	 */
	@Override
	public int read() throws IOException {		
		return in.read(buffer);
		
	}
	
	/**
	*@author Yaniv and asaf
	*@return Using the decompress method of a compressible object and gets it from the stream.
	 */
	public byte[] readObject(T obj) throws IOException{
		ArrayList<Byte> bytesFromStream = new ArrayList<Byte>(); 
		read();
		bytesFromStream.add(buffer[0]);
		read();
		bytesFromStream.add(buffer[0]);
		read();
		bytesFromStream.add(buffer[0]);
		
		while(read() != -1)
			bytesFromStream.add(buffer[0]);
		
		byte[] toBeDecompressed = new byte[bytesFromStream.size()];
		int i = 0;
		for (Byte b : bytesFromStream) {
			
			toBeDecompressed[i] = b.byteValue();
			i++;
		}
		
		
		return obj.deCompress(toBeDecompressed);
		
		 
		
		
	}
}
