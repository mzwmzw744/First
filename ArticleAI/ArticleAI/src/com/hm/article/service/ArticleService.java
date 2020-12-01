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
	 * �������� �ṹ��������װ���� ���Ǽ�
	 * 
	 * @param articleKey
	 * @return
	 */
	public Article getArticle(String articleKey) {
		Article article = new Article();

		// ���ݹؼ��֣����==��֪ʶ�⣨����mysql��==����û��
		// 1���������ؼ���ȥ���ݿ⣨JDBC��

		// �����ؼ��֣�����ࡢ���������ͬ��ʣ� ���� ȫ���������ִ� ������
		Role role = articleDao.queryRole(articleKey);
		if (role == null) {
			return null;
		}
		article.setTitle(articleKey);
		article.setMainRole(role);

		// ��ѯ�뵱ǰ�ؼ��������й��������� (������)
		List<Role> roles = articleDao.queryRoles(role);
		article.setRoles(roles);
		// ����Ȩ��

		// ����֮��Ĺ�ͬ�������ԣ���ǩ��
		List<StoryType> list = this.getCommStoryType(role, roles.get(0));
		article.setStoryTypes(list);

		// ��һ�������еĵص�
		StoryLocation storyLocation = this.getLocation(list);
		article.setStoryLocation(storyLocation);

		// ��һ�������е�ʱ�� ���ԣ�
		StoryTimer storyTimer = this.getTimer(list);
		article.setStoryTimer(storyTimer);

		// ��һ�������е��¼�
		StoryEvent storyEvent = this.getEvent(list);
		article.setStoryEvent(storyEvent);

		return article;
	}

	private StoryEvent getEvent(List<StoryType> list) {
		// �ҳ�����ʱ��㣨���Ϲ��±�ǩ ��ʱ��㣩
		List<StoryEvent> storyEvents = this.articleDao.getAllEvent(list);
		// ͳ��������ʱ�������ж��ٹ�������
		// ת��map
		Map<Integer, List<StoryEvent>> storyEventMaps = new HashMap<Integer, List<StoryEvent>>();

		// �����Ѿ����ʹ���ʱ��ID
		int oldStoryEventId = 0;
		List<StoryEvent> tempStoryEvents = null;
		for (StoryEvent storyEvent : storyEvents) {
			if (storyEvent.getEventId() != oldStoryEventId) {
				// ����һ���б�
				tempStoryEvents = new ArrayList<StoryEvent>();
				storyEventMaps.put(storyEvent.getEventId(), tempStoryEvents);
			}
			tempStoryEvents.add(storyEvent);
			oldStoryEventId = storyEvent.getEventId();
		}

		// mapʱ�������ж��ٸ��������ԣ�ͳ�ƽ�� key:ʱ����ID ��value:���
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		// �õ����йؼ���
		Set<Integer> keys = storyEventMaps.keySet();

		for (Integer key : keys) {
			// key==>value
			int count = storyEventMaps.get(key).size();
			// �趨�ٽ�
			if (count >= list.size() * 2 / 3) {
				countMap.put(key, count);
			}
		}

		Set<Integer> sets = countMap.keySet();

		if (countMap.size() == 1) {
			Integer key = (Integer) sets.toArray()[0];
			return storyEventMaps.get(key).get(0);
		} else {
			// �����ֵ
			int max = 0;
			//
			int key_ = 0;
			// ͳ�ƽ���е����йؼ���
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
	 * ѡ����ѹ��·���ʱ�� �¼�
	 * 
	 * @param list
	 * @return
	 */
	private StoryTimer getTimer(List<StoryType> list) {
		// �ҳ�����ʱ��㣨���Ϲ��±�ǩ ��ʱ��㣩
		List<StoryTimer> storyTimers = this.articleDao.getAllTimer(list);
		// ͳ��������ʱ�������ж��ٹ�������
		// ת��map
		Map<Integer, List<StoryTimer>> storyTimerMaps = new HashMap<Integer, List<StoryTimer>>();

		// �����Ѿ����ʹ���ʱ��ID
		int oldStoryTimerId = 0;
		List<StoryTimer> tempStoryTimers = null;
		for (StoryTimer storyTimer : storyTimers) {
			if (storyTimer.getStoryTimerId() != oldStoryTimerId) {
				// ����һ���б�
				tempStoryTimers = new ArrayList<StoryTimer>();
				storyTimerMaps.put(storyTimer.getStoryTimerId(), tempStoryTimers);
			}
			tempStoryTimers.add(storyTimer);
			oldStoryTimerId = storyTimer.getStoryTimerId();
		}

		// mapʱ�������ж��ٸ��������ԣ�ͳ�ƽ�� key:ʱ����ID ��value:���
		Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
		// �õ����йؼ���
		Set<Integer> keys = storyTimerMaps.keySet();

		for (Integer key : keys) {
			// key==>value
			int count = storyTimerMaps.get(key).size();
			// �趨�ٽ�
			if (count >= list.size() * 2 / 3) {
				countMap.put(key, count);
			}
		}

		Set<Integer> sets = countMap.keySet();

		if (countMap.size() == 1) {
			Integer key = (Integer) sets.toArray()[0];
			return storyTimerMaps.get(key).get(0);
		} else {
			// �����ֵ
			int max = 0;
			//
			int key_ = 0;
			// ͳ�ƽ���е����йؼ���
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
	 * �ҳ�һ�������Ҫ��ĵص�
	 * 
	 * @param list
	 * @return
	 */
	private StoryLocation getLocation(List<StoryType> list) {
		// �ҳ����еص�
		List<StoryLocation> storyLocations = this.articleDao.getAllLocation(list);
		// ����ת��
		Map<Integer, List<StoryLocation>> locationMaps = new HashMap<Integer, List<StoryLocation>>();

		// ԭ����KEY
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

		// ƥ����±�ǩ

		// �ص����� �ص㣺��������
		Map<Integer, Integer> locationCountMap = new HashMap<Integer, Integer>();

		Set<Integer> keys = locationMaps.keySet();

		// ͳ��
		for (Integer key : keys) {
			locationCountMap.put(key, locationMaps.get(key).size());
		}

		// �ҵ����ƥ��ص㣬�����ֵ
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
	 * ��ѯ��ɫ���ɫ֮�����ͬ���±�ǩ�����ԣ�
	 * 
	 * @param role
	 * @param role2
	 * @return
	 */
	private List<StoryType> getCommStoryType(Role role, Role role2) {
		List<StoryType> list = new ArrayList<StoryType>();
		// ���ǵĹ�������
		List<StoryType> list01 = this.articleDao.queryStoryType(role);
		// ��ǵĹ�������
		List<StoryType> list02 = this.articleDao.queryStoryType(role2);
		// ��ͬ�Ĺ�������
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
		// �ҳ����еص�
		List<StoryLocation> storyLocations = this.articleDao.getAllLocation(list);
		// ����ת��
		Map<Integer, List<StoryLocation>> locationMaps = new HashMap<Integer, List<StoryLocation>>();

		// ԭ����KEY
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

		// ƥ����±�ǩ

		// �ص����� �ص㣺��������
		Map<Integer, Double> locationCountMap = new HashMap<Integer, Double>();

		Set<Integer> keys = locationMaps.keySet();

		for (Integer key : keys) {
			List<StoryLocation> list_ = locationMaps.get(key);
			locationCountMap.put(key, this.calWeight(list, list_));
		}

		return null;
	}

	/**
	 * ����ƥ��Ȩ��ֵ
	 */
	public double calWeight(List<StoryType> srcStoryType, List<StoryLocation> descStoryType) {
		// ����
		Collections.sort(srcStoryType);
		int total = 0;
		double returnTotal = 0;
		// ɨ��
		for (StoryType storyType : srcStoryType) {
			// ��������
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

		// ���ݹؼ��֣����==��֪ʶ�⣨����mysql��==����û��
		// 1���������ؼ���ȥ���ݿ⣨JDBC��

		// �����ؼ��֣�����ࡢ���������ͬ��ʣ� ���� ȫ���������ִ� ������
		Role role = articleDao.queryRole("ţ��");

		// ��ѯ�뵱ǰ�ؼ��������й��������� (������)
		List<Role> roles = articleDao.queryRoles(role);
		ArticleService articleService = new ArticleService();
		// ����Ȩ��

		// ����֮��Ĺ�ͬ�������ԣ���ǩ��
		List<StoryType> list = articleService.getCommStoryType(role, roles.get(0));

		articleService.getMaxInfos(list);
	}

}
