package com.becks.algorithmdemo.TencentTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @ClassName SqlWhereAnalyze
 * @Description 判断解析sql where 条件语句
 * @Author Becks Hwang
 * @Date 2021/4/22 16:54
 */
public class SqlWhereAnalyze {

    String condition = "(\\w+\\.){0,1}\\w+\\s*(((=|>|<|IS|)\\s*?(\\w+\\.){0,1}[\\w%])|((LIKE)\\s*\"?(\\w+\\.){0,1}[\\w%]+\"))?";//条件的正则表达式,LIKE单独匹配

    String conditions = condition + "(\\s+(AND|OR)\\s*" + condition + "\\s*)*";//条件组合匹配AND、OR

    /**
     * 判断传入的字符串是否符合sql where条件语句
     * true：符合；false：不符合
     **/
    public void stringAnalyze(String sqlStr) {
        sqlStr = sqlStr.toUpperCase();
        System.out.println("sql where 解析结果：" + sqlStr.matches(conditions));
        System.out.println("******************************************");
    }

    /**
     * 判断传入的json是否符合sql where条件语句
     * 支持多层嵌套
     * SQL语句 : "a = 2 or b.c = 3"
     * 对应json:
     * {
     *  "a":"= 2",
     *  "or b":{
     *          ".c":"= 3"
     *          }
     * }
     * true：符合；false：不符合
     **/
    public void jsonAnalyze(JSONObject json) {
        System.out.println("JSON:");
        System.out.println(JSON.toJSONString(json, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat));
        stringAnalyze(jsonToString(json));
        System.out.println("******************************************");
    }

    /**
     * 将json转为String
     **/
    public String jsonToString(JSONObject json) {
        StringBuffer stringBuffer = new StringBuffer();
        for (String key : json.keySet()) {
            if (json.get(key) instanceof JSONObject) {
                stringBuffer.append(key + jsonToString(json.getJSONObject(key)) + " ");
            } else {
                stringBuffer.append(key + " " + json.getString(key) + " ");
            }
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        SqlWhereAnalyze sqlWhereAnalyze = new SqlWhereAnalyze();

        String testStr1 = "a = \"String\"";
        System.out.println("字符串：" + testStr1);
        sqlWhereAnalyze.stringAnalyze(testStr1);

        String testStr2 = "a = 2 and b like \"he%\" or a = 1";
        System.out.println("字符串：" + testStr2);
        sqlWhereAnalyze.stringAnalyze(testStr2);

        String testStr3 = "a = 2 or b.c = 3";
        System.out.println("字符串：" + testStr3);
        sqlWhereAnalyze.stringAnalyze(testStr3);

        JSONObject testJson1 = new JSONObject(true);
        testJson1.put("a", " = \"String\"");
        sqlWhereAnalyze.jsonAnalyze(testJson1);

        JSONObject testJson2 = new JSONObject(true);
        testJson2.put("a", "= 2");
        testJson2.put("and b", "like \"he%\"");
        testJson2.put("or a", "= 1");
        sqlWhereAnalyze.jsonAnalyze(testJson2);

        JSONObject testJson3 = new JSONObject(true);
        testJson3.put("a", "= 2");
        JSONObject testJson3_1 = new JSONObject(true);
        testJson3_1.put(".c", "= 3");
        testJson3.put("or b", testJson3_1);
        sqlWhereAnalyze.jsonAnalyze(testJson3);

        /**
         * 输出结果
         *
         * 字符串：a = "String"
         * sql where 解析结果：false
         * ******************************************
         * 字符串：a = 2 and b like "he%" or a = 1
         * sql where 解析结果：true
         * ******************************************
         * 字符串：a = 2 or b.c = 3
         * sql where 解析结果：true
         * ******************************************
         * JSON:
         * {
         * 	"a":" = \"String\""
         * }
         * sql where 解析结果：false
         * ******************************************
         * ******************************************
         * JSON:
         * {
         * 	"a":"= 2",
         * 	"and b":"like \"he%\"",
         * 	"or a":"= 1"
         * }
         * sql where 解析结果：true
         * ******************************************
         * ******************************************
         * JSON:
         * {
         * 	"a":"= 2",
         * 	"or b":{
         * 		".c":"= 3"
         *        }
         * }
         * sql where 解析结果：true
         * ******************************************
         * ******************************************
         *
         * Process finished with exit code 0
         **/

    }
}
