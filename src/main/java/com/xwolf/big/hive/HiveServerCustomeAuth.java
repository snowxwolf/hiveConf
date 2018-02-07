package com.xwolf.big.hive;

import com.xwolf.big.util.MD5;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hive.service.auth.PasswdAuthenticationProvider;

import javax.security.sasl.AuthenticationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Hive Server自定义认证
 * @author xwolf
 * @since 1.8
 **/
public class HiveServerCustomeAuth implements PasswdAuthenticationProvider{

    /**
     *
     * @param username 用户名
     * @param password 密码
     * @throws AuthenticationException
     */
    @Override
    public void Authenticate(String username, String password) throws AuthenticationException {
        boolean result = false;
        HiveConf hiveConf = new HiveConf();
        Configuration conf = new Configuration(hiveConf);
        String passMd5 = MD5.md5(password);
        //读取自定义配置文件
        String filePath = conf.get("hive.server2.custom.authentication.file");
        File file = new File(filePath);
        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String tempString;
            while (null != (tempString = reader.readLine())) {
                String[] datas = tempString.split(",");
                if(datas.length != 2) {
                    continue;
                }
                if(datas[0].equals(username) && datas[1].equals(passMd5)) {
                    result = true;
                    break;
                }
            }
            if (result){
                System.out.println(String.format("[user]=%s,[password]=%s,authenticate success",username,password));
            } else {
                throw new AuthenticationException();
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new AuthenticationException("User Authenticate Error,Maybe the configuration file has some error.");
        }
    }
}
