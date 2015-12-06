import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class ReadFileFromHDFS {
	public static void main(String[] args) throws IOException {
		String inputFile = args[0];
		String outFile = args[1];

		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(inputFile), conf);
		FSDataInputStream in = null;
		OutputStream out = null;
		try {
			in = fs.open(new Path(inputFile));
			in.seek(100);// make sure the inputFile bigger than 120 Byte
			File output = new File(outFile);
			if (!output.exists()) {
				output.createNewFile();
			}
			out = new FileOutputStream(output);
			IOUtils.copyBytes(in, out, 20, 1024, false);
		} finally {
			IOUtils.closeStream(in);
			IOUtils.closeStream(out);
		}
	}

}
