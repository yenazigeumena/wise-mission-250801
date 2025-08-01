package org.wiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WiseSaying {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Saying> sayings = new ArrayList<>();
        int id = 1;

        System.out.println("== 명언 앱==");
        while (true){
            System.out.println("명령) ");
            String command = scanner.nextLine().trim();

            if ("등록".equals(command)){
                System.out.print("명언 : ");
                String content = scanner.nextLine().trim();

                System.out.print("작가 : ");
                String author = scanner.nextLine().trim();

                Saying saying = new Saying(id, content, author);
                sayings.add(saying);
                System.out.println(id + "번 명언이 등록되었습니다.");
                id++;
            }
            else if ("목록".equals(command)) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("----------------------");
                for (int i = sayings.size()-1; i >= 0; i--) {
                    Saying saying = sayings.get(i);
                    System.out.println(saying.getId() + " / " + saying.getAuthor() + " / " + saying.getContent());
                }
            }
            else if (command.startsWith("삭제?id=")) {
                //substring : "삭제?id=" 이후의 문자열을 가져옴
                String idStr = command.substring("삭제?id=".length());
                int deleteId = Integer.parseInt(idStr);
                boolean removed = false;

                for (int i = 0; i < sayings.size(); i++) {
                    Saying saying = sayings.get(i);
                    if (saying.getId() == deleteId) {
                        sayings.remove(i);
                        System.out.println(deleteId + "번 명언이 삭제되었습니다.");
                        removed = true;
                        break;
                    }
                }
                if(!removed) {
                    System.out.println(deleteId + "번 명언은 존재하지 않습니다.");
                }
            }

            else if (command.startsWith("수정?id=")) {
                String idStr = command.substring("수정?id=".length());
                int updateId = Integer.parseInt(idStr);
                boolean edit = false;

                for (Saying saying : sayings) {
                    if (saying.getId() == updateId) {

                        System.out.println("명언(기존) : " + saying.getContent());
                        System.out.print("명언 : ");
                        String newContent = scanner.nextLine().trim();

                        System.out.println("작가(기존) : " + saying.getAuthor());
                        System.out.print("작가 : ");
                        String newAuthor = scanner.nextLine().trim();

                        saying.content = newContent;
                        saying.author = newAuthor;

                        System.out.println(updateId + "번 명언이 수정되었습니다.");
                        edit = true;
                        break;
                    }
                }
                if (!edit) {
                    System.out.println(updateId + "번 명언은 존재하지 않습니다.");
                }
            }

            else if ("종료".equals(command)) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }
        }
        scanner.close();
    }
    static class Saying {
        private int id;
        private String content;
        private String author;

        // Constructor
        public Saying(int id, String content, String author) {
            this.id = id;
            this.content = content;
            this.author = author;
        }

        // Getters
        public int getId() {
            return id;
        }
        public String getContent() {
            return content;
        }
        public String getAuthor() {
            return author;
        }
    }
}
