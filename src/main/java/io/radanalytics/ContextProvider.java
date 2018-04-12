package io.radanalytics;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;


/**
 * Provide the Spark context
 */
public class ContextProvider {

    private static ContextProvider INSTANCE = null;

    private SparkConf sparkConf;
    private JavaSparkContext sparkContext;

    private ContextProvider() {
    }

    private ContextProvider(Properties props) {
        this.sparkConf = new SparkConf().setAppName("Client");
        this.sparkConf.setJars(new String[]{props.getJarFile()});
        this.sparkContext = new JavaSparkContext(sparkConf);
    }

    public static boolean init(Properties props) {
        try {
            if (INSTANCE == null) {
                INSTANCE = new ContextProvider(props);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public static JavaSparkContext getContext() {
        return INSTANCE.sparkContext;
    }

}