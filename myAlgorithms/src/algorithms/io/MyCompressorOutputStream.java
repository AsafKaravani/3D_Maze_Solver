package algorithms.io;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import algorithms.mazeGenerators.Maze3D;

public class MyCompressorOutputStream extends OutputStream{

	OutputStream out;

	public MyCompressorOutputStream(OutputStream out){
		this.out=out;
	}
	
	@Override
	public void write(int arg0) throws IOException {
		out.write(arg0);
	}
	
	public void compress(Maze3D maze){
		int count = 0;
		int lastBit = -1;
		
		for (int indexLayer = 0; indexLayer <maze.getMaze().length; indexLayer++) {
			for (int indexRow = 0; indexRow < maze.getMaze()[0].length; indexRow++) {
				for (int indexColumn = 0; indexColumn < maze.getMaze()[0][0].length; indexColumn++) {
				
					if (lastBit == maze.getMaze()[indexLayer][indexRow][indexColumn] || lastBit == -1) {
						count++;
						lastBit = maze.getMaze()[indexLayer][indexRow][indexColumn];
					}
					else{
						try {
							write(lastBit);
						} catch (IOException e) {
							e.printStackTrace();
						}
						lastBit = maze.getMaze()[indexLayer][indexRow][indexColumn];
						try {
							write(count);
						} catch (IOException e) {
							e.printStackTrace();
						}
					
						count = 0;	
					}
					
				}
			}
		}
	}




}
