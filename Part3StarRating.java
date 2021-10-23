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


public class Part3StarRating {
	public static String Table_Name = "GameTable";
	
	public static void main(String[] args) throws Throwable {
		Configuration conf = HBaseConfiguration.create();        
		@SuppressWarnings({ "deprecation", "resource" })
		HTable hTable = new HTable(conf, Table_Name);
		
		//define the filter
		SingleColumnValueFilter filter1 = new SingleColumnValueFilter(
				Bytes.toBytes("Rating"), 
				Bytes.toBytes("star_rating"),
				CompareOp.EQUAL,
				new BinaryComparator(Bytes.toBytes(5)));
		
		
		Scan scan1 = new Scan();
		scan1.setFilter(filter1);
		
		//now we extract the result
		ResultScanner scanner1 = hTable.getScanner(scan1);
		
		int star_rating_5 = 0;
		for(Result result=scanner1.next(); result!=null; result=scanner1.next()) 
			star_rating_5++;
		
		System.out.println("Star_rating_5: "+star_rating_5);
	}
}
