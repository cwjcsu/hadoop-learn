import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class WriteToHDFS {

	public static void main(String[] args) throws IOException {
		String inputFile = args[0];
		String outputFile = args[1];
		InputStream in = null;
		try {
			in = new FileInputStream(inputFile);
			Configuration conf = new Configuration();
			FileSystem fs = FileSystem.get(URI.create(outputFile), conf);
			OutputStream out = fs.create(new Path(outputFile));
			in.read(new byte[100], 0, 100);//读取前面100byte，把读指针移动到101位置上
			IOUtils.copyBytes(in, out, 20, 1024, true);
		} finally {
			IOUtils.closeStream(in);
		}
	}

}
