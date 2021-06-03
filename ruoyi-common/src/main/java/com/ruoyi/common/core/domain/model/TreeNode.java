package com.ruoyi.common.core.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * -树构建
 * @author banrenhe
 *
 */
public class TreeNode {

	protected String id;
	protected String parentId;
	protected List<TreeNode> children = new ArrayList<>();

	public void add(TreeNode node) {
		children.add(node);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "TreeNode{" +
				"id=" + id +
				", parentId=" + parentId +
				", children=" + children +
				'}';
	}
}

