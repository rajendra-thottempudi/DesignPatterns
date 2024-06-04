package Examples;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

// Enum to represent the type of file node
enum Type {
    FILE, DIRECTORY, SYMLINK;
}

// Base class for file nodes
class IFileNode {
    protected String name;
    protected int size;
    protected Type fileType;

    public IFileNode(String name, int size, Type fileType) {
        this.name = name;
        this.size = size;
        this.fileType = fileType;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return fileType + "(" + name + ")";
    }
}

// Class to represent a file
class File extends IFileNode {
    private String extension;

    public File(String name, int size, String extension) {
        super(name, size, Type.FILE);
        this.extension = extension;
    }
}

// Class to represent a directory
class Directory extends IFileNode {
    private List<IFileNode> children;

    public Directory(String name) {
        super(name, 0, Type.DIRECTORY);
        this.children = new ArrayList<>();
    }

    public Directory(String name, List<IFileNode> children) {
        super(name, 0, Type.DIRECTORY);
        this.children = new ArrayList<>(children);
        for (IFileNode child : children) {
            this.size += child.getSize();
        }
    }

    // Method to add a child node to the directory
    public void addChild(IFileNode child) {
        children.add(child);
        size += child.getSize();
    }

    // Method to remove a child node from the directory
    public void removeChild(IFileNode child) {
        if (!children.contains(child)) {
            throw new IllegalArgumentException("Child not found!");
        }
        children.remove(child);
        size -= child.getSize();
    }

    // Method to get the children of the directory
    public List<IFileNode> getChildren() {
        return children;
    }
}

// Abstract class to represent a criteria
abstract class ICritera {
    public abstract boolean apply(IFileNode file);
}

// Class to represent a criteria that matches file name
class FileNameCriteria extends ICritera {
    private String targetFilename;

    public FileNameCriteria(String targetFilename) {
        this.targetFilename = targetFilename;
    }

    @Override
    public boolean apply(IFileNode file) {
        return file.getName().equals(targetFilename);
    }
}

// Class to represent a criteria that matches file size greater than a given size
class GreaterThanSizeCriteria extends ICritera {
    private int targetSize;

    public GreaterThanSizeCriteria(int targetSize) {
        this.targetSize = targetSize;
    }

    @Override
    public boolean apply(IFileNode file) {
        return file.getSize() >= targetSize;
    }
}

// Class to represent the default criteria which matches all files
class DefaultCriteria extends ICritera {
    @Override
    public boolean apply(IFileNode file) {
        return true;
    }
}

// Class to represent AND criteria
class ANDCriteria extends ICritera {
    private ICritera criteria1;
    private ICritera criteria2;

    public ANDCriteria(ICritera criteria1, ICritera criteria2) {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }

    @Override
    public boolean apply(IFileNode file) {
        return criteria1.apply(file) && criteria2.apply(file);
    }
}

// Class to represent OR criteria
class ORCriteria extends ICritera {
    private ICritera criteria1;
    private ICritera criteria2;

    public ORCriteria(ICritera criteria1, ICritera criteria2) {
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
    }

    @Override
    public boolean apply(IFileNode file) {
        return criteria1.apply(file) || criteria2.apply(file);
    }
}

// Class to build criteria using builder pattern
class BuildCriteria {
    private ICritera buildCriteria;

    public BuildCriteria() {
        this.buildCriteria = new DefaultCriteria();
    }

    public BuildCriteria andOp(ICritera criteria) {
        this.buildCriteria = new ANDCriteria(this.buildCriteria, criteria);
        return this;
    }

    public BuildCriteria orOp(ICritera criteria) {
        this.buildCriteria = new ORCriteria(this.buildCriteria, criteria);
        return this;
    }

    public ICritera build() {
        return this.buildCriteria;
    }
}

// Class to find files in the file system based on criteria
class FindLibrary {
    public List<IFileNode> findAPI(IFileNode root, ICritera criteria) {
        List<IFileNode> result = new ArrayList<>();
        findHelper(root, criteria, result);
        return result;
    }

    private void findHelper(IFileNode root, ICritera criteria, List<IFileNode> result) {
        if (root.fileType == Type.FILE) {
            if (criteria.apply(root)) {
                result.add(root);
            }
            return;
        }
        for (IFileNode child : ((Directory) root).getChildren()) {
            findHelper(child, criteria, result);
        }
    }

    public static void main(String[] args) {
        Directory dir1 = new Directory("dir1");
        File file1 = new File("file1", 100, "pdf");
        File file2 = new File("file2", 20, "xml");
        dir1.addChild(file1);
        dir1.addChild(file2);
        Directory root = new Directory("/");
        root.addChild(dir1);

        ICritera criteria = new BuildCriteria()
                .andOp(new FileNameCriteria("file2"))
                .andOp(new GreaterThanSizeCriteria(50))
                .build();

        FindLibrary lib = new FindLibrary();
        List<IFileNode> result = lib.findAPI(root, criteria);

        for (IFileNode node : result) {
            System.out.println(node);
        }
    }
}

