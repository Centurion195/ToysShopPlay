import java.io.FileWriter;
import java.io.IOException;

public class SaveResult {
    private String fileName;

    public SaveResult(String fileName) {
        this.fileName = fileName;
        try(FileWriter writer = new FileWriter(fileName, false)) {
            writer.write("");
            writer.flush();
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void write(String textLine) {
        try(FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(textLine + "\n");
            writer.flush();
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
