package sdk.security.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 字符串处理工具
 */
public class StringUtil {
	
	private StringUtil(){
		
	}
	
	public static boolean isEmptyString(String s) {
		if (s == null || "".equals(s.trim())) {
			return true;
		} else {
			return false;
		}
	}

	public static String replace(String s, String target, String replace) {
		if (s == null || target == null || replace == null) {
			throw new RuntimeException("字符串运算错误，进行字符串替换操作时参数不正确。");
		}
		int start = s.indexOf(target);
		if (start == -1)
			return s;
		String out = s.substring(0, start) + replace + s.substring(start + target.length());
		return out;
	}

	/**
	 * 将字符数据连接为字符串，以split作为间隔
	 * 
	 * @param array
	 * @param split
	 * @return
	 */
	public static String join(String[] array, String split) {
		if (array == null || array.length == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);
			if (i < array.length - 1) {
				sb.append(split);
			}
		}
		return sb.toString();
	}

	public static String[] commaDelimitedListToStringArray(String str) {
		return delimitedListToStringArray(str, ",");
	}

	public static String[] delimitedListToStringArray(String str, String delimiter) {
		return delimitedListToStringArray(str, delimiter, null);
	}

	public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete) {
		if (str == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] { str };
		}
		List result = new ArrayList();
		if ("".equals(delimiter)) {
			for (int i = 0; i < str.length(); i++)
				result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
		} else {
			int pos = 0;
			int delPos;
			while ((delPos = str.indexOf(delimiter, pos)) != -1) {
				result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
				pos = delPos + delimiter.length();
			}
			if ((str.length() > 0) && (pos <= str.length())) {
				result.add(deleteAny(str.substring(pos), charsToDelete));
			}
		}
		return toStringArray(result);
	}

	public static String deleteAny(String inString, String charsToDelete) {
		if ((!hasLength(inString)) || (!hasLength(charsToDelete))) {
			return inString;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String[] toStringArray(Collection<String> collection) {
		if (collection == null) {
			return null;
		}
		return (String[]) collection.toArray(new String[collection.size()]);
	}

	public static boolean hasLength(CharSequence str) {
		return str != null && str.length() > 0;
	}

	public static boolean hasLength(String str) {
		return hasLength((CharSequence) str);
	}

}