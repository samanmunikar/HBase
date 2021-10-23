import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.util.Bytes;


public class Part3HelpfulVote {
	public static String Table_Name = "GameTable";
	
	public static void main(String[] args) throws Throwable {
		Configuration conf = HBaseConfiguration.create();        
		@SuppressWarnings({ "deprecation", "resource" })
		HTable hTable = new HTable(conf, Table_Name);
		
		Scan scan = new Scan();
		
		//now we extract the result
		ResultScanner scanner = hTable.getScanner(scan);
		int count = 0;
		int sum = 0;
		for(Result result=scanner.next(); result!=null; result=scanner.next()) 
		{
			
			int helpful_votes=Bytes.toInt(result.getValue(
					Bytes.toBytes("Rating"),
					Bytes.toBytes("helpful_votes")));
			count++;
			sum+=helpful_votes;
		}
		double avg = (double)(sum/count);
	
		
		System.out.println(avg);
				
	}
}
