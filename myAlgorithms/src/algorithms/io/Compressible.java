package algorithms.io;

public interface Compressible {
	public byte[] compress();
	public byte[] deCompress(byte[] compressed);
}
