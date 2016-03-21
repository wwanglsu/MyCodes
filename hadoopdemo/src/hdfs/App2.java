package hdfs;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class App2 {
//Use FileSystem to handle file read/write
	public static final String HDFS_PATH = "hdfs://hadoop:9000/hello";
	public static final String DIR_PATH="/d1000";
	public static final String FILE_PATH="/d1000/f1000";
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		final FileSystem fileSystem = FileSystem.get(new URI(HDFS_PATH), new Configuration());
		makeDirectort(fileSystem);
		uploadData(fileSystem);
		
		//download file
		final FSDataInputStream in = fileSystem.open(new Path(FILE_PATH));
		IOUtils.copyBytes(in, System.out, 1024, true);
		
		deleteFile(fileSystem);
		
	}

	private static void deleteFile(final FileSystem fileSystem) throws IOException {
		fileSystem.delete(new Path(FILE_PATH), true);
	}

	private static void uploadData(final FileSystem fileSystem) throws IOException {
		//upload nonexisting file
		final FSDataOutputStream out = fileSystem.create(new Path(FILE_PATH));
		
		final FileInputStream	in = new FileInputStream("C:\\Users\\wwang\\Documents\\temp.txt");
		IOUtils.copyBytes(in, out, 1024, true);
		
	}

	private static void makeDirectort(final FileSystem fileSystem) throws IOException {
		//create a folder
		fileSystem.mkdirs(new Path(DIR_PATH));
	}

}
