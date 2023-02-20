import com.aspose.pdf.Document;

import java.io.*;

public class FileChanger {
	private String path;

	private File directory;

	public boolean setPath(String path) {
		this.path = path;
		directory = new File(this.path);
		return directory.isDirectory();
	}

	public File[] getFilesNames() {

		// 디렉토리인지 확인
		if (directory.isDirectory()) {
			// 디렉토리 내의 파일 목록 가져오기
			return directory.listFiles();

		} else {
			return null;
		}
	}

	public void renameFiles(String deleteString) {

		File[] filesNames = getFilesNames();

		for (File f : filesNames) {

			String fileName = f.getName();

			// 변경 할 이름
			String newFileName = fileName.replace(deleteString, "");
			String newFilePath = path + "/" + newFileName;

			File newFile = new File(newFilePath);

			boolean b = f.renameTo(newFile);

			if (b) System.out.println(fileName + " : 변경 성공 to " + newFileName);
			else System.out.println(fileName + "변경 실패");

		}
	}

	public void unlock(String password) {

		File[] filesNames = getFilesNames();

		for (File f : filesNames) {
			String fileName = f.getName();
			String extension = fileName.split("\\.")[1];
			if (!extension.equals("pdf")) {
				continue;
			}

			String fullName = path + "/" + fileName;
			// 문서 열기
			Document document = new Document(fullName, password);
			// PDF 해독
			document.decrypt();
			// 업데이트된 PDF 저장
			document.save(fullName);
			System.out.println(fileName + " -> 암호 해제 완료");
		}
		System.out.println();
		System.out.println("< 암호 해제 메소드 종료 >");
	}
}


