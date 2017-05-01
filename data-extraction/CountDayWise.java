import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class CountDayWise {

  public static class DayMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable();
    private Text word = new Text();
    final String DELIMITER = ",";

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {

      StringBuilder sb = new StringBuilder();
      String[] tokens = value.toString().split(DELIMITER);

      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
      try {
          Date date = format.parse(tokens[1]);
          String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
          word.set(dayOfWeek);
          IntWritable val = new IntWritable(Integer.parseInt(tokens[4]));          
          // one.set(1);
          System.out.println("Mapping: "+word.toString()+", "+val.toString());
          context.write(word, val);
      }
      catch (Exception e) {

      }
    }
  }

  public static class DayReducer
       extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable> values,
                       Context context
                       ) throws IOException, InterruptedException {
      //sum all those frequencies of a particular key
      System.out.println("Reducing: "+key.toString());
      int sum = 0;
      for (IntWritable val : values) {
        System.out.println(val.toString());
        sum += val.get();
      } 
      result.set(sum);
      System.out.println("Sum: "+result.toString());
      context.write(key, result);
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "day_click_count");
    job.setJarByClass(CountDayWise.class);
    job.setMapperClass(DayMapper.class);
    job.setCombinerClass(DayReducer.class);
    job.setReducerClass(DayReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
