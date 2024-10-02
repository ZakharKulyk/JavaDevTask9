package httpService;

import java.util.Scanner;

public class HttpImageStatusCli {
    HttpStatusImageDownloader httpStatusImageDownloader = new HttpStatusImageDownloader();
    HttpStatusChecker httpStatusChecker = new HttpStatusChecker();


    public void askStatus() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter HTTP status code");
        int userRequestStatus = scanner.nextInt();
        httpStatusChecker.getStatusImage(userRequestStatus);
        System.out.println("this status exists. Downloading....");
        httpStatusImageDownloader.downloadStatusImage(userRequestStatus);


    }
}
