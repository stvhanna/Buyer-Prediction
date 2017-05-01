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


public class maximumBrought {

  public static class DayMapper
       extends Mapper<Object, Text, Text, Text >{

    private final static IntWritable one = new IntWritable();
    private Text word = new Text();
    // private IntWritable val = new IntWritable();
    private Text val = new Text();
    final String DELIMITER = ",";

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {

      StringBuilder sb = new StringBuilder();
      String[] tokens = value.toString().split(DELIMITER);
      // IntWritable val = new IntWritable();
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
      try {

          
            // Date date = format.parse(tokens[1]);
            // String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
            // String month_of_the_year = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
            // word.set(dayOfWeek);
            // IntWritable val = new IntWritable(Integer.parseInt(tokens[4]));          
            // one.set(1);
            word.set(tokens[0]);
            // System.out.println(tokens[0]);
            // val.set(Integer.parseInt(dayOfWeek));
            // System.out.println("Mapping: "+word.toString()+", "+val.toString());
            // context.write(word, val);

            // Date date = format.parse(tokens[1]);
            // Calendar sessionTime = Calendar.getInstance();
            // sessionTime.setTime(date);
            // int month = date.get(Calendar.DAY_OF_THE_MONTH);
            // int month = date.get(Calendar.DAY_OF_THE_MONTH);
            // String temp = Integer.toString();
            // System.out.println(temp);
            // val.set(Integer.parseInt(tokens[4]));
            // val.set((sessionTime.get(Calendar.DAY_OF_THE_MONTH)));
            // val.set((sessionTime.get(Calendar.MONTH)));
            // String temp = tokens[0];
            // System.out.println(temp + "   " + tokens[1]);
            // val.set(Integer.parseInt(tokens[4]));
            // val
            // word.set(tokens[2]);
            val.set(tokens[1]);
            // System.out.println(word.toString() + "     " + val.toString() ); 
            context.write(word , val);

      }
      catch (Exception e) {
      }
    }
  }

  public static class DayReducer
       extends Reducer<Text, Text  , Text, Text > {
    // private IntWritable result = new IntWritable();
        private Text result = new Text();

    public void reduce(Text key, Iterable<Text > values,
                       Context context
                       ) throws IOException, InterruptedException 
      {
      //sum all those frequencies of a particular key
      // compositeWritable ts = new compositeWritable();
    // System.out.println("I am in the reducer funtion");
    
      // System.out.println("Reducing: "+key.toString());
      // int sum = 0;
      String temp = "";
      for (Text val : values) {
        // System.out.println(val.toString());        
        // sum += val.get();
        // ts.ll.add(val.get());
        temp = temp + val.toString() + ",";
        // context.write(key , val);
      } 
      // result.set(sum);
      // System.out.println("result: "+result);
      result.set(temp);
      context.write(key, result);
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "day_click_count");
    job.setJarByClass(maximumBrought.class);
    job.setMapperClass(DayMapper.class);
    job.setCombinerClass(DayReducer.class);
    job.setReducerClass(DayReducer.class);
    job.setOutputKeyClass(Text.class);
    // job.setOutputValueClass(Text.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Text.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);

    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    System.exit(job.waitForCompletion(true) ? 0 : 1);
    
  }
}












