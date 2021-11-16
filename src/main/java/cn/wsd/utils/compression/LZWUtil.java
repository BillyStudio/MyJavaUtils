package cn.wsd.utils.compression;

import java.util.*;

public class LZWUtil {
	static int maxBitSize;	// 记录压缩码元所占最大二进制位数

	/**
	 * 计算LZW算法压缩比例
	 * @param fileStream
	 * @param byteArrLen 返回字节数组的长度，默认是2
	 * @return
	 */
	public static byte[] calcCompressedRatio(String fileStream, int byteArrLen) {
		List<Integer> compressed = compress(fileStream);
		double compressedRatio = maxBitSize * compressed.size() / (8.0 * fileStream.length());
		byte[] ans = new byte[byteArrLen];
		double base = 0;
		for (int i = 0; i < byteArrLen*8; ++i) {
			if (compressedRatio > base) {
				setBit(ans, i, 1);
			} else {
				setBit(ans, i, 0);
			}
		}
		return ans;
	}

	/** Compress a string to a list of output symbols. */
	public static List<Integer> compress(String uncompressed) {
		int maxValue = 0;
		// Build the dictionary.
		int dictSize = 256;
		Map<String,Integer> dictionary = new HashMap<String,Integer>();
		for (int i = 0; i < 256; i++)
			dictionary.put("" + (char)i, i);

		String w = "";
		List<Integer> result = new ArrayList<Integer>();
		for (char c : uncompressed.toCharArray()) {
			String wc = w + c;
			if (dictionary.containsKey(wc))
				w = wc;
			else {
				result.add(dictionary.get(w));
				// Add wc to the dictionary.
				dictionary.put(wc, dictSize++);
				maxValue = Math.max(dictSize, maxValue);
				w = "" + c;
			}
		}

		// Output the code for w.
		if (!w.equals(""))
			result.add(dictionary.get(w));
		// calulate max bit length
		maxBitSize = 0;
		while(maxValue != 0) {
			maxValue >>= 1;
			maxBitSize++;
		}
		return result;
	}

	/** Decompress a list of output ks to a string. */
	public static String decompress(List<Integer> compressed) {
		// Build the dictionary.
		int dictSize = 256;
		Map<Integer,String> dictionary = new HashMap<Integer,String>();
//		for (int i = 0; i < 256; i++)
//			dictionary.put(i, "" + (char)i);

		String w = "" + (char)(int)compressed.remove(0);
		StringBuffer result = new StringBuffer(w);
		for (int k : compressed) {
			String entry;
			if (dictionary.containsKey(k))
				entry = dictionary.get(k);
			else if (k == dictSize)
				entry = w + w.charAt(0);
			else
				throw new IllegalArgumentException("Bad compressed k: " + k);

			result.append(entry);

			// Add w+entry[0] to the dictionary.
			dictionary.put(dictSize++, w + entry.charAt(0));

			w = entry;
		}
		return result.toString();
	}

	public static String generateRandomAlphanumericString(int targetStringLength) {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();

		return generatedString;
	}

	private static void setBit(byte[] arr, int idx, int bit) {
		int arrIdx = idx / 8;
		int bitIdx = idx % 8;
		arr[arrIdx] &= (1<<bitIdx);
	}

	public static void main(String[] args) {
		for(int i = 0; i < 10; ++i) {
			String input = generateRandomAlphanumericString(5000); // "TOBEORNOTTOBEORTOBEORNOT"
//			System.out.println(input);
			List<Integer> compressed = compress(input);
			System.out.printf("max bit size= %d, compressed size= %d, ", maxBitSize, compressed.size());
			double compressedRatio = maxBitSize * compressed.size() / (8.0 * input.length());
			System.out.printf("Compressed ratio=%f ,", compressedRatio);
			String output = decompress(compressed);
			System.out.println(input.equals(output));
		}
	}
}
