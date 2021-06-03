package com.ruoyi.system.util;

import com.ruoyi.common.utils.TreeUtil;
import com.ruoyi.system.domain.vo.CommonTreeVO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author banrenhe
 */
public class TreeVOUtil {

	/**
	 * 构建数据、服务管理树 树结构
	 *
	 * @param commonTreeDto
	 * @return
	 */
	public static <T> List<CommonTreeVO> getBuildTree(List<T> commonTreeDto, String root) {

		List<CommonTreeVO> treeList = commonTreeDto.stream()
				.map(commonTree -> {
					CommonTreeVO node = new CommonTreeVO();
					//复制对象
					BeanUtils.copyProperties(commonTree, node, CommonTreeVO.class);
					return node;
				}).collect(Collectors.toList());
		return TreeUtil.buildByRecursive(treeList, root);
	}
}
