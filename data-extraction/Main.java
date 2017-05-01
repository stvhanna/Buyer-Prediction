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
public class Main {

  public static class ClicksMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
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
          context.write(word, one);
      }
      catch (Exception e) {

      }
    }
  }

  public static class BuysMapper
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
          one.set(Integer.parseInt(tokens[4]));
          context.write(word, one);
      }
      catch (Exception e) {

      }
    }
  }

  public static class CategoryClicksMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    final String DELIMITER = ",";

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {

      StringBuilder sb = new StringBuilder();
      String[] tokens = value.toString().split(DELIMITER);
      String n = "";
      try {
          if (tokens[3].equals("S")) {
              n += "13 "+ tokens[2];
              //System.out.println("Word: "+n);   
          }
          else if(Integer.parseInt(tokens[3]) >= 0 && 
            Integer.parseInt(tokens[3]) <= 12) {
              n += tokens[3]+"  " +tokens[2];
          }
          else {
              n += "14 "+tokens[2];
              //System.out.println("Word: "+n);
          }
          word.set(n);
          context.write(word, one);
      }
      catch (Exception e) {
            System.out.println("Error: "+e.toString());
      }
    }
  }

  public static class ItemBuysMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    final String DELIMITER = ",";

    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      String[] tokens = value.toString().split(DELIMITER);
      try { 
          word.set(tokens[2]);
          one.set(Integer.parseInt(tokens[4]));
          context.write(word, one);
      }
      catch (Exception e) {
          System.out.println("Error: "+e.toString());
      }
    }
  }

  public static class SessionItemClicksMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    final String DELIMITER = ",";
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      String n = "";
      String[] tokens = value.toString().split(DELIMITER);
      try { 
          n += tokens[0] + " " + tokens[2];                 
          word.set(n);
          context.write(word, one);
      }
      catch (Exception e) {
          System.out.println("Error: "+e.toString());
      }
    }
  }

  public static class SessionItemBuysMapper
       extends Mapper<Object, Text, Text, IntWritable>{

    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();
    final String DELIMITER = ",";
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      String[] tokens = value.toString().split(DELIMITER);
      String n = "";
      try { 
          n += tokens[0] + " " + tokens[2];
          word.set(n);
          one.set(Integer.parseInt(tokens[4]));
          context.write(word, one);
      }
      catch (Exception e) {
          System.out.println("Error: "+e.toString());
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
      int sum = 0;
      for (IntWritable val : values) {
        sum += val.get();
      }
      result.set(sum);
      context.write(key, result);
    }
  }

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "unit");
    job.setJarByClass(Main.class);
    if(args[0].equals("clicks")) {
        job.setMapperClass(ClicksMapper.class);
    }
    if(args[0].equals("buys")) {
        job.setMapperClass(BuysMapper.class);  
    }

    if(args[0].equals("category")) {
        job.setMapperClass(CategoryClicksMapper.class);  
    }

    if(args[0].equals("items_buy")) {
        job.setMapperClass(ItemBuysMapper.class);  
    }

    if(args[0].equals("session_item_clicks")) {
        job.setMapperClass(SessionItemClicksMapper.class);  
    }

    if(args[0].equals("session_item_buys")) {
        job.setMapperClass(SessionItemBuysMapper.class);  
    }
    
    job.setCombinerClass(DayReducer.class);
    job.setReducerClass(DayReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(args[1]));
    FileOutputFormat.setOutputPath(job, new Path(args[2]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}

















