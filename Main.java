import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 문제
 * 1. 알파벳+숫자로 입력한다.
 * 2. 내림차순 표현.
 * 
 * 규칙
 * - 알파벳+숫자로만 입력
 * Test-Case
 * - 대소문자 테스트(완료) / 숫자만 있는 경우(완료) / 대소문자만 있는 경우(완료) / 숫자가 영어보다 더 있는 경우(완료)
 * @author YangChunSung
 * @since 18.04.13
 */
public class Main {

	public static void main(String[] args) {
		
		try {
			//while(true) {
				//printView(); // 첫 화면 view
				
				Scanner sc = new Scanner(System.in);
				String str = sc.next();
				
				//종료 로직
				/*if("q".equals(str) || "Q".equals(str)) {
					System.out.println("==== 사용자에 의한 종료되었습니다 ====");
					break;
				}*/
				
				// 영어와 숫자가 있는지 체크
				boolean bl = checkNumberAndAlphabet(str);
				
				// 영어와 숫자 검증
				if(bl) {
					// 숫자 가져온 후, 내림 차순 정렬
					String numStr = str.replaceAll("[^0-9]", "");
					ArrayList<String> numList = getTextReturn(numStr, "desc"); // desc:내림차순 정렬
					
					// 영어만 가져온 후 , 내림 차순 정렬
					String engStr = str.replaceAll("[^a-zA-Z]", "");
					ArrayList<String> engList = getTextReturn(engStr, "desc"); // desc:내림차순 정렬
					
					// 최종 결과
					viewStr(numList, engList);
					
					//break;
					
				} else {
					System.out.println("");
					System.out.println("==== [규칙] 영어랑 숫자와 같이 입력하세요 ====");
					System.out.println("");
				}
			//}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 최종 결과 추출
	 * @param numList : 숫자 리스트
	 * @param engList : 영어 리스트
	 */
	public static void viewStr(ArrayList<String> numList, ArrayList<String> engList ) {
		
		int numLength = numList.size();
		int engLength = engList.size();
		int loopLength = numLength > engLength ? numLength:engLength;
		StringBuffer sb = new StringBuffer();
		
		for (int loopIdx = 0; loopIdx < loopLength; loopIdx++) {
			sb.append(loopIdx < engLength ? engList.get(loopIdx) : "").append(loopIdx < numLength ? numList.get(loopIdx) : "");
		}
		
		/*for (int i = 0; i < loopLength; i++) {
			if (i < engLength) {
				sb.append(engList.get(i));
			}
			if (i < numLength) {
				sb.append(numList.get(i));
			}
		}*/
		
		System.out.println("sb : "+sb.toString());
		System.out.println("==== 종료되었습니다 ====");
	}
	
	/**
	 * printView
	 */
	public static void printView() {
		System.out.print("");
		System.out.println("##################################################");
		System.out.println("#### 영어랑 숫자와 같이 입력하는 Console Game ####");
		System.out.println("#### 종료는 'Q' or 'q' 를 입력하시면 됩니다   ####");
		System.out.println("##################################################");
		System.out.print("> ");
	}
	
	/**
	 * 입력 받은 텍스트 내림차순 정렬
	 * @param text : 입력 받은 텍스트
	 * @param order : 내림차순(desc), 오름차순(asc)
	 * @return 정렬 된 리스트 데이터
	 */
	public static ArrayList<String> getTextReturn(String text, final String order) {
		
		ArrayList<String> textList = new ArrayList<String>();		
		char c;
		
		for(int charIdx=0; charIdx<text.length(); charIdx++) {
		    c = (text.charAt(charIdx));
		    textList.add(charIdx, String.valueOf(c));
		}
		
		Collections.sort(textList, new Comparator<String>() {
    	    public int compare(String s1, String s2) {
    	        return "desc".equals(order)?s2.compareTo(s1):s1.compareTo(s2);
    	    }
    	});
		
		return textList;
		
		/*ArrayList<String> textList = new ArrayList<String>();
		char[] array_word = new char[text.length()]; // 스트링을 담을 배열
		for(int i=0; i<array_word.length; i++) {
		    array_word[i]=(text.charAt(i));//스트링을 한글자씩 끊어 배열에 저장
		    String stringValueOf = String.valueOf(array_word[i]);
		    textList.add(i, stringValueOf);
		}
		Collections.sort(textList);
		if("desc".equals(order)) {
			Collections.reverse(textList);
		}
		return textList;*/
	}
	
	/**
	 * 숫자와 영어 검증 메소드
	 * @param textInput
	 * @return true, false
	 */
	public static boolean checkNumberAndAlphabet(String textInput) {
		char chrInput;
		boolean bl = false;
		int CheckNumber = 0;
		int CheckAlphabet = 0;
		int textInputLenth = textInput.length();
		
		for(int textLen=0; textLen<textInputLenth; textLen++) {
			chrInput = textInput.charAt(textLen); // 입력받은 텍스트에서 문자 하나하나 가져와서 체크
			
			if (chrInput >= 0x61 && chrInput <= 0x7A) {// 영어 소문자
				CheckNumber++;
			} else if (chrInput >= 0x41 && chrInput <= 0x5A) {// 영어 대문자
				CheckNumber++;
			} else if (chrInput >= 0x30 && chrInput <= 0x39) {// 숫자
				CheckAlphabet++;
			} else { // 영문자 및 숫자 아님
				return bl; 
			}
		}
		
		// 영어와 숫자가 무조건 있어야 한다
		if(CheckNumber >= 0 && CheckAlphabet >= 0) {
			bl = true;
			return bl;
		}
		
		return bl;
	}
}
