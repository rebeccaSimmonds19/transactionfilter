package io.radanalytics;

import org.apache.commons.lang.StringUtils;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;

import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.SparkSession;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Properties;


import java.util.List;

/**
 * REST end point to return data
 */
@RestController
public class Controller {

    @RequestMapping("/")
    public String filterData() {
        //get SparkContext
        JavaSparkContext jsc = ContextProvider.getContext();

        //set up Postgresql
        String url = "jdbc:postgresql://postgresl/transactionDb?user=username&password=password";
        Properties properties = new Properties();
        properties.setProperty("driver","org.postgresql.Driver");
        SparkSession spark = SparkSession.builder().config(jsc.getConf()).getOrCreate();
        //test with csv
        Dataset df = spark.read().jdbc(url, "clients",properties).toDF();


        //coloumn constraint
        Dataset result = df.where("PAY_0> 0 AND PAY_2 > 0 AND PAY_3 > 0 AND PAY_4 > 0 AND PAY_5 > 0 AND PAY_6 >0");
        List<String> listOne = result.as(Encoders.STRING()).collectAsList();

        //return data
        return StringUtils.join(listOne, ",");
    }
}