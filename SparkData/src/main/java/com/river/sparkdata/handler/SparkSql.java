package com.river.sparkdata.handler;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrameReader;
import org.apache.spark.sql.SQLContext;

/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-10-07 09:10
 **/
public class SparkSql {
    private JavaSparkContext sparkContext = null;

    public SparkSql(JavaSparkContext sparkContext){
        this.sparkContext = sparkContext;
    }

    public void call(){
        JavaRDD rdd = sparkContext.textFile("D:\\test.json");
        SQLContext sqlContext = SQLContext.getOrCreate(rdd.context());
        DataFrame dataFrame = sqlContext.read().json(rdd);
        dataFrame.registerTempTable("person");
        DataFrame resultDataFrame = sqlContext.sql("select * from person where lovesPandas=true");
        resultDataFrame.show(false);
    }
}
