/**
 * @program: RiverDemo
 * @description:
 * @author: River
 * @create: 2019-10-27 10:38
 **/
package com.river.FinkDemo;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

/**
 * @ClassName:  SocketWindowWordCount
 * @Description:TODO(flink单词个数统计)
 * @author: Jimu
 * @email:  maker2win@163.com
 * @date:   2019年3月19日 下午3:18:03
 *
 * @Copyright: 2019 www.maker-win.net Inc. All rights reserved.
 *
 */
public class SocketWindowWordCount {
<<<<<<< HEAD
    /*public static void main(String[] args) throws Exception{
        //连接端口号
        final int port=9000;
        *//*try {
            final ParameterTool params = ParameterTool.fromArgs(args);
            port = params.getInt("port");
        } catch (Exception e) {
            System.out.println("No port specified. Please run 'SocketWindowWordCount --port <port>'");
            return ;
        }*//*
        //获取执行环节
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //获取连接socket输入数据
        DataStream<String> text = env.socketTextStream("localhost", port,"\n");

        //解析数据、对数据进行分组、窗口函数和统计个数

        DataStream<WordWithCount> windowCounts =text.flatMap(new FlatMapFunction<String, WordWithCount>() {

            private static final long serialVersionUID = 6800597108091365154L;

            public void flatMap(String value, Collector<WordWithCount> out) throws Exception {
                for(String word:value.split("//s")) {
                    out.collect(new WordWithCount(word, 1));
                }
            }
        })
                .keyBy("word")
                .timeWindow(Time.seconds(5),Time.seconds(1))
                .reduce(new ReduceFunction<WordWithCount>() {

                    public WordWithCount reduce(WordWithCount value1, WordWithCount value2) throws Exception {

                        return new WordWithCount(value1.word,value1.count+value2.count);
                    }
                });
        windowCounts.print().setParallelism(1);

        env.execute("Socket Window WordCount");

    }*/
=======
    public static void main(String[] args) throws Exception {
        // the host and the port to connect to
        final String hostname;
        final int port;
        try {
            final ParameterTool params = ParameterTool.fromArgs(args);
            hostname = params.has("hostname") ? params.get("hostname") : "localhost";
            port = params.has("port") ? params.getInt("port"):9000;
        } catch (Exception e) {
            System.err.println("No port specified. Please run 'SocketWindowWordCount " +
                    "--hostname <hostname> --port <port>', where hostname (localhost by default) " +
                    "and port is the address of the text server");
            System.err.println("To start a simple text server, run 'netcat -l <port>' and " +
                    "type the input text into the command line");
            return;
        }
        // get the execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // get input data by connecting to the socket
        DataStream<String> text = env.socketTextStream(hostname, port, "\n");
        // parse the data, group it, window it, and aggregate the counts
        DataStream<WordWithCount> windowCounts = text
                .flatMap(new FlatMapFunction<String, WordWithCount>() {
                    public void flatMap(String value, Collector<WordWithCount> out) {
                        for (String word : value.split("\\s")) {
                            out.collect(new WordWithCount(word, 1L));
                        } }})
                .keyBy("word")
                .timeWindow(Time.seconds(5))
                .reduce(new ReduceFunction<WordWithCount>() {
                    public WordWithCount reduce(WordWithCount a, WordWithCount b) {
                        return new WordWithCount(a.word, a.count + b.count);
                    }});
        // print the results with a single thread, rather than in parallel
        windowCounts.print().setParallelism(1);
        env.execute("Socket Window WordCount");
    }
    /**
     * Data type for words with count.
     */
    public static class WordWithCount {
        public String word;
        public long count;
        public WordWithCount() {}
        public WordWithCount(String word, long count) {
            this.word = word;
            this.count = count;
        }
        @Override
        public String toString() {
            return word + " : " + count;
        }
    }
>>>>>>> 4e7d5be5383c4a2431cb4eacca62015123994f5c

}

