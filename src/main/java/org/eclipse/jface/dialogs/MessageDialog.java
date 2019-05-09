package org.eclipse.jface.dialogs;

import com.joyner.hack.jvm.ZipCompress;
import org.eclipse.swt.widgets.Shell;

import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class MessageDialog {

    public static  void openInformation(Shell shell, String info, String msg) {

    }

    public static void main(String[] args) throws Exception {
        System.out.println(0xFFFFFFFD);
        System.out.println(Integer.toBinaryString(60));
        System.out.println(-3>>2);
        System.out.println("00000000000000000000000000001010".length());
    }
}
