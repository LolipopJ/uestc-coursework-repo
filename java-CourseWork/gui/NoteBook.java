package coursework.gui;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author Lolipop
 * @version 1.0
 * @lastUpdate 2019/11/6
 */
public class NoteBook {
    private JTextArea textArea;
    private File file = null;
    private JFrame frame = new JFrame("NoteBook");
    private Clipboard clipboard = frame.getToolkit().getSystemClipboard();

    private NoteBook() {
        // Frame
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // TextArea
        textArea = new JTextArea();
        textArea.setFont(new Font("黑体", Font.PLAIN, 20));

        // ScrollPane
        JScrollPane pane = new JScrollPane(textArea);
        frame.add(pane);

        // Menu
        // Menu: init menu body
        JMenuBar menu = new JMenuBar();
        frame.setJMenuBar(menu);

        // Menu: create menus
        JMenu fileMenu = new JMenu("File(F)");
        JMenu editMenu = new JMenu("Edit(E)");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        editMenu.setMnemonic(KeyEvent.VK_E);

        // Menu: create menu items
        JMenuItem newItem = new JMenuItem("New(N)", KeyEvent.VK_N);
        JMenuItem openItem = new JMenuItem("Open(O)", KeyEvent.VK_O);
        JMenuItem saveItem = new JMenuItem("Save(S)", KeyEvent.VK_S);
        JMenuItem exitItem = new JMenuItem("Exit(X)", KeyEvent.VK_X);
        JMenuItem cutItem = new JMenuItem("Cut(T)", KeyEvent.VK_T);
        JMenuItem copyItem = new JMenuItem("Copy(C)", KeyEvent.VK_C);
        JMenuItem pasteItem = new JMenuItem("Paste(P)", KeyEvent.VK_P);

        // fileMenu: set events
        // 新建NoteBook窗口
        newItem.addActionListener(e -> new NoteBook());

        // 打开文件
        openItem.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(openItem) == JFileChooser.APPROVE_OPTION) {
                File aimFile = fileChooser.getSelectedFile();

                // 打开新窗口并读取文件
                NoteBook newNoteBook = new NoteBook();
                readFile(aimFile, newNoteBook.textArea);
            }
        });

        // 保存文件
        saveItem.addActionListener(e -> {
            // 文件存在时（已经保存过）
            if (file != null) {
                saveFile(file.getPath());
            }

            // 文件不存在时（初次保存）
            else {
                JFileChooser fileChooser = new JFileChooser();

                // 后缀名过滤
                String extension = ".txt";
                fileChooser.setFileFilter(new FileNameExtensionFilter("文本文件(*.txt)", extension));

                if (fileChooser.showSaveDialog(saveItem) == JFileChooser.APPROVE_OPTION) {
                    File newFile = fileChooser.getSelectedFile();

                    // 获取用户输入的文件名
                    String fName = fileChooser.getName(newFile);

                    // 若文件名不包含".txt"后缀则在最后加上".txt"
                    if (!fName.contains(extension)) {
                        newFile = new File(fileChooser.getCurrentDirectory(), fName+".txt");
                    }

                    // 保存文件
                    saveFile(newFile.getPath());

                    // 修改全局变量file
                    file = newFile;

                    // 修改窗口title
                    frame.setTitle(file.getName()+" - NoteBook");
                }
            }
        });

        // 退出NoteBook
        exitItem.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(null, "Confirm exit NoteBook?",
                    "Exit", JOptionPane.YES_NO_OPTION);
            if (choice == 0) {
                frame.dispose();
            }
        });

        // editMenu: set events
        // 剪切
        cutItem.addActionListener(e -> {
            // 将选中的文本内容传递给剪切板
            StringSelection cutText = new StringSelection(textArea.getSelectedText());
            clipboard.setContents(cutText, null);

            // 删除选中文本
            int start = textArea.getSelectionStart();
            int end = textArea.getSelectionEnd();
            textArea.replaceRange("", start, end);
        });

        // 复制
        copyItem.addActionListener(e -> {
            StringSelection copyText = new StringSelection(textArea.getSelectedText());
            clipboard.setContents(copyText, null);
        });

        // 粘贴
        pasteItem.addActionListener(e -> {
            // 从剪切板获取内容保存到contents中
            Transferable contents = clipboard.getContents(null);

            // 设置DataFlavor映射剪切板String型数据
            DataFlavor flavor = DataFlavor.stringFlavor;

            // 若存在String型数据，则将数据粘贴到光标选中处
            if (contents.isDataFlavorSupported(flavor)) {
                try {
                    // 将contents数据转化成String格式保存到text中
                    String text = (String)contents.getTransferData(flavor);

                    // 替换选中内容
                    int start = textArea.getSelectionStart();
                    int end = textArea.getSelectionEnd();
                    textArea.replaceRange(text, start, end);
                } catch (UnsupportedFlavorException | IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Menu: add items to menus
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // Menu: add menus to menu body & set visible
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
