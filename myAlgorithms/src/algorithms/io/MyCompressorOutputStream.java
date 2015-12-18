package algorithms.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3D;

public class MyCompressorOutputStream<T extends Compressible> extends OutputStream {

	OutputStream out;

	public MyCompressorOutputStream(OutputStream out) {
		this.out = out;
	}
	/**
	*@author Yaniv and asaf
	*@return Write to the stream.
	 */
	@Override
	public void write(int arg0) throws IOException {
		out.write(arg0);
	}
	/**
	*@author Yaniv and asaf
	*@return Using the compress method of a compressible object and sends it to the stream.
	 */
	public void writeObject(T compressible) throws IOException {
		byte[] arrayOfBytes;

		compressible.compress();
		arrayOfBytes = compressible.compress();
		for (byte b : arrayOfBytes) {
			write(b);
		}
	}

}
