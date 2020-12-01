package com.hm.article.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.hm.article.action.ArticleAction;
import com.hm.article.dao.ArticleDao;
import com.hm.article.entity.Article;
import com.hm.article.entity.Role;
import com.hm.article.entity.RoleDetail;
import com.hm.article.entity.StoryEvent;
import com.hm.article.entity.StoryType;
import com.hm.article.entity.StoryLocation;
import com.hm.article.entity.StoryTimer;

/**
 * 
 * @author Administrator
 *
 */
public class ArticleService {

	private ArticleDao articleDao = new ArticleDao();

	/**
	 * 生成文章 结构方法：组装流程 主骨架
	 * 
	 * @param articleKey
	 * @return
	 */
	public Article getArticle(String articleKey) {
		Article article = new Article();

		// 根据关键字（人物）==》知识库（人物mysql）==》有没有
		// 1、方法：关键字去数据库（JDBC）

		// 搜索关键字（人物）类、对象、组件（同义词） 主角 全文搜索：分词 索引表
		Role role = articleDao.queryRole(articleKey);
		if (role == null) {
			return null;
		}
		article.setTitle(articleKey);
		article.setMainRole(role);

		// 查询与当前关键字人物有关联的人物 (主，从)
		List<Role> roles = articleDao.queryRoles(role);
		article.setRoles(roles);
		// 分析权重

		// 人物之间的共同故事属性（标签）
		List<StoryType> list = this.getCommStoryType(role, roles.get(0));
		article.setStoryTypes(list);

		// 找一个最贴切的地点
		StoryLocation storyLocation = this.getLocation(list);
		article.setStoryLocation(storyLocation);

		// 找一个最贴切的时间 断言：
		StoryTimer storyTimer = this.getTimer(list);
		article.setStoryTimer(storyTimer);

		// 找一个最贴切的事件
		StoryEvent storyEvent = this.getEvent(list);
		article.setStoryEvent(storyEvent);

		return article;
	}

	private StoryEvent getEvent(List<StoryType> list) {
		// 找出所有时间点（符合故事标签 的时间点）
		List<StoryEvent> storyEvents = this.articleDao.getAllEvent(list);
		// 统计数量：时间线上有多少故事属性
		// 转换map
		Map<Integer, List<StoryEvent>> storyEventMaps = new HashMap<Integer, List<StoryEvent>>();

		// 保存已经访问过的时间ID
		int oldStoryEventId = 0;
		List<StoryEvent> tempStoryEvents = null;
		for (StoryEvent storyEvent : storyEvents) {
			if (storyEvent.getEventId() != oldStoryEventId) {
				// 创建一个列表
				tempStoryEvents = new ArrayList<StoryEvent>();
				storyEventMaps.put(storyEvent.getEventId(), tempStoryEvents);
			}
			tempStoryEvents.add(storyEvent);
			oldStoryEventId = storyEvent.getEventId();
		}

		// map时间线上有多少个故事属性：统计结果 key:时间点的ID ，value:结果
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		// 得到所有关键字
		Set<Integer> keys = storyEventMaps.keySet();

		for (Integer key : keys) {
			// key==>value
			int count = storyEventMaps.get(key).size();
			// 设定临界
			if (count >= list.size() * 2 / 3) {
				countMap.put(key, count);
			}
		}

		Set<Integer> sets = countMap.keySet();

		if (countMap.size() == 1) {
			Integer key = (Integer) sets.toArray()[0];
			return storyEventMaps.get(key).get(0);
		} else {
			// 求最大值
			int max = 0;
			//
			int key_ = 0;
			// 统计结果中的所有关键字
			for (Integer key : sets) {
				if (countMap.get(key) > max) {
					max = countMap.get(key);
					key_ = key;
				}
			}

			return storyEventMaps.get(key_).get(0);
		}
	}

	/**
	 * 选择最佳故事发生时间 事件
	 * 
	 * @param list
	 * @return
	 */
	private StoryTimer getTimer(List<StoryType> list) {
		// 找出所有时间点（符合故事标签 的时间点）
		List<StoryTimer> storyTimers = this.articleDao.getAllTimer(list);
		// 统计数量：时间线上有多少故事属性
		// 转换map
		Map<Integer, List<StoryTimer>> storyTimerMaps = new HashMap<Integer, List<StoryTimer>>();

		// 保存已经访问过的时间ID
		int oldStoryTimerId = 0;
		List<StoryTimer> tempStoryTimers = null;
		for (StoryTimer storyTimer : storyTimers) {
			if (storyTimer.getStoryTimerId() != oldStoryTimerId) {
				// 创建一个列表
				tempStoryTimers = new ArrayList<StoryTimer>();
				storyTimerMaps.put(storyTimer.getStoryTimerId(), tempStoryTimers);
			}
			tempStoryTimers.add(storyTimer);
			oldStoryTimerId = storyTimer.getStoryTimerId();
		}

		// map时间线上有多少个故事属性：统计结果 key:时间点的ID ，value:结果
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		// 得到所有关键字
		Set<Integer> keys = storyTimerMaps.keySet();

		for (Integer key : keys) {
			// key==>value
			int count = storyTimerMaps.get(key).size();
			// 设定临界
			if (count >= list.size() * 2 / 3) {
				countMap.put(key, count);
			}
		}

		Set<Integer> sets = countMap.keySet();

		if (countMap.size() == 1) {
			Integer key = (Integer) sets.toArray()[0];
			return storyTimerMaps.get(key).get(0);
		} else {
			// 求最大值
			int max = 0;
			//
			int key_ = 0;
			// 统计结果中的所有关键字
			for (Integer key : sets) {
				if (countMap.get(key) > max) {
					max = countMap.get(key);
					key_ = key;
				}
			}
			return storyTimerMaps.get(key_).get(0);
		}
	}

	/**
	 * 找出一个最符合要求的地点
	 * 
	 * @param list
	 * @return
	 */
	private StoryLocation getLocation(List<StoryType> list) {
		// 找出所有地点
		List<StoryLocation> storyLocations = this.articleDao.getAllLocation(list);
		// 数据转换
		Map<Integer, List<StoryLocation>> locationMaps = new HashMap<Integer, List<StoryLocation>>();

		// 原来的KEY
		int oldKey = 0;
		List<StoryLocation> tempList = null;

		for (StoryLocation storyLocation : storyLocations) {
			if (storyLocation.getStoryLocationId() != oldKey) {
				tempList = new ArrayList<StoryLocation>();
				tempList.add(storyLocation);
				locationMaps.put(storyLocation.getStoryLocationId(), tempList);

			} else {
				tempList.add(storyLocation);
			}
			oldKey = storyLocation.getStoryLocationId();
		}

		// 匹配故事标签

		// 地点排序 地点：故事数量
		Map<Integer, Integer> locationCountMap = new HashMap<Integer, Integer>();

		Set<Integer> keys = locationMaps.keySet();

		// 统计
		for (Integer key : keys) {
			locationCountMap.put(key, locationMaps.get(key).size());
		}

		// 找到最大匹配地点，求最大值
		keys = locationCountMap.keySet();

		int max = 0;
		int key_ = 0;
		for (Integer key : keys) {
			if (locationCountMap.get(key) >= max) {
				max = locationCountMap.get(key);
				key_ = key;
			}
		}
		return locationMaps.get(key_).get(0);
	}

	/**
	 * 查询角色与角色之间的其同故事标签（属性）
	 * 
	 * @param role
	 * @param role2
	 * @return
	 */
	private List<StoryType> getCommStoryType(Role role, Role role2) {
		List<StoryType> list = new ArrayList<StoryType>();
		// 主角的故事属性
		List<StoryType> list01 = this.articleDao.queryStoryType(role);
		// 配角的故事属性
		List<StoryType> list02 = this.articleDao.queryStoryType(role2);
		// 相同的故事属性
		for (StoryType storyType : list01) {
			for (StoryType storyType2 : list02) {
				if (storyType.getStoryTypeId() == storyType2.getStoryTypeId()) {
					list.add(storyType);
					break;
				}
			}
		}
		return list;
	}

	private StoryLocation getMaxInfos(List<StoryType> list) {
		// 找出所有地点
		List<StoryLocation> storyLocations = this.articleDao.getAllLocation(list);
		// 数据转换
		Map<Integer, List<StoryLocation>> locationMaps = new HashMap<Integer, List<StoryLocation>>();

		// 原来的KEY
		int oldKey = 0;
		List<StoryLocation> tempList = null;

		for (StoryLocation storyLocation : storyLocations) {
			if (storyLocation.getStoryLocationId() != oldKey) {
				tempList = new ArrayList<StoryLocation>();
				tempList.add(storyLocation);
				locationMaps.put(storyLocation.getStoryLocationId(), tempList);

			} else {
				tempList.add(storyLocation);
			}
			oldKey = storyLocation.getStoryLocationId();
		}

		// 匹配故事标签

		// 地点排序 地点：故事数量
		Map<Integer, Double> locationCountMap = new HashMap<Integer, Double>();

		Set<Integer> keys = locationMaps.keySet();

		for (Integer key : keys) {
			List<StoryLocation> list_ = locationMaps.get(key);
			locationCountMap.put(key, this.calWeight(list, list_));
		}

		return null;
	}

	/**
	 * 计算匹配权重值
	 */
	public double calWeight(List<StoryType> srcStoryType, List<StoryLocation> descStoryType) {
		// 排序
		Collections.sort(srcStoryType);
		int total = 0;
		double returnTotal = 0;
		// 扫描
		for (StoryType storyType : srcStoryType) {
			// 计算总量
			total += storyType.getWeight();
		}
		for (StoryLocation storyType : descStoryType) {
			double baiFeiBi = storyType.getWeight() / total;
			int storyTypeId = storyType.getStoryTypeId();
			for (StoryLocation s : descStoryType) {
				if (s.getStoryTypeId() == storyTypeId) {
					returnTotal += (1 - Math.abs(s.getWeight() - storyType.getWeight()) / s.getStoryTypeId() - 1)
							* baiFeiBi;
				}
			}
		}
		return returnTotal;
	}

	public static void main(String[] args) {

		ArticleDao articleDao = new ArticleDao();

		// 根据关键字（人物）==》知识库（人物mysql）==》有没有
		// 1、方法：关键字去数据库（JDBC）

		// 搜索关键字（人物）类、对象、组件（同义词） 主角 全文搜索：分词 索引表
		Role role = articleDao.queryRole("牛郎");

		// 查询与当前关键字人物有关联的人物 (主，从)
		List<Role> roles = articleDao.queryRoles(role);
		ArticleService articleService = new ArticleService();
		// 分析权重

		// 人物之间的共同故事属性（标签）
		List<StoryType> list = articleService.getCommStoryType(role, roles.get(0));

		articleService.getMaxInfos(list);
	}

}
