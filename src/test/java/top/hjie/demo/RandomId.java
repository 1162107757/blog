package top.hjie.demo;

import java.util.Random;


// 生成不重复的int
public class RandomId {
    private static Random random = new Random();
    private static String table = "0123456789";
    
    public static String randomId() {
        int id=random.nextInt(10);
        String ret = null,
                num = String.format("%05d", id);
        int key = random.nextInt(10),
                seed = random.nextInt(100);
        Caesar caesar = new Caesar(table, seed);
        num = caesar.encode(key, num);
        ret = num
                + String.format("%01d", key)
                + String.format("%02d", seed);
        
        ret = caesar.randomTime(ret);
        
        return ret;
    }
}


class Caesar {
	
    private String table;
    private int seedA = 1103515245;
    private int seedB = 12345;

    public Caesar(String table, int seed) {
        this.table = chaos(table, seed, table.length());
    }
    public Caesar(String table) {
        this(table, 11);
    }
    public Caesar() {
        this(11);
    }
    public Caesar(int seed) {
        this("ABCDEFGHIJKLMNOPQRSTUVWXYZ", seed);
    }
    public char dict(int i, boolean reverse) {
        int s = table.length(), index = reverse ? s - i : i;
        return table.charAt(index);
    }
    public int dict(char c,  boolean reverse) {
        int s = table.length(), index = table.indexOf(c);
        return reverse ? s - index : index;
    }
    public int seed(int seed) {
        long temp = seed;
        return (int)((temp * seedA + seedB) & 0x7fffffffL);
    }

    public String chaos(String data, int seed, int cnt) {
        StringBuffer buf = new StringBuffer(data);
        char tmp; int a, b, r = data.length();
        for (int i = 0; i < cnt; i += 1) {
            seed = seed(seed); a = seed % r;
            seed = seed(seed); b = seed % r;
            tmp = buf.charAt(a);
            buf.setCharAt(a, buf.charAt(b));
            buf.setCharAt(b, tmp);
        }
        return buf.toString();
    }

    public String crypto(boolean reverse,
                         int key, String text) {
        String ret = null;
        StringBuilder buf = new StringBuilder();
        int m, s = table.length(), e = text.length();

        for(int i = 0; i < e; i += 1) {
            m = dict(text.charAt(i), reverse);
            if (m < 0) break;
            m = m + key + i;
            buf.append(dict(m % s, reverse));
        }
        if (buf.length() == e)
            ret = buf.toString();
        return ret;
    }
    public String encode(int key, String text) {
        return crypto(false, key, text);

    }
    public String decode(int key, String text) {
        return crypto(true , key, text);
    }
    
    public String randomTime(String ret){
    	StringBuffer append = new StringBuffer().append(System.currentTimeMillis());
    	char[] timeArray = append.toString().toCharArray();
    	String randomNum = "";
    	for (int i = 0; i < timeArray.length; i++) {
    		Random random = new Random();
    		int nextInt = random.nextInt(timeArray.length);
    		randomNum += timeArray[nextInt];
		}
    	randomNum += ret;
    	char[] ranArray = randomNum.toCharArray();
    	randomNum = "";
    	for (int i = 0; i < 8; i++) {
    		Random random = new Random();
    		int nextInt = random.nextInt(ranArray.length);
    		randomNum += ranArray[nextInt];
		}
    	return randomNum;
    }
}
