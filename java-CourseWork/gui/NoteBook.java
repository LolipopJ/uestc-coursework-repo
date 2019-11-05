package coursework.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Lolipop
 * @lastUpdate 2019/11/5
 */
public class NoteBook {
    private JTextArea textArea;

    private NoteBook() {
        // Basic
        JFrame frame = new JFrame("NoteBook");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // TextArea
        textArea = new JTextArea();
        textArea.setFont(new Font("黑体", Font.PLAIN, 20));

        // ScrollPane
        JScrollPane pane = new JScrollPane(textArea);
        frame.add(pane);

        // Menu
        // Menu-init menu body
        JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu);
        // Menu-create menus
        JMenu fileMenu = new JMenu("File(F)");
        JMenu editMenu = new JMenu("Edit(E)");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        editMenu.setMnemonic(KeyEvent.VK_E);

        // Menu-create menu items
        JMenuItem newItem = new JMenuItem("New(N)", KeyEvent.VK_N);
        JMenuItem openItem = new JMenuItem("Open(O)", KeyEvent.VK_O);
        JMenuItem saveItem = new JMenuItem("Save(S)", KeyEvent.VK_S);
        JMenuItem exitItem = new JMenuItem("Exit(X)", KeyEvent.VK_X);
        JMenuItem cutItem = new JMenuItem("Cut(T)", KeyEvent.VK_T);
        JMenuItem copyItem = new JMenuItem("Copy(C)", KeyEvent.VK_C);
        JMenuItem pasteItem = new JMenuItem("Paste(P)", KeyEvent.VK_P);

        // Menu-set item events
        // 新建NoteBook窗口
        newItem.addActionListener(e -> {
            new NoteBook();
        });

        // 打开文件
        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(openItem) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                // 在新窗口读取文件
                NoteBook newFile = new NoteBook();
                readFile(file, newFile.textArea);
            };
        });

        // 保存文件
        saveItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            
            // 后缀名过滤
            fileChooser.setFileFilter(new FileNameExtensionFilter("文本文件(*.txt)", "txt"));

            if (fileChooser.showSaveDialog(saveItem) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                saveFile(file.getPath());
            };
        });

        // 退出NoteBook
        exitItem.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(null, "Confirm exit NoteBook?",
                    "Exit", JOptionPane.YES_NO_OPTION);
            if (choice == 0) {
                frame.dispose();
            }
        });

        // Menu-add items to menus
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // Menu-add menus to menu body & set visible
        menu.add(fileMenu);
        menu.add(editMenu);
        menu.setVisible(true);

        // 设置界面可见
        frame.setVisible(true);
    }

    /**
     * 读取文件并在新的窗口显示出来
     * @param file 选择欲打开的文件
     * @param textArea 新建窗口的textArea
     */
    private void readFile (File file, JTextArea textArea) {
        BufferedReader bReader;
        try {
            // init BufferedReader & StringBuilder & str
            bReader = new BufferedReader(new FileReader(file));
            StringBuilder sBuilder = new StringBuilder();
            String str;

            // BufferedReader所读取数据不为空行时，把str存储的行内容传递给StringBuilder
            while ((str = bReader.readLine()) != null) {
                sBuilder.append(str).append('\n');
            }

            // 将StringBuilder存储的数据显示在textArea上
            textArea.setText(sBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将输入的内容保存为文本文件
     * @param path 文件存储的路径
     */
    private void saveFile (String path) {
        FileOutputStream os;
        try {
            // init FileOutputStream
            os = new FileOutputStream(path);

            // 将textArea域的内容转化为UTF_8编码格式的文本字符对象，并写入对应路径下的文件中
            os.write(textArea.getText().getBytes(StandardCharsets.UTF_8));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        new NoteBook();
    }
}
