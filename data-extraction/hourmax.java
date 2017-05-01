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
import java.util.*;
import java.util.Locale;
import java.util.Calendar;

class compositeWritable{
    LinkedList<Integer > ll = new LinkedList<Integer >();
}


public class hourmax {

  public static class DayMapper
       extends Mapper<Object, Text, Text, IntWritable >{

    private final static IntWritable one = new IntWritable();
    private Text word = new Text();
    private Text val = new Text();
    final String DELIMITER = ",";

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {

      StringBuilder sb = new StringBuilder();
      String[] tokens = value.toString().split(DELIMITER);
      IntWritable val = new IntWritable();
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
      try {
          /*
            Date date = format.parse(tokens[1]);
            String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
            word.set(dayOfWeek);
            IntWritable val = new IntWritable(Integer.parseInt(tokens[4]));          
            // one.set(1);
            System.out.println("Mapping: "+word.toString()+", "+val.toString());
            context.write(word, val);
          */
            Date date = format.parse(tokens[1]);
            Calendar sessionTime = Calendar.getInstance();
            sessionTime.setTime(date);
            String temp = Integer.toString(sessionTime.get(Calendar.DAY_OF_WEEK));
            // System.out.println(hour);
            // val.set(Integer.parseInt(tokens[4]));
            // String temp = tokens[4];
            // System.out.println(temp + "   " + tokens[1]);
            // val.set(Integer.parseInt(tokens[4]));            
            // int b = int(tokens[4])
            word.set(temp);         
            val.set(Integer.parseInt(tokens[4])) ;
            context.write(word , val);
      }
      catch (Exception e) {
      }
    }
  }

  public static class DayReducer
       extends Reducer<Text, IntWritable , Text, IntWritable > {
    // private IntWritable result = new IntWritable();
        private IntWritable result = new IntWritable();

    public void reduce(Text key, Iterable<IntWritable > values,
                       Context context
                       ) throws IOException, InterruptedException 
      {
      //sum all those frequencies of a particular key
      // compositeWritable ts = new compositeWritable();
    // System.out.println("I am in the reducer funtion");
    
      // System.out.println("Reducing: "+key.toString());
      int sum = 0;
      // String temp = "";
      for (IntWritable val : values) {
        // System.out.println(val.toString());        
          sum += val.get();
        // ts.ll.add(val.get());
        // temp = temp + val.toString() + ",";
      } 
      // result.set(sum);
      // System.out.println("result: "+result);
      result.set(sum);
      context.write(key, result);
    }
  }




  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "day_click_count");
    job.setJarByClass(hourmax.class);
    job.setMapperClass(DayMapper.class);
    job.setCombinerClass(DayReducer.class);
    job.setReducerClass(DayReducer.class);
    job.setOutputKeyClass(Text.class);
    // job.setOutputValueClass(Text.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(IntWritable.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}





















