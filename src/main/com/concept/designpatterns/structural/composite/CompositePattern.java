package src.main.com.concept.designpatterns.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class CompositePattern {
    public static void main(String[] args) {
        Directory root = new Directory("Root");
        Directory folder1 = new Directory("Folder 1");
        Directory folder2 = new Directory("Folder 2");
        File file1 = new File("File 1.txt");
        File file2 = new File("File 2.txt");
        File file3 = new File("File 3.txt");

        root.addComponent(folder1);
        root.addComponent(folder2);
        folder1.addComponent(file1);
        folder2.addComponent(file2);
        root.addComponent(file3);

        root.printName();
    }
}

interface FileSystemComponent {
    void printName();
}

class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println("File: " + name);
    }
}

class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components;

    public Directory(String name) {
        this.name = name;
        components = new ArrayList<>();
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void printName() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components) {
            component.printName();
        }
    }
}
