package main.java.IIImidProject;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.ArrayList;

class ExtensionFileFilter extends FileFilter {
    private String description = "";
    private ArrayList<String> extensions = new ArrayList<>();//自定義方法,用於新增副檔名

    public void addExtension(String extension) {
        if (!extension.startsWith(".")) {
            extension = "." + extension;
            extensions.add(extension.toLowerCase());
        }
    }//用於設定該檔案過濾器的描述文字

    public void setDescription(String aDescription) {
        description = aDescription;
    }//繼承FileFilter類必須實現的抽象方法,返回該檔案過濾器的描述文字

    public String getDescription() {
        return description;
    }//繼承FileFilter類必須實現的抽象方法,判斷該檔案過濾器是否接受該檔案

    public boolean accept(File f) {
//如果該檔案是路徑,接受該檔案
        if (f.isDirectory()) return true;
//將檔名轉為小寫(全部轉為小寫後比較,用於忽略檔名大小寫)
        String name = f.getName().toLowerCase();
//遍歷所有可接受的副檔名,如果副檔名相同,該檔案可接受
        for (String extension : extensions) {
            if (name.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }
}