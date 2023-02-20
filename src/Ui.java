import com.aspose.pdf.exceptions.InvalidPasswordException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Ui {

	Scanner sc;
	BufferedReader br;
	FileChanger fileChanger;

	public Ui() {
		br = new BufferedReader(new InputStreamReader(System.in));
		fileChanger = new FileChanger();
		System.out.println("폴더 내의 모든 파일을 변경하는 코드입니다.");
		System.out.println("한 폴더에 변경할 파일들을 모두 넣어주세요.");
	}

	public void start() throws IOException {

		String url;
		do {
			System.out.println("< 폴더 URL 입력 >");
			System.out.print(">> ");
			url = br.readLine();
		} while (!fileChanger.setPath(url));

		while (true) {

			System.out.println("| 1.파일 이름 변경 | 2.pdf 암호 제거 | 3.종료 |");
			System.out.print(">> ");


			String input = br.readLine();

//			try {
//				int i = Integer.parseInt(input);
//			}

			switch (input) {
				case "1":
					changeName();
					break;
				case "2":
					unlockPdf();
					break;
				case "3":
					br.close();
					return;
				default:
					System.out.println("1~3 사이의 숫자를 입력해 주세요.");
			}
		}
	}

	public void changeName() throws IOException {

		System.out.println("< 공통적으로 없앨 문자열 입력 >");
		System.out.print(">> ");
		String cropStr = br.readLine();
		fileChanger.renameFiles(cropStr);
	}

	public void unlockPdf() throws IOException {
		System.out.println("< 암호 입력 >");
		System.out.print(">> ");

		String password = br.readLine();
		try {
			fileChanger.unlock(password);

		} catch (InvalidPasswordException e) {
			System.out.println("암호가 일치하지 않습니다.");
		}
	}

}
