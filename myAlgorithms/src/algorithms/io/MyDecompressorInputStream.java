package algorithms.io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream<T extends Compressible> extends InputStream{
	InputStream in;
	
	
	public MyDecompressorInputStream(InputStream in){
		this.in=in;
	}
	@Override
	public int read() throws IOException {
		in.read();
		return 0;
	}
	public void readObject(T Compressible) throws IOException{
		byte[] arrayOfBytes;
		Compressible.deCompress(Compressible.compress());
		arrayOfBytes=Compressible.deCompress(Compressible.compress());
		for (byte b : arrayOfBytes) {
			read();
		}
		
	}
}
