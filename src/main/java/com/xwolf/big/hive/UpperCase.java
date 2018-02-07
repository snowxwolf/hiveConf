package com.xwolf.big.hive;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * 转化为大写字母
 * @author xwolf
 * @since 1.8
 **/
public class UpperCase extends UDF {

    public Text evaluate(final Text s) {

        if (s == null) {
            return null;
        }
        return new Text(s.toString().toUpperCase());
    }
}
