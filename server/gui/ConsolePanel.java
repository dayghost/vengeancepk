package server.gui;

import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JEditorPane;

import java.util.*;

/**
 *
 * @author Requiem-Reborn
 */
 
public class ConsolePanel extends JEditorPane {

    private String header = "<html>\r\n<head></head><body>str</body></html>";

    List<String>data = new ArrayList<String>(5);

    public ConsolePanel(){
        setBackground(Color.black);
        setEditable(false);
        setContentType("text/html");
        setText("<html>\r\n<head></head><body></body></html>");
        setSystemVars();
    }

    public final void setSystemVars(){
        System.setOut(new PrintStream(new SysOutStream()));
        System.setErr(new PrintStream(new ErrOutStream()));
    }

    public void append(String str){
        if(str == null)
            return;
                            while(data.size() > 100)
                        data.remove(0);
                data.add("<font color=\"white\">" + str.replaceAll("\r","").replaceAll("\n","<br />") + "</font>");
        String s = "";
        for(String str1 : data)
            if(str1 != null)
            s += str1;
           setText(header.replaceAll("str",s));
           repaint();
    }

        public void appendErr(String str){
            str = str.trim();
                    if(str == null)
            return;
                    while(data.size() > 100)
                        data.remove(0);
                            data.add("<font color=\"red\">" + str.replaceAll("\r","").replaceAll("\n","<br />") + "</font>");
        String s = "";
        for(String str1 : data)
            if(str1 != null)
            s += str1;
           setText(header.replaceAll("str",s));
           repaint();
    }

public class SysOutStream extends OutputStream{

	OutputStream out;

	public SysOutStream(){
        try{
        out = new FileOutputStream("sys.txt",true);
        }catch(Exception e){}
}

        @Override
        public void write(int b) throws IOException {
	try{
		out.write(b);
        }catch(Exception e){}
            append(new String(new char[]{(char)b}));
        }

        @Override
        public void write(byte[] b){
	try{
		out.write(b);
        }catch(Exception e){}
            append(new String(b));
        }

                @Override
        public void write(byte[] b,int offset,int len){
	try{
		out.write(b,offset,len);
        }catch(Exception e){}
            append(new String(b,offset,len));
        }

}

public class ErrOutStream extends OutputStream{
    
    OutputStream out;
    
    public ErrOutStream(){
        try{
        out = new FileOutputStream("err.txt",true);
        }catch(Exception e){}
    }


        @Override
        public void write(int b) throws IOException {
            out.write(b);
            appendErr(new String(new char[]{(char)b}));
        }

        @Override
        public void write(byte[] b) throws IOException{
            out.write(b);
            appendErr(new String(b) + "\r\n");
        }

                @Override
        public void write(byte[] b,int offset,int len) throws IOException{
                    out.write(b,offset,len);
            appendErr(new String(b,offset,len) + "\r\n");
        }

}

}
