import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;


public class Part3ReviewHeadlines {
	public static String Table_Name = "GameTable";
	
	public static void main(String[] args) throws Throwable {
		Configuration conf = HBaseConfiguration.create();        
		@SuppressWarnings({ "deprecation", "resource" })
		HTable hTable = new HTable(conf, Table_Name);
		
		//define the filter
		SingleColumnValueFilter filter = new SingleColumnValueFilter(
				Bytes.toBytes("Rating"), 
				Bytes.toBytes("star_rating"),
				CompareOp.EQUAL,
				new BinaryComparator(Bytes.toBytes(Integer.parseInt("1"))));
		
		
		Scan scan = new Scan();
		scan.setFilter(filter);
		
		//now we extract the result
		ResultScanner scanner = hTable.getScanner(scan);
		for(Result result=scanner.next(); result!=null; result=scanner.next()) {
			
			String review_headlines=new String(result.getValue(
					Bytes.toBytes("Review"),
					Bytes.toBytes("review_headline")));
			System.out.println("Review_Headline:"+review_headlines);
		}
	}
}
