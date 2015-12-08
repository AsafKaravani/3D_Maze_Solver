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

	@Override
	public void write(int arg0) throws IOException {
		out.write(arg0);
	}

	public void writeObject(T compressible) throws IOException {
		byte[] arrayOfBytes;

		compressible.compress();
		arrayOfBytes = compressible.compress();
		for (byte b : arrayOfBytes) {
			write(b);
		}
	}

}
