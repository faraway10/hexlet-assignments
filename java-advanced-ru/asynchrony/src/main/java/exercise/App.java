package exercise;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String filePath1, String filePath2, String resultFilePath) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Path path1 = Paths.get(filePath1);
                return Files.readString(path1);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Path path2 = Paths.get(filePath2);
                return Files.readString(path2);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });

        CompletableFuture<String> future3 = future1.thenCombine(future2, (str1, str2) -> {
            Path path3 = Paths.get(resultFilePath);
            try {
                Files.writeString(path3, str1);
                Files.writeString(path3, str2, StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            return str1 + str2;
        }).exceptionally(ex -> {
            System.out.println(ex.getMessage());
            return null;
        });

        return future3;

    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN

        // END
    }
}
