/*
 
 * Package:com.rbt.common.util
 * FileName: RandomStrg.java
 */
package com.rbt.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.StringTokenizer;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * @function 功能  生成随机标识
 * @author  创建人 HXK
 * @date  创建日期  Jun 26, 2014
 */
public class RandomStrUtil {
	
    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
    public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
    public static final String NUMBERCHAR = "0123456789";  
    private static Integer length = new Integer(8);
    public static void setLength(int count){ length =new Integer(count);}

    private static String randomstr;
    private static boolean allchars = false;

    private static HashMap hmap;
    private static ArrayList lower = null;
    private static ArrayList upper = null;
    private static char[] single = null;
    private static int singlecount = 0;
    private static boolean singles = false;
    private static String algorithm = null;
    private static String provider = null;
    private static boolean secure = false;
    private static Random random = null;
    private static SecureRandom secrandom = null;

    
    /**
     * 方法描述：生成十为随机数字
     * @return
     */
    public static String getNumberRand(){
        return getRand("10");
    }
    
    /**
     * 方法描述：生成十六为随机数字
     * @return
     */
    public static String getCardnumRand(){
        return getRand("4");
    }
    
    public static String getRand(String len){
    	String tradeId = "";
        RandomStrUtil.setCharset("1-9");
        RandomStrUtil.setLength(len);
        try {
            RandomStrUtil.generateRandomObject();
            tradeId=RandomStrUtil.getRandom();
        } catch (Exception e){
            System.out.println("e = " + e.toString());
        }
        return tradeId;
    }
    
    /**
     * 方法描述：按照日期生成随机数
     * @return
     */
    public static String getDateRand()    {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		Date d = c.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(d) + (int)(Math.random() *10);
	}
    
    
    private static float getFloat() {
        if (random == null)
            return secrandom.nextFloat();
        else
            return random.nextFloat();
    }

    /**
     * generate the Random object that will be used for this random number
     * generator
     *
     */
    public static void generateRandomObject() throws Exception {

        // check to see if the object is a SecureRandom object
        if (secure) {
            try {
                // get an instance of a SecureRandom object
                if (provider != null)
                // search for algorithm in package provider
                    random = SecureRandom.getInstance(algorithm, provider);
                else
                    random = SecureRandom.getInstance(algorithm);
            } catch (NoSuchAlgorithmException ne) {
                throw new Exception(ne.getMessage());
            } catch (NoSuchProviderException pe) {
                throw new Exception(pe.getMessage());
            }
        } else
            random = new Random();
    }

    /**
     * generate the random string
     *
     */
    private static void generaterandom() {

        if (allchars)
            for (int i = 0; i < length.intValue(); i++)
                randomstr = randomstr + new Character((char)((int) 34 +
                        ((int)(getFloat() * 93)))).toString();
        else if (singles) {
            // check if there are single chars to be included

            if (upper.size() == 3) {
                // check for the number of ranges max 3 uppercase lowercase digits

                // build the random string
                for (int i = 0; i < length.intValue(); i++) {
                    // you have four groups to choose a random number from, to make
                    // the choice a little more random select a number out of 100

                    // get a random number even or odd
                    if (((int) (getFloat() * 100)) % 2 == 0) {

                        // the number was even get another number even or odd
                        if (((int) (getFloat() * 100)) % 2 == 0)
                        // choose a random char from the single char group
                            randomstr = randomstr + randomSingle().toString();
                        else
                        // get a random char from the first range
                            randomstr = randomstr + randomChar((Character)lower.get(2),
                                    (Character)upper.get(2)).toString();
                    } else {
                        // the number was odd

                        if (((int) (getFloat() * 100)) % 2 == 0)
                        // choose a random char from the second range
                            randomstr = randomstr + randomChar((Character)lower.get(1),
                                    (Character)upper.get(1)).toString();
                        else
                        // choose a random char from the third range
                            randomstr = randomstr + randomChar((Character)lower.get(0),
                                    (Character)upper.get(0)).toString();
                    }
                }
            } else if (upper.size() == 2) {
                // single chars are to be included choose a random char from
                // two different ranges

                // build the random char from single chars and two ranges
                for (int i = 0; i < length.intValue(); i++) {
                    // select the single chars or a range to get each random char
                    // from

                    if (((int)(getFloat() * 100)) % 2 == 0) {

                        // get random char from the single chars
                        randomstr = randomstr + randomSingle().toString();
                    } else if (((int) (getFloat() * 100)) % 2 == 0) {

                        // get the random char from the first range
                        randomstr = randomstr + randomChar((Character)lower.get(1),
                                (Character)upper.get(1)).toString();
                    } else {

                        // get the random char from the second range
                        randomstr = randomstr + randomChar((Character)lower.get(0),
                                (Character)upper.get(0)).toString();
                    }
                }
            } else if (upper.size() == 1) {

                // build the random string from single chars and one range
                for (int i = 0; i < length.intValue(); i++) {
                    if (((int) getFloat() * 100) % 2 == 0)
                    // get a random single char
                        randomstr = randomstr + randomSingle().toString();
                    else
                    // get a random char from the range
                        randomstr = randomstr + randomChar((Character)lower.get(0),
                                (Character)upper.get(0)).toString();
                }
            } else {
                // build the rand string from single chars
                for (int i = 0; i < length.intValue(); i++)
                    randomstr = randomstr + randomSingle().toString();
            }
        } else {

            // no single chars are to be included in the random string
            if (upper.size() == 3) {

                // build random strng from three ranges
                for (int i = 0; i < length.intValue(); i++) {

                    if (((int) (getFloat() * 100)) % 2 == 0) {

                        // get random char from first range
                        randomstr = randomstr + randomChar((Character)lower.get(2),
                                (Character)upper.get(2)).toString();
                    } else if (((int) (getFloat() * 100)) % 2 == 0) {

                        // get random char form second range
                        randomstr = randomstr + randomChar((Character)lower.get(1),
                                (Character)upper.get(1)).toString();
                    } else {

                        // get random char from third range
                        randomstr = randomstr + randomChar((Character)lower.get(0),
                                (Character)upper.get(0)).toString();
                    }
                }
            } else if (upper.size() == 2) {

                // build random string from two ranges
                for (int i = 0; i < length.intValue(); i++) {
                    if (((int) (getFloat() * 100)) % 2 == 0)
                    // get random char from first range
                        randomstr = randomstr + randomChar((Character)lower.get(1),
                                (Character)upper.get(1)).toString();
                    else
                    // get random char from second range
                        randomstr = randomstr + randomChar((Character)lower.get(0),
                                (Character)upper.get(0)).toString();
                }
            } else

            // build random string
                for (int i = 0; i < length.intValue(); i++)
                        // get random char from only range
                    randomstr = randomstr + randomChar((Character)lower.get(0),
                            (Character)upper.get(0)).toString();
        }
    }

    /**
     * generate a random char from the single char list
     *
     * returns - a randomly selscted character from the single char list
     *
     */
    private static Character randomSingle() {

        return (new Character(single[(int)((getFloat() * singlecount) - 1)]));
    }

    /**
     * generate a random character
     *
     * @param lower  lower bound from which to get a random char
     * @param upper  upper bound from which to get a random char
     *
     *
     */
    private static Character randomChar(Character lower, Character upper) {
        int tempval;
        char low = lower.charValue();
        char up = upper.charValue();

        // get a random number in the range lowlow - lowup
        tempval = (int)((int)low + (getFloat() * ((int)(up - low))));

        // return the random char
        return (new Character((char) tempval));
    }

    /**
     * get the randomly created string for use with the
     * &lt;jsp:getProperty name=<i>"id"</i> property="randomstr"/&gt;
     *
     * @return - randomly created string
     *
     */
    public static String getRandom() {

        randomstr = new String();

        generaterandom(); // generate the first random string

        if (hmap != null) {

            while (hmap.containsKey(randomstr)) {
                // random string has already been created generate a different one
                generaterandom();
            }

            hmap.put(randomstr, null);  // add the new random string
        }

        return randomstr;
    }

    /**
     * set the ranges from which to choose the characters for the random string
     *
     * @param low  set of lower ranges
     * @param up  set of upper ranges
     *
     */
    public static void setRanges(ArrayList low, ArrayList up) {
        lower = low;
        upper = up;
    }


    /**
     * set the hashmap that is used to check the uniqueness of random strings
     *
     * @param map  hashmap whose keys are used to insure uniqueness of random strgs
     *
     */
    public static void setHmap(HashMap map) {
        hmap = map;
    }

    /**
     * set the length of the random string
     *
     * @param value  length of the random string
     *
     */
    public static void setLength(String value) {
        length = new Integer(value);

    }

    /**
     * set the algorithm name
     *
     * @param value  name of the algorithm to use for a SecureRandom object
     *
     */
    public static void setAlgorithm(String value) {
        algorithm = value;
        secure = true;  // a SecureRandom object is to be used
    }

    /**
     * set the provider name
     *
     * @param value  name of the package to check for the algorithm
     *
     */
    public static void setProvider(String value) {
        provider = value;
    }

    /**
     * set the allchars flag
     *
     * @param value  boolean value of the allchars flag
     *
     */
    public static void setAllchars(boolean value) {
        allchars = value;
    }

    /**
     * set the array of single chars to choose from for this random string and the
     * number of chars in the array
     *
     * @param chars  the array of single chars
     * @param value  the number of single chars
     *
     */
    public static void setSingle(char[] chars, int value) {
        single = chars;  // set the array of chars
        singlecount = value;  // set the number of chars in array single
        singles = true;  // set flag that single chars are in use
    }

    public static void setCharset(String value)
    {
        // values tells the method whether or not to check for single chars
        boolean more = true;

        // create the arraylists to hold the upper and lower bounds for the char
        // ranges
        lower = new ArrayList(3);
        upper = new ArrayList(3);

        // user has chosen to use all possible characters in the random string
        if (value.compareTo("all") == 0) {
            allchars = true;  // set allchars flag
            // all chars are to be used so there are no single chars to sort
            // through
            more = false;
        }else if ((value.charAt(1) == '-') && (value.charAt(0) != '\\')) {
            // run through the ranges at most 3
            while (more && (value.charAt(1) == '-')){

                // check to make sure that the dash is not the single char
                if (value.charAt(0) == '\\')
                    break;
                else {
                    // add upper and lower ranges to there list
                    lower.add(new Character(value.charAt(0)));
                    upper.add(new Character(value.charAt(2)));
                }

                // check to see if there is more to the charset
                if (value.length() <= 3)
                    more = false;
                else
                // create a new string so that the next range if there is one
                // starts it
                    value = value.substring(3);
            }
        }

        // if more = false there are no single chars in the charset
        if (more) {

            single = new char[30];  // create single

            // create a set of tokens from the string of single chars
            StringTokenizer tokens = new StringTokenizer(value);

            while (tokens.hasMoreTokens()) {
                // get the next token from the string
                String token = tokens.nextToken();

                if (token.length() > 1)
                // char is a - add it to the list
                    single[singlecount++] = '-';

                // add the current char to the list
                single[singlecount++] = token.charAt(0);
            }
        }
        if ((lower == null) && (single == null))
            setCharset("a-zA-Z0-9");
    }
    /** 
     * 返回一个定长的随机字符串(只包含大小写字母、数字) 
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */  
    public static String generateString(int length) {  
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
        }  
        return sb.toString();  
    }  
  
    /** 
     * 返回一个定长的随机纯字母字符串(只包含大小写字母) 
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */  
    public static String generateMixString(int length) {  
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));  
        }  
        return sb.toString();  
    }  
  
    /** 
     * 返回一个定长的随机纯大写字母字符串(只包含大小写字母) 
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */  
    public static String generateLowerString(int length) {  
        return generateMixString(length).toLowerCase();  
    }  
  
    /** 
     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母) 
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */  
    public static String generateUpperString(int length) {  
        return generateMixString(length).toUpperCase();  
    }  
  
    /** 
     * 生成一个定长的纯0字符串 
     *  
     * @param length 
     *            字符串长度 
     * @return 纯0字符串 
     */  
    public static String generateZeroString(int length) {  
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            sb.append('0');  
        }  
        return sb.toString();  
    }  
  
    /** 
     * 根据数字生成一个定长的字符串，长度不够前面补0 
     *  
     * @param num 
     *            数字 
     * @param fixdlenth 
     *            字符串长度 
     * @return 定长的字符串 
     */  
    public static String toFixdLengthString(long num, int fixdlenth) {  
        StringBuffer sb = new StringBuffer();  
        String strNum = String.valueOf(num);  
        if (fixdlenth - strNum.length() >= 0) {  
            sb.append(generateZeroString(fixdlenth - strNum.length()));  
        } else {  
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth  
                    + "的字符串发生异常！");  
        }  
        sb.append(strNum);  
        return sb.toString();  
    }  
  
    /** 
     * 每次生成的len位数都不相同 
     *  
     * @param param 
     * @return 定长的数字 
     */  
    public static int getNotSimple(int[] param, int len) {  
        Random rand = new Random();  
        for (int i = param.length; i > 1; i--) {  
            int index = rand.nextInt(i);  
            int tmp = param[index];  
            param[index] = param[i - 1];  
            param[i - 1] = tmp;  
        }  
        int result = 0;  
        for (int i = 0; i < len; i++) {  
            result = result * 10 + param[i];  
        }  
        return result;  
    }  
  
    public static void main(String[] args) {  
        System.out.println("返回一个定长的随机字符串(只包含大小写字母、数字):" + generateString(18));  
    }  
}
