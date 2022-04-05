package packrle;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Packer {

    public void pack(final Reader reader, final Writer writer) throws IOException {
        int c = reader.read();
        int k = 1;
        int next;
        while (c != -1) {
            next = reader.read();
            if (c == next) k++;
            else {
                //если символ представлен в единственном экземпляре, цифру 1 перед ним не ставим
                if(k != 1) writer.write(Integer.toString(k));
                //тире показывает, что следующий символ либо цифр, либо тире
                if(Character.isDigit(c)||c == '-') writer.write("-");
                writer.write(c);
                k = 1;
                c = next;
            }
        }
        reader.close();
        writer.close();
    }

    public void unPack(final Reader reader, final Writer writer) throws IOException {
        int c = reader.read();
        while (c != -1) {
            int reps = 0;
            while(Character.isDigit(c)) {
                reps = reps * 10 + Integer.parseInt(Character.toString(c));
                c = reader.read();
            }
            if(c == '-') c = reader.read();
            if(reps == 0) reps = 1;
            writer.write(Character.toString(c).repeat(reps));
            c = reader.read();
        }
        reader.close();
        writer.close();
    }

}
