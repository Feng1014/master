package src.class01;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 统计某个路径下的文件数
 */
public class CountFiles {

    /**
     * 栈实现宽度优先遍历
     *
     * @param filePath
     * @return
     */
    public static int countFiles(String filePath) {
        File file = new File(filePath);
        if (!file.isFile() && !file.isDirectory()) {
            return 0;
        }
        if (file.isFile()) {
            return 1;
        }
        int files = 0;
        Stack<File> stack = new Stack<>();
        stack.push(file);
        while (!stack.isEmpty()) {
            File pop = stack.pop();
            for (File f : pop.listFiles()) {
                if (f.isFile()) {
                    files++;
                }
                if (f.isDirectory()) {
                    stack.push(f);
                }
            }
        }
        return files;
    }

    /**
     * 队列实现宽度优先遍历
     *
     * @param filePath
     * @return
     */
    public static int countFiles1(String filePath) {
        File file = new File(filePath);
        if (!file.isFile() && !file.isDirectory()) {
            return 0;
        }
        if (file.isFile()) {
            return 1;
        }
        int files = 0;
        Queue<File> queue = new LinkedList<>();
        queue.offer(file);
        while (!queue.isEmpty()) {
            File poll = queue.poll();
            for (File f : poll.listFiles()
            ) {
                if (f.isFile()) {
                    files++;
                }
                if (f.isDirectory()) {
                    queue.offer(f);
                }
            }
        }
        return files;
    }

    public static void main(String[] args) {
        String filePath = "C:\\Users\\Feng\\Desktop";
        if (countFiles(filePath) != countFiles1(filePath)) {
            System.out.println("error");
        } else {
            System.out.println(countFiles1(filePath));
        }
    }
}
