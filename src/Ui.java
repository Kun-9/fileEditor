import java.io.IOException;
import java.util.Scanner;

public class Ui {

	Scanner sc;
	FileChanger fileChanger;

	public Ui() {
		sc = new Scanner(System.in);
		fileChanger = new FileChanger();
		System.out.println("폴더 내의 모든 파일을 변경하는 코드입니다.");
		System.out.println("한 폴더에 변경할 파일들을 모두 넣어주세요.");
	}

	public void start() throws IOException {

		String url;
		do {
			System.out.println("< 폴더 URL 입력 >");
			System.out.print(">> ");
			url = sc.next();
		} while (!fileChanger.setPath(url));

		while (true) {

			System.out.println("| 1.파일 이름 변경 | 2.pdf 암호 제거 | 3.종료 |");
			System.out.print(">> ");

			int input = sc.nextInt();

			switch (input) {
				case 1:
					changeName();
					break;
				case 2:
					unlockPdf();
					break;
				case 3:
					return;
				default:
			}
		}
	}

	public void changeName() {

		System.out.println("< 공통적으로 없앨 문자열 입력 >");
		System.out.print(">> ");
		String cropStr = sc.next();
		fileChanger.renameFiles(cropStr);
	}

	public void unlockPdf() {
		System.out.println("< 암호 입력 >");
		System.out.print(">> ");
		String password = sc.next();
		fileChanger.unlock(password);

	}

}
