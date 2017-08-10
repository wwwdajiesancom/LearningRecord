package www.loujie.com.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 存放目录结构
 * 
 * @author loujie
 *
 */
public class DirEntry implements Serializable {

	private static final long serialVersionUID = -9208631844954822833L;
	public static String file_suffix = ".m3u8";
	public static final String bak_file_name = "dir_entry.bak";

	private Boolean isRoot = false;// 是否为根,第一层级目录(也就是从控制台输入的目录)
	private String path;// 路径
	private Boolean isLeaf = false;// 是否为叶子（下面没有目录了）
	private List<DirEntry> childDirEntrys = new ArrayList<>();// 子目录
	private Integer completeDirSize = 0;// 完成子目录个数,通过getCompleteStatus设置的值
	private List<String> unchildFiles = new ArrayList<>();// 子文件
	private List<String> completeChildFiles = new ArrayList<>();// 完成的
	private List<String> failChildFiles = new ArrayList<>();// 失败的

	public DirEntry() {
	}

	public DirEntry(String path) {
		this(path, false);
	}

	public DirEntry(String path, Boolean isRoot) {
		this.path = path;
		this.isRoot = isRoot;
	}

	public List<String> getFailChildFiles() {
		return failChildFiles;
	}

	public void setFailChildFiles(List<String> failChildFiles) {
		this.failChildFiles = failChildFiles;
	}

	public Boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	public List<DirEntry> getChildDirEntrys() {
		return childDirEntrys;
	}

	public void setChildDirEntrys(List<DirEntry> childDirEntrys) {
		this.childDirEntrys = childDirEntrys;
	}

	public Integer getCompleteDirSize() {
		return completeDirSize;
	}

	public void setCompleteDirSize(Integer completeDirSize) {
		this.completeDirSize = completeDirSize;
	}

	public synchronized List<String> getUnchildFiles() {
		return unchildFiles;
	}

	public void setUnchildFiles(List<String> unchildFiles) {
		this.unchildFiles = unchildFiles;
	}

	public synchronized List<String> getCompleteChildFiles() {
		return completeChildFiles;
	}

	public void setCompleteChildFiles(List<String> completeChildFiles) {
		this.completeChildFiles = completeChildFiles;
	}

	/**
	 * 调用该方法,必须要先初始化
	 * 
	 * @return
	 */
	public Boolean getCompleteStatus() {
		// 1.先根据快捷的计算
		if ((this.unchildFiles.size() == 0) && (this.completeDirSize - this.childDirEntrys.size() >= 0)) {
			return true;
		}
		// 2.再通过迭代的方式计算
		if (this.unchildFiles.size() == 0) {
			// 2.1如果是叶子节点
			if (this.isLeaf) {
				return (this.unchildFiles.size() == 0);
			}
			// 2.2不是叶子节点
			for (DirEntry item : this.childDirEntrys) {
				// 如果不是叶子,并且尚未加载过
				if (!item.isLeaf && item.getChildDirEntrys().size() <= 0) {
					return false;
				}
				// 如果有一个是false则结束
				if (!item.getCompleteStatus()) {
					return false;
				}
			}
			// 2.3上面都通过了,说明子目录都完成了
			this.setCompleteDirSize(this.childDirEntrys.size());
			// 3.返回结果
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return this.path;
	}

}
