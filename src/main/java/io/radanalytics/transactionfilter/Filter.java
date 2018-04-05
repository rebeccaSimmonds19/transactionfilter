package radanalytics.transactionfilter;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

import java.util.Properties;


/**
 * Filter clients which have not defaulted on payments
 * so the left over clients can be sent to the rules engine
 * the rules engine can then determine if they are eligible for a credit card
 *
 */
public class Filter {

    //constructor
    public Filter()
    {

    }

    public void filterData(String data)
    {
        //create a spark session
        SparkSession spark = SparkSession.builder().appName("Simple Name").config("spark.master", "local").getOrCreate();
        String url = "jdbc:postgresql://postgresl/transactionDb?user=username&password=password";
        Properties properties = new Properties();
        properties.setProperty("driver","org.postgresql.Driver");
        Dataset df =spark.read().jdbc(url, "clients",properties).toDF();
        //coloumn constraint
        Dataset result = df.where("PAY_0> 0 AND PAY_2 > 0 AND PAY_3 > 0 AND PAY_4 > 0 AND PAY_5 > 0 AND PAY_6 >0");
        System.out.println(result);
    }
}
