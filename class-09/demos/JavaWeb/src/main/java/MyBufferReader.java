import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class MyBufferReader extends BufferedReader {
    public MyBufferReader(Reader in, int sz) {
        super(in, sz);
    }

    public MyBufferReader(Reader in) {
        super(in);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Mine is better");
    }
}
