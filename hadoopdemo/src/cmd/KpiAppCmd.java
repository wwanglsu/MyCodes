package cmd;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.hadoop.fs.FileSystem;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

public class KpiAppCmd extends Configured implements Tool{
	
	static String INPUT_PATH = ""; //"hdfs://hadoop0:9000/pphone";
	static String OUT_PATH = ""; // "hdfs://hadoop0:9000/outqhoto";

	@Override
	public int run(String[] arg0) throws Exception {
		INPUT_PATH = arg0[0];
		OUT_PATH = arg0[1];
		
		Configuration conf = new Configuration();
		final FileSystem fileSystem = FileSystem.get(new URI(INPUT_PATH), conf);
		final Path outPath = new Path(OUT_PATH);
		if(fileSystem.exists(outPath)){
			fileSystem.delete(outPath, true);
		}
		
		final Job job = new Job(new Configuration(), KpiWritable.class.getSimpleName());
		//1.1 指定输入文件路径
		FileInputFormat.setInputPaths(job, INPUT_PATH);
		//指定哪个类 用来格式化输入文件
		job.setInputFormatClass(TextInputFormat.class);
		
		//1.2 指定自定义的Mapper类
		job.setMapperClass(MyMapper.class);
		//指定输出<K2, V2>
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(KpiWritable.class);
		
		//1.3 指定分区类
		job.setPartitionerClass(HashPartitioner.class);
		job.setNumReduceTasks(1);
		
		//1.4 TODO  排序 分区
		
		//1.5 TODO 合并 可选
		
		//2.2 指定自定义的reduce类
		job.setReducerClass(MyReducer.class);
		//指定输出<k3,v3>的类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(KpiWritable.class);
		
		//2.3 指定输出到哪里
		FileOutputFormat.setOutputPath(job, new Path(OUT_PATH));
		//设定输出文件的格式化类
		job.setOutputFormatClass(TextOutputFormat.class);
		
		//把代码提交给JobTracker执行
		job.waitForCompletion(true);
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new KpiAppCmd(), args);
	}
	
	static class MyMapper extends Mapper<LongWritable, Text, Text, KpiWritable>{
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, KpiWritable>.Context context) throws IOException, InterruptedException {
			final String[] splited = value.toString().split("\t");
			final String msisdn = splited[1];
			final Text k2 = new Text(msisdn);
			final KpiWritable v2 = new KpiWritable(splited[21],splited[22],splited[23],splited[24]);
			context.write(k2, v2);
		}
	}

	static class MyReducer extends Reducer<Text, KpiWritable, Text, KpiWritable>{
		/**
		 * @param	k2	表示整个文件中不同的手机号码	
		 * @param	v2s	表示该手机号在不同时段的流量的集合
		 */
		protected void reduce(Text k2, java.lang.Iterable<KpiWritable> v2s, org.apache.hadoop.mapreduce.Reducer<Text, KpiWritable, Text, KpiWritable>.Context context) throws IOException, InterruptedException {
			long upPackNum = 0L;
			long downPackNum = 0L;
			long upPayLoad = 0L;
			long downPayLoad = 0L;
			
			for(KpiWritable kpiWritable : v2s){
				upPackNum += kpiWritable.upPackNum;
				downPackNum +=kpiWritable.downPackNum;
				upPayLoad +=kpiWritable.upPayLoad;
				downPayLoad +=kpiWritable.downPayLoad;
			}
			
			final KpiWritable v3 = new KpiWritable(upPackNum+"", downPackNum+"", upPayLoad+"", downPayLoad+"");
			context.write(k2, v3);
			
		}
	}
}

class KpiWritable implements Writable {
	long upPackNum;
	long downPackNum;
	long upPayLoad;
	long downPayLoad;
	
	
	public KpiWritable(){}
		
	public KpiWritable(String upPackNum, String downPackNum, String upPayLoad, String downPayLoad){
			this.upPackNum = Long.parseLong(upPackNum);
			this.downPackNum = Long.parseLong(downPackNum);
			this.upPayLoad = Long.parseLong(upPayLoad);
			this.downPayLoad = Long.parseLong(downPayLoad);
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		this.upPackNum = in.readLong();
		this.downPackNum = in.readLong();
		this.upPayLoad = in.readLong();
		this.downPayLoad = in.readLong();
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		out.writeLong(upPackNum);
		out.writeLong(downPackNum);
		out.writeLong(upPayLoad);
		out.writeLong(downPayLoad);
		
	}
	
	@Override
	public String toString() {
		return upPackNum + "\t" + downPackNum + "\t" + upPayLoad + "\t" + downPayLoad;
	}
}